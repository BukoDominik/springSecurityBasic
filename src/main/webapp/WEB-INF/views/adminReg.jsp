<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<%--@elvariable id="user" type="pl.coderslab.model.UserName"--%>
<form:form method="post" modelAttribute="user">
    <form:input path="username" name="username" value="${user.username}"/>
    <form:input path="password" name="password" value="${user.password}"/>
    <button type="submit" class="btn btn-primary pull-right">Zarejestruj</button>
</form:form>
</body>
</html>