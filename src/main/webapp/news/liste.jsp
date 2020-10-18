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
        
        <p><a href="news?action=1">Nouvelle News</a></p>
        <table>
            <c:forEach var="news" items="${requestScope.listeNews}">
            <tr>
            <td><h2>${news.titre}</h2></td>
            </tr>
            <tr>
                <td>
                    <span>${news.datePub}</span>
                    <span>${news.theme.libelle}</span>
                </td>
            </tr>
             <tr>
                <td>${news.content}</td>
            </tr>
            <tr>
                <td>
                    <c:forEach var="tag" items="${news.tags}">
                    <span>${tag.idTag}</span>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>
                    <span><a href="news?id=${news.idNews}&action=2">Modifier</a></span>&nbsp;&nbsp;
                    <span><a href="news?id=${news.idNews}$action=3">Supprimer</a></span>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
