<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Collection</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid" style="margin-top:30px">

    <h3>My collection <a class="btn btn-success" href="/collection/addgame">Add a game</a></h3> Last update: xxx
    <table class="table table-hover">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Cover</th>
            <th scope="col">Name</th>
            <th scope="col">Published</th>
            <th scope="col">Players</th>
            <th scope="col">Playing time</th>
            <th scope="col">Rating</th>
            <th scope="col">Ranking</th>
            <th scope="col">Weight</th>
            <th></th>

        </tr>

        <c:forEach var="myGame" items="${mygames}">
            <tr>

                <td>${myGame.id}</td>
                <td><a href="https://boardgamegeek.com/boardgame/${myGame.id}"><img height="100px" src="${myGame.cover}"></td>
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
                <td><a class="btn btn-danger btn-sm" onclick="return confirm('Are you sure to delete ${myGame.name} from your collection?')"
                        href="/collection/delete/${myGame.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>



