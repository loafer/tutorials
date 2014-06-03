<%--
  Created by IntelliJ IDEA.
  User: zhaojh
  Date: 2014/5/30
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <!-- login form -->
        <form id="loginForm" method="post" action="">
            <input type="hidden" name="_eventId" value="submit">
            <input type="hidden" name="execution" id="execution">
            <input type="hidden" name="lt" id="lt">
            username: <input type="text" name="username">
            password: <input type="password" name="password">
            <input type="submit" value="login">
        </form>

        <!-- error -->
        <div id="errMsg" style="display: none; color: red;">

        </div>

        <!-- content -->
        <div>
            Welcome!!
        </div>

        <script src="js/jquery-1.11.1.min.js"></script>
        <script>
            var cas = (function(){
                var loginTicket,
                    execution = null,
                    error = null,
                    casLoginURL = '<spring:message code="cas.login.url" />',
                    thisPageURL = '<%=basePath%>',
                    serviceURL = encodeURIComponent(thisPageURL + '<spring:message code="cas.login.success.goto.location" />');

                var prepareLoginForm = function(){
                    $('#loginForm').attr('action', casLoginURL);
                    $("#lt").val(loginTicket);
                    $("#execution").val(execution);
                },
                checkForLoginTicket = function(){
                    var loginTicketProvided = false;
                    var queryString = '', param = [], temp = [];

                    queryString   = window.location.search;
                    queryString   = queryString.substr (1);
                    param   = queryString.split ('&');

                    var i=0;
                    while(param[i]){
                        temp = param[i].split ('=');
                        if (temp[0] == 'lt') {
                            loginTicket = temp[1];
                            loginTicketProvided = true;
                        }

                        if (temp[0] == 'error_message') {
                            error = temp[1];
                            console.log(error)
                        }

                        if (temp[0] == 'execution') {
                            execution = temp[1];
                        }

                        if(temp[0] == 'service'){
                            serviceURL = temp[1];
                        }
                        i++;
                    }

                    casLoginURL += '?service=' + serviceURL + '&login-at=' + encodeURIComponent(thisPageURL);
                    if(!loginTicketProvided){
                        location.href = casLoginURL + '&get-lt=true';
                    }

                    showError();
                },
                showError = function(){
                    if(error){
                        error = decodeURIComponent (error);
                        $('#errMsg').show().text(error);
                    }
                }

                return {
                    prepareLoginForm: function(){onload = prepareLoginForm; return this;},
                    checkForLoginTicket: function(){checkForLoginTicket(); return this;}
                }
            })();

            cas.checkForLoginTicket().prepareLoginForm();
        </script>
    </body>
</html>
