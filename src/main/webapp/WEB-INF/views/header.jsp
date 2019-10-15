<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

<style>
body { background: linear-gradient(to right, purple , hotpink) !important; color: white;}
table td {
    color: floralwhite;
}
table th {
    color:white;
}
</style>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: purple;">

    <a class="navbar-brand" href="/"><img src="https://i.imgur.com/Mba1Y3V.png" height="80"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">My collection</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/orders">Pick a game</a>
            </li>
            <li class="nav-item">
            <form th:action="@{/logout}" method="post">
                <input type="submit" value="Wyloguj"/>
            </form>
            </li>
        </ul>
    </div>
</nav>