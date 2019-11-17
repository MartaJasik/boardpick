<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Collection</title>
    <style>
    .leaderboard tr:nth-child(even) td {
        background-color: grey;
    }
    .leaderboard tr:nth-child(odd) td {
        background-color: saddlebrown;
    }
    .leaderboard tr:first-child td {
        background-color: darkgoldenrod;
    }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid justify-content-center" style="margin-top:30px">

    <div class="row align-items-center h-100">
        <div class="col-4 mx-auto">
            <a target="_blank" href="https://boardgamegeek.com/boardgame/${game.id}"><img height="400px"
                                                                          src="${game.cover}"></a>
        </div>
        <div class="col-8" class="vertical-center">
            <h1 class="vertical-center">${game.name}</h1>
            <div class="col-4 mx-auto">
                <br>
                <br>
                <h5>Leaderboard:</h5>
                <table id="leaderboard" data-toggle="table" class="table table-hover borderless leaderboard">
                    <thead>
                    <tr>
                        <th scope="col" data-field="username">User</th>
                        <th scope="col" data-field="count" data-sort-order="desc">Wins</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="win" items="${wins}">
                        <tr>
                            <td>${win.key.username}</td>
                            <td>${win.value}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="row align-items-center h-100">

        <div class="col-12 mx-auto">
            <table id="table" data-toggle="table" class="table table-hover borderless">
                <thead>
                <tr>
                    <th scope="col" data-field="published">Published</th>
                    <th scope="col" data-field="players">Players</th>
                    <th scope="col" data-field="playingtime">Playing time</th>
                    <th scope="col" data-field="rating">Rating</th>
                    <th scope="col" data-field="ranking">Ranking</th>
                    <th scope="col" data-field="weight">Weight</th>
                    <th scope="col" data-field="owned">Owned by</th>

                </tr>
                </thead>
                <tbody>
                <tr>

                    <td>${game.published}</td>
                    <td><c:choose>
                        <c:when test="${game.minPlayers eq game.maxPlayers}">
                            ${game.minPlayers}
                        </c:when>
                        <c:otherwise>
                            ${game.minPlayers}-${game.maxPlayers}
                        </c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${game.minPlaytime eq game.maxPlaytime}">
                            ${game.minPlaytime} min
                        </c:when>
                        <c:otherwise>
                            ${game.minPlaytime}-${game.maxPlaytime} min
                        </c:otherwise>
                    </c:choose></td>
                    <td>${game.rating}</td>
                    <td>${game.ranking}</td>
                    <td>${game.weight}/5</td>
                    <td><c:forEach var="user" items="${game.users}">${user.username}<br></c:forEach></td>

                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <br>
    <div class="row align-items-center h-100">
        <div class="col-12 mx-auto">
<br>
            <h4> ${playscount} plays in total: </h4>
            <br>
            <table id="table2" data-toggle="table" class="table table-hover borderless">
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
                <c:forEach var="play" items="${plays}">
                    <tr>
                        <td>${play.id}</td>
                        <td>${play.game.name}</td>
                        <td>${play.date}</td>
                        <td><c:forEach var="player" items="${play.players}">${player.username} </c:forEach></td>
                        <td>${play.winner.username}</td>
                        <td><a class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure to delete ID${play.id} play from your plays?')"
                               href="/plays/delete/${play.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>


</div>

</div>
<jsp:include page="footer.jsp"/>

</body>
</html>



