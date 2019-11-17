<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Collection</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid" style="margin-top:30px">

    <h3>My collection</h3>
    <p>of ${count} games</p>
    <a class="btn btn-light" href="/collection/addgame">Add another!</a>

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
            <th></th>

        </tr>
        </thead>

        <tbody>
        <c:forEach var="myGame" items="${mygames}">
            <tr>
                <td><a target="_blank" href="https://boardgamegeek.com/boardgame/${myGame.id}"><img height="100px"
                                                                                    src="${myGame.cover}"></td>
                <td>${myGame.name}</td>
                <td>${myGame.published}</td>
                <td><c:choose>
                    <c:when test="${myGame.minPlayers eq myGame.maxPlayers}">
                        ${myGame.minPlayers}
                    </c:when>
                    <c:otherwise>
                        ${myGame.minPlayers}-${myGame.maxPlayers}
                    </c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${myGame.minPlaytime eq myGame.maxPlaytime}">
                        ${myGame.minPlaytime} min
                    </c:when>
                    <c:otherwise>
                        ${myGame.minPlaytime}-${myGame.maxPlaytime} min
                    </c:otherwise>
                </c:choose></td>
                <td>${myGame.rating}</td>
                <td>${myGame.ranking}</td>
                <td>${myGame.weight}/5</td>
                <td><a class="btn btn-success btn-sm"
                       href="/gamestats/${myGame.id}">Game stats</a>
                </td>
                <td><a class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure to delete ${myGame.name} from your collection?')"
                       href="/collection/delete/${myGame.id}">Delete</a>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>



