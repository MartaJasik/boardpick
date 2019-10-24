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
        <form>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="category">I wanna</label>
                    <select id="category" class="form-control">
                        <sec:authorize access="isAuthenticated()">
                            <option selected>Play something I own</option>
                            <option>Find a new game</option>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <option selected>Find a new game</option>
                            <option disabled>Play something I own</option>
                        </sec:authorize>

                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="players">Players</label>
                    <select id="players" class="form-control">
                        <option>1</option>
                        <option selected>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>>6</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="weight">Weight</label>
                    <select id="weight" class="form-control">
                        <option>Easy</option>
                        <option selected>Easy to Medium</option>
                        <option>Medium</option>
                        <option>Medium to Hardcore</option>
                        <option>Hardcore</option>
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label for="time">Time</label>
                    <select id="time" class="form-control">
                        <option selected>Quick (<30min)</option>
                        <option>Quick-Standard (<60min)</option>
                        <option>Standard (30-60min)</option>
                        <option>Long (>1h)</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn btn-light">Show me the games!</button>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
    </form>
<br>

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



