<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <h3>Hello ${name}!</h3>
        <script src="../../../js/vender/jquery-1.10.2.min.js"></script>
        <script>
            $(function(){
                alert('Hello World!');
            });
        </script>
    </body>
</html>
