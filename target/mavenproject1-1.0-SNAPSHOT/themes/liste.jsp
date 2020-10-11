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
        
        <h1>Pas de java dans les pages JSP, on utilise utilise EL et JSTL</h1>
        <p>${2*2}</p>
        <p><a href="themes?action=1">Nouveau Theme</a></p>
        <table>
            <tr>
                <th>Libellé</th>
            </tr>
            <c:forEach var="theme" items="${requestScope.listeThemes}">
            <tr>
                <td>${theme.libelle}</td>
                    <td><a href="themes?id=${theme.idTheme}&action=2">Modifier</<td>
                    <td><a href="themes?id=${theme.idTheme}$action=3">Supprimer</a></<td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
