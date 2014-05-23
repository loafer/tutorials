<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title></title>
    </head>
    <body>
        <form method="post" action="user">
            <input type="hidden" name="id" value="${user.id}">
            <div>
                <label>Name</label>
                <div>
                    <input type="text" name="name" value="${user.name}">
                </div>
            </div>
            <div>
                <label>Age</label>
                <div>
                    <input type="text" name="age" value="${user.age}">
                </div>
            </div>
            <div>
                <input type="submit" value="submit">
            </div>
        </form>
    </body>
</html>
