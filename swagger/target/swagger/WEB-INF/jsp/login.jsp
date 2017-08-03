<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>登陆</title>
</head>
<body>
<c:if test="${!empty error}">
    <c:out value="${error}"/>
</c:if>
<form action="/loginCheck.html" method="post">
    用户名:
    <input type="text" name="userName" />
    <br/>
    密码:
    <input type="password" name="password" />
    <input type="submit" value="登陆"/>
    <input type="reset" value="重置"/>
</form>
</body>
</html>