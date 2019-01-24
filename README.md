# 大叔基础类库
> 我们一般会把公用的代码放在一个包里，然后其它 项目可以直接使用，就像你使用第三方包一样！
## 主要内容
* [x] 仓库
* [x] 发到本地仓库
* [x] 私有仓库如果添加用户名和密码
* [x] 使用和引用私有仓库
* [x] jpa实体基类和继承
* [x] mybatis建立和更新时间自动填充

### 仓库
存储包的地方叫做仓库，一般可以分为本地仓库和远程仓库，本地一般用mavenLocal表示，在build.gradle中我们都可以看到，一般在安装包时，会优先从本地仓库下载，这样速度是最快的；远程仓库可以在世界各地使用你的包包，提高了代码的重用，面向对象里叫做`DRY`原则。
[详细介绍](https://www.cnblogs.com/lori/p/10242486.html)
### 发到本地仓库
```
apply plugin: "maven-publish"
task sourceJar(type: Jar) {
    from sourceSets.main.allJava
    classifier "sources" //定义一个标志 (生成的jar包后面加上sources, 如: jlib-2.2.11-sources.jar)
}

publishing {
    publications {
        maven(MavenPublication) {
            from components.java
            artifact sourceJar
        }
    }

    //定义目标仓库 (包所存放的地方)
    repositories {
        mavenLocal()
    }
}

```
### 私有仓库如果添加用户名和密码
```
repositories {
    maven {

        if (project.version.endsWith('-SNAPSHOT')) {
            url = "快照版本的nexus仓库地址"
        } else {
            url = "release版本的仓库地址"
        }

        credentials {
            username 'nexus仓库用户名'
            password 'nexus仓库密码'
        }
    }
    }
}
```
### 使用和引用私有仓库
```
 repositories {
        mavenLocal()
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        maven {
            url 'https://maven.zhyea.com/nexus/content/groups/public'
            credentials {
                username 'robin'
                password 'robin'
            }
        }
        mavenCentral()
    }
```
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
