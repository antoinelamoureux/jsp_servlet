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
                <input type=""hidden" name="id" value="${requestScope.news.idNews}"/>
                <p><input type="text" name="libelle"  value=""${requestScope.news.titre}/></p>
                <p><input type="text" name="libelle"  value=""${requestScope.news.content}/></p>
                 <select name="theme" id="theme-select">
                    <option value="">--Please choose an option--</option>
                    <c:forEach var="theme" items="${requestScope.listeThemes}">
                    <option value="${theme.idTheme}">${theme.libelle}</option>
                    </c:forEach>
                </select>
                <select name="tags" id="theme-select">
                    <option value="">--Please choose an option--</option>
                    <c:forEach var="tag" items="${requestScope.listeTags}">
                    <option value="${tag.idTag}">${tag.libelle}</option>
                    </c:forEach>
                </select>
                <p><input type="submit" name="OK !" /></p>
            </form>
        </div>
    </body>
</html>