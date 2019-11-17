<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Collection</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid" style="margin-top:30px">

    <h3>${user.username}'s collection</h3>

<br>
   <br>

    <table id="table" data-toggle="table" class="table table-hover borderless">
        <thead>
        <tr>
            <th scope="col" data-field="cover" data-sortable="false">Cover</th>
            <th scope="col" data-field="name" data-sortable="true">Name</th>
            <th scope="col" data-field="published" data-sortable="true">Published</th>
            <th scope="col" data-field="players" data-sortable="true">Players</th>
            <th scope="col" data-field="playingtime" data-sortable="true">Playing time</th>
            <th scope="col" data-field="rating" data-sortable="true">Rating</th>
            <th scope="col" data-field="ranking" data-sortable="true">Ranking</th>
            <th scope="col" data-field="weight" data-sortable="true">Weight</th>
            <th></th>

        </tr>
        </thead>

        <tbody>
        <c:forEach var="usersGame" items="${userGames}">
            <tr>
                <td><a target="_blank" href="https://boardgamegeek.com/boardgame/${usersGame.id}"><img height="100px"
                                                                                    src="${usersGame.cover}"></td>
                <td>${usersGame.name}</td>
                <td>${usersGame.published}</td>
                <td><c:choose>
                    <c:when test="${usersGame.minPlayers eq usersGame.maxPlayers}">
                        ${usersGame.minPlayers}
                    </c:when>
                    <c:otherwise>
                        ${usersGame.minPlayers}-${usersGame.maxPlayers}
                    </c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${usersGame.minPlaytime eq usersGame.maxPlaytime}">
                        ${usersGame.minPlaytime} min
                    </c:when>
                    <c:otherwise>
                        ${usersGame.minPlaytime}-${usersGame.maxPlaytime} min
                    </c:otherwise>
                </c:choose></td>
                <td>${usersGame.rating}</td>
                <td>${usersGame.ranking}</td>
                <td>${usersGame.weight}/5</td>
                <td><a class="btn btn-success btn-sm" href="/collection/add/${usersGame.id}">I have it too!</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>



