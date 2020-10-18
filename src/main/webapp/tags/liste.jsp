<%-- 
    Document   : liste
    Created on : 8 oct. 2020, 16:30:21
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
        
        <p><a href="tags?action=1">Nouveau Tag</a></p>
        <table>
            <tr>
                <th>Libellé</th>
            </tr>
            <c:forEach var="tag" items="${requestScope.listeTags}">
            <tr>
                <td>${tag.libelle}</td>
                    <td><a href="tags?id=${tag.idTag}&action=2">Modifier</<td>
                    <td><a href="tags?id=${tag.idTag}$action=3">Supprimer</a></<td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
