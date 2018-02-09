##### 一、权限
- RBAC模型：（Role-Based Access Control）基于角色的权限控制
用户-角色-权限，都适用。
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/rbac-1.png?raw=true)
- 符合以下三个原则：
	- 1、最小权限原则。
	- 2、责任分离原则。
	- 3、数据抽象。
##### 二、shiro
- shiro介绍：认证、授权、会话管理、加密（web安全四大机制）
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-1.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-2.png?raw=true)
- 架构图：
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-3.png?raw=true)
- 身份认证
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-4.png?raw=true)
- 授权
WildcardPermission.java AuthorizationInfo.java
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-5.png?raw=true)
- 权限拦截
AdviceFilter.java PathMatchingFilter.java AccessControlFilter.java 
ProxiedFilterChain.java(这个类使得所有的servlet过滤器都在shiro的过滤器执行之后才执行)

![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-6.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-7.png?raw=true)
ProxiedFilterChain.java源码如下：
```java
public class ProxiedFilterChain implements FilterChain {

    //TODO - complete JavaDoc

    private static final Logger log = LoggerFactory.getLogger(ProxiedFilterChain.class);

    private FilterChain orig;
    private List<Filter> filters;
    private int index = 0;

    public ProxiedFilterChain(FilterChain orig, List<Filter> filters) {
        if (orig == null) {
            throw new NullPointerException("original FilterChain cannot be null.");
        }
        this.orig = orig;
        this.filters = filters;
        this.index = 0;
    }

    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (this.filters == null || this.filters.size() == this.index) {
            //we've reached the end of the wrapped chain, so invoke the original one:
            if (log.isTraceEnabled()) {
                log.trace("Invoking original filter chain.");
            }
            this.orig.doFilter(request, response);
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Invoking wrapped filter at index [" + this.index + "]");
            }
            this.filters.get(this.index++).doFilter(request, response, this);
        }
    }
}
``` 
- 回话管理
shiro的回话管理不依赖于web容器，可以实现单点登录
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-8.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-9.png?raw=true)
- 权限缓存
Cache.java CacheManager.java
做了realm的缓存
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-8.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-10.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-11.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-12.png?raw=true)