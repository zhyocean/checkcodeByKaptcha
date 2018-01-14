<%--
  Created by IntelliJ IDEA.
  User: 吾爱阳
  Date: 2018/1/14
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/submit" method="get">
    验证码:<input type="text" name="yzm"><img src="/pictureServlet"/><br/>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
