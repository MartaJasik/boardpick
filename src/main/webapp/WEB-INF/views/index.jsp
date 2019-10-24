<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Homepage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: linear-gradient(to right, purple, hotpink) !important;
        }

        .img-responsive {
            width: auto;
            height: 200px;
            -webkit-margin-after: 10px;
            -webkit-margin-end: 10px;
        }
        .image-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="row text-center">
    <div class="col-sm-2 text-center">
    </div>
    <div class="col-sm-8 text-center">
        <img src="https://i.imgur.com/Mba1Y3V.png" width="40%" class="rounded mx-auto d-block">
        <div class="d-flex justify-content-center">
            <a class="nav-link" href="/collection">
                <button type="button" class="btn btn-outline-light">My collection</button>
            </a>
            <br>
            <a class="nav-link" href="/picker">
                <button type="button" class="btn btn-outline-light">Choose a game</button>
            </a>
        </div>
        <br><br>
        <h5>Recently added</h5>
        <br>
        <div class="row image-container">
            <c:forEach var="recentGame" items="${recentGames}">

                <div class="thumbnail">
                    <a href="https://boardgamegeek.com/boardgame/${recentGame.id}">
                        <img src="${recentGame.cover}" alt="${recentGame.name}" class="img-responsive">
                        </a>
                </div>
            </c:forEach>
        </div>
        </div>
        <div class="col-sm-2 text-center">
        </div>
        </div>
</body>
</html>
