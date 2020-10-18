<%-- 
    Document   : modify
    Created on : 9 oct. 2020, 11:26:18
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="tags" method="post">
                <input type=""hidden" name="action" value="${requestScope.action}"/>
                <input type=""hidden" name="id" value="${requestScope.tag.idTag}"/>
                <p><input type="text" name="libelle"  value=""${requestScope.tag.libelle}/></p>
                <p><input type="submit" name="OK !" /></p>
            </form>
        </div>
    </body>
</html>