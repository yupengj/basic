[返回目录](../README.md)

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