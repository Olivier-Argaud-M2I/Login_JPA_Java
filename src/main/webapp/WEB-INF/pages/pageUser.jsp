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

    <jsp:include page="./fragments/_header.jsp"/>


<%--        <form action="<c:url value="/home?log=logout"/>" method="post">--%>
<%--            <input type="submit" value="logout">--%>
<%--        </form>--%>
<%--        <form action="<c:url value="/home"/>" method="get">--%>
<%--            <input type="submit" value="Home">--%>
<%--        </form>--%>

        <fieldset>
          <legend>New News</legend>
          <form action="<c:url value="/user?type=createNews"/>" method="POST">
              <input type="text" id="titre" name="titre" placeholder="Titre" required>
              <input type="text" id="text" name="text" placeholder="Text" required>
              <input type="number" id="idNews" name="id" hidden>
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
              <button onclick="updateNews('${news.id}','${news.titre}','${news.text}')">Modifier</button>
              <button onclick="test()">test</button>
          </fieldset>
      </c:forEach>

    <fieldset>
      <legend>New User</legend>
      <form action="<c:url value="/user?type=createUser"/>" method="POST">
          <input type="text" id="username" name="username" placeholder="Username" required/>
          <input type="text" id="password" name="password" placeholder="Password" required>
          <input type="number" id="idUser" name="id" hidden>
          <input type="submit" value="valider" >
      </form>
    </fieldset>


    <c:forEach items="${users}" var="user">
        <fieldset>
            <p>titre : ${user.username}</p>
            <p>password : ${user.password}</p>
            <form action="<c:url value="/user?type=deleteUser&id=${user.id}"/>" method="POST">
                <input type="submit" value="Supprimer">
            </form>
            <button onclick="updateUser('${user.id}','${user.username}')">Modifier</button>
        </fieldset>
    </c:forEach>

    <script>
        function updateUser(id,username){
            document.getElementById("idUser").value = id;
            document.getElementById("username").value = username;
            document.getElementById("password").value = "";
        }

        function updateNews(id,titre,text){
            document.getElementById("idNews").value = id;
            document.getElementById("titre").value = titre;
            document.getElementById("text").value = text;
        }

        function test(){
            console.log("test");
        }
    </script>

  </body>
</html>
