<%-- 
    Document   : delete
    Created on : 9 oct. 2020, 11:47:10
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
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
                <p>Voulez-vous vraiment supprimer le thème ${request.new.titre} ?</p>
                <input type=""hidden" name="action" value="${requestScope.action}"/>
                <input type=""hidden" name="id" value="${requestScope.new.idNews}"/>
                <p><input type="submit" value="OK !" /><a href="/themes"><button>Cancel</button></a></p>
            </form>
        </div>
    </body>
</html>