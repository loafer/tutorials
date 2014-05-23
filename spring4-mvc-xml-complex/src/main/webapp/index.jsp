<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <h3>Spring-mvc-xml-complex index.</h3>
        <div>
            hello: <spring:message code="hello.test" />
        </div>
        <div>
            user: <spring:message code="user.test"/>
        </div>
    </body>
</html>