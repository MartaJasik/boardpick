<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>


<form:form method="post" action="/register" modelAttribute="user">
    <div class="form-group row">

    <label for="username" class="col-sm-2 col-form-label"> User Name : </label>
    <div class="col-sm-4">
    <form:input path="username" id="username" name="username" class="form-control" placeholder="Username"/><form:errors
        path="username"/></div>
    </div>
    <div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label"> Password: </label>
    <div class="col-sm-4">
    <form:password path="password" id="password" name="password" class="form-control" placeholder="Password"/><form:errors
        path="password"/></div>
    </div>
    <div class="form-group row">
    <div class="col-sm-8">
    <button type="submit" class="btn btn-light">Register</button>
    </div>

    </div>
    <input type="hidden"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"/>
</form:form>
    <div class="col-sm-3">
    </div>

    <jsp:include page="../footer.jsp"/>

    </body>
    </html>



