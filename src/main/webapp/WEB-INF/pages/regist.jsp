<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/7/29
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<form action="doReg" method="post">
    用户名：<input type="text" name="username"/><br/>
    <p></p>
    密码：<input type="password" name="password"/><br/>
    <p></p>
    年龄：<input type="text" name="age"/><br/>
    <p></p>
    手机号码：<input type="text" name="phone"/><br/>
    <p></p>
    电子邮箱：<input type="text" name="email"/><br/>

    <input type="submit" value="提交">
</form>

</body>
</html>
