<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


    <style>
        body {
            background: linear-gradient(to right, purple, hotpink) !important;
            color: white;
        }

        table td {
            color: floralwhite;
        }

        table th {
            color: white;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: purple;">

    <a class="navbar-brand" href="/"><img src="https://i.imgur.com/Mba1Y3V.png" height="80"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">

                <a class="nav-item nav-link" href="/collection">
                    <button type="button" class="btn btn-outline-light">My collection</button>
                </a>

                <a class="nav-item nav-link" href="/picker">
                    <button type="button" class="btn btn-outline-light">Pick a game</button>
                </a>


            <sec:authorize access="!isAuthenticated()">

                    <a class="nav-item nav-link" href="/login">
                        <button type="button" class="btn btn-outline-secondary">Login</button>
                    </a>

                    <a class="nav-item nav-link" href="/register">
                        <button type="button" class="btn btn-outline-secondary">Register</button>
                    </a>

            </sec:authorize>
            <sec:authorize access="isAuthenticated()">

                    <a class="nav-link" href="/logout">
                        <button type="button" class="btn btn-outline-secondary">Logout</button>
                    </a>

            </sec:authorize>

        </div>
    </div>
</nav>
<div class="row text-center">
    <div class="col-sm-2 text-center">
    </div>
    <div class="col-sm-8 text-center">

        <br>
