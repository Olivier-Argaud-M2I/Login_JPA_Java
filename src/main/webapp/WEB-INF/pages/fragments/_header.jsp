<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: olivi
  Date: 27/07/2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="head">



    <c:choose>
        <c:when test="${userIsLogged}">
            <form action="<c:url value="/home?log=logout"/>" method="post">
                <input type="submit" value="logout">
            </form>
        </c:when>
    </c:choose>



    <a href="<c:url value="/home?page=home"/>">Accueil</a>
    <a href="<c:url value="/about?page=about"/>">About</a>
    <a href="<c:url value="/contact?page=contact"/>">Contact</a>
    <a href="<c:url value="/login?page=login"/>">Login</a>

    <c:choose>
        <c:when test="${userIsLogged}">
            <a href="<c:url value="/user"/>">Admin</a>
        </c:when>
    </c:choose>


</div>