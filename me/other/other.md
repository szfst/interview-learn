### 一、http状态码：
- Http协议的响应消息：由三部分组成：状态行、消息头、响应正文
- 响应码种类：
	- 1XX：提示信息。表示请求已经接收继续处理
	- 2XX：成功，表示请求已经接收成功。
	- 3XX：重定向。要完成的请求必须经过进一步的操作。
	- 4XX：客户端错误。可能是请求语法错误或者请求无法实现。
	- 5XX：服务端错误。服务器未能处理请求（可能内部出现异常）
- 常见响应状态码：
	- 200 0K：表示成功
	- 400 Bad Request：错误的请求语法，不能被服务器理解。
	- 401 Unauthorized：请求未经授权。
	- 403 Forbidden：服务器收到请求，但请求被服务器拒绝。
	- 404 Not Found：请求的资源不存在。
	- 405 Method Not allowed：请求方式不被允许，如只支持get请求，但客户端使用了post。
	- 500 Internal Server Error：服务器发送不可预期的错误。
	- 503 Server Unavailable：服务器当前不能处理客户端请求。一段时间后可能恢复正常。
