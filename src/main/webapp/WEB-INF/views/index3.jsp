<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BoardPick Homepage</title>
<jsp:include page="header.jsp"/>
<div class="container" style="margin-top:30px">
<h3>Zlecenia w toku</h3>
<table class="table table-hover">
    <tr>
        <th scope="col">Status</th>
        <th scope="col">Samochód</th>
        <th scope="col">Pracownik</th>
        <th scope="col">Przyjęcie zlecenia</th>
        <th scope="col">Rozpoczęcie</th>
        <th scope="col">Problem</th>
        <th scope="col">Naprawa</th>
        <th scope="col">Koszt klienta</th>

    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.status}</td>
            <td>${order.vehicle}</td>
            <td>${order.employee}</td>
            <td>${order.przyjecie}</td>
            <td>${order.rozpoczecie}</td>
            <td>${order.problem}</td>
            <td>${order.naprawa}</td>
            <td>${order.kosztKlienta} PLN</td>
        </tr>
    </c:forEach>

</table>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>



