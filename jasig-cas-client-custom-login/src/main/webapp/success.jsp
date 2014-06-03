<%--
  Created by IntelliJ IDEA.
  User: zhaojh
  Date: 2014/5/30
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <title></title>
    </head>
    <body>
        <h3>Welcome !!!</h3>
        <div>
            <a href="http://localhost:8080/cas/logout?service=http://localhost:8084/cas-client-custom-login">logout</a>
        </div>
    </body>
</html>
