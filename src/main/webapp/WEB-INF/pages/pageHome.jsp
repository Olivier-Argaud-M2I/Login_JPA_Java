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
      <title>Home</title>
  </head>
  <body>

  <c:choose>
      <c:when test="${userIsLogged}">
          <form action="<c:url value="/home?log=logout"/>" method="post">
              <input type="submit" value="logout">
          </form>
          <form action="<c:url value="/user"/> ">
              <input type="submit" value="Admin">
          </form>
      </c:when>
      <c:otherwise>
          <fieldset>
              <legend>Login</legend>
              <form action="<c:url value="/user"/>" method="POST">
                  <input type="text" name="username" placeholder="Username"/>
                  <input type="text" name="password" placeholder="Password">
                  <input type="submit" value="valider" >
              </form>
          </fieldset>
      </c:otherwise>
  </c:choose>




    <c:forEach items="${news}" var="elem">
        <fieldset>
            <p>titre : ${elem.titre}</p>
            <p>id : ${elem.text}</p>
        </fieldset>
    </c:forEach>

  </body>
</html>
