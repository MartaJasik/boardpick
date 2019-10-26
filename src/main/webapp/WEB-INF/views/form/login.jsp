<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>


    <form th:th:action="@{/login}" method="post" class="text-center">
        <div class="form-group row">

            <label for="username" class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-4">
                <input type="text" id="username" name="username" class="form-control" placeholder="Username"/></div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-4">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password"/></div>
        </div>
        <div class="form-group row">
            <div class="col-sm-8">
               <button type="submit" class="btn btn-light">Sign in</button>
            </div>

        </div>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
<div class="col-sm-3">
</div>

    <jsp:include page="../footer.jsp"/>

</body>
</html>



