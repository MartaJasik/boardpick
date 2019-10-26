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
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>

    <style>
        body {
            background: linear-gradient(to right, #0E0810, #370047) !important;
            color: #E9E8E9;
        }



        table td {
            color: #E9E8E9;
        }

        table th {
            color: #E9E8E9;
        }

        .borderless td, .borderless th {
            border-style: hidden !important;

        }

        .borderless tr {
            border: solid !important;
            border: 1px !important;

        }


    </style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: purple;">
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="/"><img src="https://i.imgur.com/Mba1Y3V.png" height="80"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/collection">
                    <button type="button" class="btn btn-outline-light">My collection</button>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/plays">
                    <button type="button" class="btn btn-outline-light">My plays</button>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/picker">
                    <button type="button" class="btn btn-outline-light">Pick a game</button>
                </a>
            </li>

        </ul>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="/login">
                        <button type="button" class="btn btn-outline-secondary">Login</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">
                        <button type="button" class="btn btn-outline-secondary">Register</button>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">
                        <button type="button" class="btn btn-outline-secondary">Logout</button>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<div class="row text-center">
    <div class="col-sm-2 text-center">
    </div>
    <div class="col-sm-8 text-center">

        <br>
