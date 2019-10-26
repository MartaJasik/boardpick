<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Collection</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid" style="margin-top:30px">
    <a href="https://boardgamegeek.com/boardgame/${game.id}"><img height="200px"
                                                                  src="${game.cover}"></a>
    <h3>${game.name}</h3>
    <p>of ${count} games</p>

<br>
   <br>

    <table id="table" data-toggle="table" class="table table-hover borderless">
        <thead>
        <tr>
            <th scope="col" data-field="published">Published</th>
            <th scope="col" data-field="players">Players</th>
            <th scope="col" data-field="playingtime">Playing time</th>
            <th scope="col" data-field="rating">Rating</th>
            <th scope="col" data-field="ranking">Ranking</th>
            <th scope="col" data-field="weight">Weight</th>

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

            </tr>
        </tbody>
    </table>

    <table id="tableSec" data-toggle="table" class="table table-hover borderless">
        <thead>
        <tr>
            <th scope="col" data-field="owned">Owned by</th>
            <th scope="col" data-field="players">No. of plays</th>
            <th scope="col" data-field="bestplayer">Best player</th>


        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:forEach var="user" items="${game.users}">${user.username}<br></c:forEach></td>
            <td>${playscount}</td>
            <td>${game.weight}/5</td>

        </tr>
        </tbody>
    </table>

</div>
<jsp:include page="footer.jsp"/>

</body>
</html>



