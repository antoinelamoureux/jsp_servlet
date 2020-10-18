<%-- 
    Document   : create
    Created on : 9 oct. 2020, 09:59:38
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:include page="../haut.jsp"/>
        <jsp:include page="../navigation.jsp"/>   
        
        <div id=""main">
            <form action="themes" method="post">
                <input type="hidden" name="action" value="${requestScope.action}"/>
                <p><input type="text" name="libelle" /></p>
                <p><input type="button" value="OK !" /></p>
            </form>
        </div>
    </body>
</html>