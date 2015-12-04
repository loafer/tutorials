<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <body>
        <form method="post" action="j_spring_security_check">
            <div>
                <label>User:</label>
                <input type="text" name="j_username">
            </div>
            <div>
                <label>Password:</label>
                <input type="text" name="j_password">
            </div>
            <div>
                <input type="submit" value="login">
            </div>
        </form>
    </body>
</html>
