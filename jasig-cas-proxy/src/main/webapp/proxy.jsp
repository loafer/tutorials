<%@ page import="org.jasig.cas.client.validation.Assertion" %>
<%@ page import="org.jasig.cas.client.util.AbstractCasFilter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <%
            Assertion assertion = (Assertion)session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
            String proxyTicket = assertion.getPrincipal().getProxyTicketFor("http://msoffice/");
            out.write("proxy Ticket: " + proxyTicket);
        %>
    </body>
</html>
