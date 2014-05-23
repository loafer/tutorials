<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title></title>
    </head>
    <body>
        <h3>welcome, user list!</h3>
        <div>
            <a href="user/new">New</a>
            <form>
                Search Age:
                <input type="text" name="age">
                <input type="submit" value="find">
            </form>
        </div>
        <table style="width: 500px;" border="1">
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Operation</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <a href="user/${user.id}">Edit</a> |
                        <a href="javascript:;" onclick="doDelete('${user.id}')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <script src="js/vender/jquery-1.10.2.min.js"></script>
        <script>
            function doDelete(userid){
                $.ajax({
                    type: 'DELETE',
                    url: 'user/' + userid,
                    success: function(data){
                        window.location.reload();
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        alert('delete failure!: ' + errorThrown);
                    }
                })
            }
        </script>
    </body>
</html>
