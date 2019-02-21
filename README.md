# 大叔基础类库
> 我们一般会把公用的代码放在一个包里，然后其它 项目可以直接使用，就像你使用第三方包一样！
## 主要内容
* [x] [仓库](./document/repository.md)
* [x] 发到本地仓库
* [x] 私有仓库如果添加用户名和密码
* [x] 使用和引用私有仓库
* [x] jpa实体基类和继承
* [x] mybatis建立和更新时间自动填充
* [x] oauth2流程


### jpa实体基类和继承
[详细介绍](https://www.cnblogs.com/lori/p/10266508.html)

```
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class EntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;


  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "created_on")
  protected LocalDateTime createdOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "updated_on")
  protected LocalDateTime updatedOn;

  /**
   * Sets createdAt before insert
   */
  @PrePersist
  public void setCreationDate() {
    this.createdOn = LocalDateTime.now();
    this.updatedOn = LocalDateTime.now();
  }

  /**
   * Sets updatedAt before update
   */
  @PreUpdate
  public void setChangeDate() {
    this.updatedOn = LocalDateTime.now();
  }
}

// 具体类，继承这个基类
@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TestEntityBuilder extends EntityBase {
  private String title;
  private String description;

  @Builder(toBuilder = true)
  public TestEntityBuilder(Long id, LocalDateTime createdOn, LocalDateTime updatedOn,
                           String title, String description) {
    super(id, createdOn, updatedOn);
    this.title = title;
    this.description = description;
  }
}
```

### mybatis建立和更新时间自动填充
[详细介绍](https://www.cnblogs.com/lori/p/10281976.html)
```
@Getter
@Builder(toBuilder = true)
@ToString
public class UserInfo {
  private Long id;
  private String name;
  private String email;

  @CreatedOnFuncation
  private LocalDateTime createdOn;
  @UpdatedOnFuncation
  private LocalDateTime updatedOn;
}
```
### oauth2流程
参考：https://tools.ietf.org/html/rfc6749
```
     +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+
```
authorization information.  Unlike access tokens, refresh tokens are
   intended for use only with authorization servers and are never sent
   to resource servers.
```
  +--------+                                           +---------------+
  |        |--(A)------- Authorization Grant --------->|               |
  |        |                                           |               |
  |        |<-(B)----------- Access Token -------------|               |
  |        |               & Refresh Token             |               |
  |        |                                           |               |
  |        |                            +----------+   |               |
  |        |--(C)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(D)- Protected Resource --| Resource |   | Authorization |
  | Client |                            |  Server  |   |     Server    |
  |        |--(E)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(F)- Invalid Token Error -|          |   |               |
  |        |                            +----------+   |               |
  |        |                                           |               |
  |        |--(G)----------- Refresh Token ----------->|               |
  |        |                                           |               |
  |        |<-(H)----------- Access Token -------------|               |
  +--------+           & Optional Refresh Token        +---------------+
```