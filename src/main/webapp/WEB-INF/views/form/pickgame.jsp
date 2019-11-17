<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick - add a game</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container-fluid" style="margin-top:30px">

    <h3>Look for a game to play!</h3>
<br>
    <form method="post">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="category">I wanna</label>
                    <select id="category" name="category" class="form-control">
                        <sec:authorize access="isAuthenticated()">
                            <option selected value="mine">Play something I own</option>
                            <option value="new">Find a new game</option>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <option selected value="new">Find a new game</option>
                            <option disabled value="mine">Play something I own (login first!)</option>
                        </sec:authorize>

                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="players">Players</label>
                    <select id="players" name="players" class="form-control">
                        <option value="1">1</option>
                        <option selected value=2>2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">>6</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="weight">Weight</label>
                    <select id="weight" name="weight" class="form-control">
                        <option value="easy">Easy</option>
                        <option selected value="easymedium">Easy to Medium</option>
                        <option value="medium">Medium</option>
                        <option value="mediumhard">Medium to Hard</option>
                        <option value="hard">Hard</option>
                        <option value="dontcare">I dont't care</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="time">Time</label>
                    <select id="time" name="time" class="form-control">
                        <option selected value="quick">Quick (<30min)</option>
                        <option value="quickstandard">Quick-Standard (<60min)</option>
                        <option value="standard">Standard (30-60min)</option>
                        <option value="long">Long (>1h)</option>
                        <option value="dontcare">I dont't care</option>
                    </select>
                </div>
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type="submit" class="btn btn-light" value="Show me the games!">

    </form>
<br>

        <h4> Found: </h4>
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
        <c:forEach var="game" items="${games}">
            <tr>
                <td><a target="_blank" href="https://boardgamegeek.com/boardgame/${game.id}"><img height="100px" src="${game.cover}"></td>
                <td>${game.name}</td>
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
                <td><a class="btn btn-primary btn-sm" href="/collection/add/${game.id}">Dodaj do kolekcji</a></td>

        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp"/>

</body>
</html>



