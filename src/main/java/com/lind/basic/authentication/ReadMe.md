### AuthenticationProvider
* 默认实现：DaoAuthenticationProvider
授权方式提供者，判断授权有效性，用户有效性，在判断用户是否有效性，它依赖于UserDetailsService实例，开发人员可以自定义UserDetailsService的实现。
1. additionalAuthenticationChecks方法校验密码有效性
2. retrieveUser方法根据用户名获取用户
3. createSuccessAuthentication完成授权持久化

### 认证时执行的顺序
1. LindUserNameAuthenticationFilter
1. LindAuthenticationProvider.retrieveUser
1. LindAuthenticationProvider.additionalAuthenticationChecks
1. UserDetailsService
1. Authentication

springSecurity源码：https://github.com/spring-projects/spring-security