<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: olivi
  Date: 02/08/2022
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>User</title>
    </head>
    <body>
        <form action="<c:url value="/home?log=logout"/>" method="post">
            <input type="submit" value="logout">
        </form>
        <form action="<c:url value="/home"/>" method="get">
            <input type="submit" value="Home">
        </form>

        <fieldset>
          <legend>New News</legend>
          <form action="<c:url value="/user?type=createNews"/>" method="POST">
              <input type="text" name="titre" placeholder="Titre"/>
              <input type="text" name="text" placeholder="Text">
              <input type="submit" value="valider" >
          </form>
        </fieldset>

      <c:forEach items="${news}" var="news">
          <fieldset>
              <p>titre : ${news.titre}</p>
              <p>id : ${news.text}</p>
              <form action="<c:url value="/user?type=deleteNews&id=${news.id}"/>" method="POST">
                  <input type="submit" value="Supprimer">
              </form>
          </fieldset>
      </c:forEach>

    <fieldset>
      <legend>New User</legend>
      <form action="<c:url value="/user?type=createUser"/>" method="POST">
          <input type="text" name="username" placeholder="Username"/>
          <input type="text" name="password" placeholder="Password">
          <input type="submit" value="valider" >
      </form>
    </fieldset>


    <c:forEach items="${users}" var="user">
        <fieldset>
            <p>titre : ${user.username}</p>
            <p>id : ${user.password}</p>
            <form action="<c:url value="/user?type=deleteUser&id=${user.id}"/>" method="POST">
                <input type="submit" value="Supprimer">
            </form>
        </fieldset>
    </c:forEach>

  </body>
</html>
