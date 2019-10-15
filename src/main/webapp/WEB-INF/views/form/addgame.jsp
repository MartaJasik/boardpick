<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick - add a game</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid" style="margin-top:30px">

    <h3>Look for a game to add!</h3>

    <form method="post">
        <div class="form-row align-items-center">
            <div class="col-sm-3 my-1">
                <input type="text" class="form-control" id="inlineFormInputName" placeholder="Title" name="title">
            </div>
            <div class="col-auto my-1">
                <input type="submit" value="Submit" class="btn btn-outline-light">
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
    </form>


        <h4> Found: </h4>
    <table class="table table-hover">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Cover</th>
            <th scope="col">Name</th>
            <th scope="col">Published</th>

            <th></th>

        </tr>

        <c:forEach var="game" items="${games}">
        <tr>
            <td>${game.id}</td>
            <td><a href="https://boardgamegeek.com/boardgame/${game.id}"><img height="100px" src="${game.cover}"></a></td>
            <td>${game.name}</td>
            <td>${game.published}</td>
            <td><a class="btn btn-primary btn-sm" href="/collection/add/${game.id}">Dodaj do kolekcji</a></td>
        </tr>
        </c:forEach>

        </table>
</div>
<jsp:include page="../footer.jsp"/>

</body>
</html>



