<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick - add a game</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid justify-content-center" style="margin-top:30px">

    <h3>Add a play!</h3>

    <form:form method="post" modelAttribute="play">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="date">Date</label>
                <form:input type="date" path="date" class="form-control" id="date"/>
            </div>
            <div class="form-group">
                <label for="game">Game</label>
                <form:select class="form-control" path="game" id="game">
                    <c:forEach var="myGame" items="${mygames}">
                        <form:option value="${myGame.id}">${myGame.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label for="players">Players</label>
                <form:select multiple="true" class="form-control" path="players" id="players">
                    <c:forEach var="user" items="${users}">
                        <form:option value="${user.id}">${user.username}</form:option>
                    </c:forEach>

                </form:select>

            </div>
            <div class="form-group">

                <label for="winner">Winner</label>
                <form:select class="form-control" path="winner" id="winner">
                    <c:forEach var="user" items="${users}">
                        <form:option value="${user.id}">${user.username}</form:option>
                    </c:forEach>
                </form:select>

            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-group">

                <button type="submit" class="btn btn-light">Add</button>
            </div>
        </div>

    </form:form>


    <h4> Your plays: </h4>
    <p>${playscount} in total, including ${wincount} wins!</p>
    <table id="table" data-toggle="table" class="table table-hover borderless">
        <thead>
        <tr>
            <th scope="col" data-field="id" data-sortable="false">ID</th>
            <th scope="col" data-field="game" data-sortable="true">Game</th>
            <th scope="col" data-field="date" data-sortable="true">Date</th>
            <th scope="col" data-field="players" data-sortable="true">Players</th>
            <th scope="col" data-field="winner" data-sortable="true">Winner</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="play" items="${myplays}">
            <tr>
                <td>${play.id}</td>
                <td>${play.game.name}</td>
                <td>${play.date}</td>
                <td><c:forEach var="player" items="${play.players}">${player.username} </c:forEach></td>
                <td>${play.winner.username}</td>
                <td><a class="btn btn-success btn-sm"
                       href="/gamestats/${play.game.id}">Game stats</a>
                    <a class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure to delete ID${play.id} play from your plays?')"
                       href="/plays/delete/${play.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp"/>

</body>
</html>



