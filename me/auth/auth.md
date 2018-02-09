##### һ��Ȩ��
- RBACģ�ͣ���Role-Based Access Control�����ڽ�ɫ��Ȩ�޿���
�û�-��ɫ-Ȩ�ޣ������á�
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/rbac-1.png?raw=true)
- ������������ԭ��
	- 1����СȨ��ԭ��
	- 2�����η���ԭ��
	- 3�����ݳ���
##### ����shiro
- shiro���ܣ���֤����Ȩ���Ự�������ܣ�web��ȫ�Ĵ���ƣ�
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-1.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-2.png?raw=true)
- �ܹ�ͼ��
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-3.png?raw=true)
- �����֤
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-4.png?raw=true)
- ��Ȩ
WildcardPermission.java AuthorizationInfo.java
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-5.png?raw=true)
- Ȩ������
AdviceFilter.java PathMatchingFilter.java AccessControlFilter.java 
ProxiedFilterChain.java(�����ʹ�����е�servlet����������shiro�Ĺ�����ִ��֮���ִ��)

![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-6.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-7.png?raw=true)
ProxiedFilterChain.javaԴ�����£�
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
- �ػ�����
shiro�Ļػ�����������web����������ʵ�ֵ����¼
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-8.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-9.png?raw=true)
- Ȩ�޻���
Cache.java CacheManager.java
����realm�Ļ���
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-8.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-10.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-11.png?raw=true)
![avatar](https://github.com/szfst/interview-learn/blob/master/me/auth/img/shiro-12.png?raw=true)