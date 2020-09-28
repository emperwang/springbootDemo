oauth2 授权模式:
1. 授权码模式(Authorization code)
2. 密码模式(Resource Owner Password Credentials)
3. 简化模式(Implicit)
4. 客户端模式(Client Credentials)

此模块是 密码模式.

对应流程图步骤如下:
A   用户向客户端提供用户名和密码
B   客户端将用户名和密码发送给授权服务器,向授权服务器请求令牌
C   授权服务器确认无误之后,向客户端提供访问令牌