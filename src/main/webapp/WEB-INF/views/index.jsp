<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-18
  Time: 오전 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="../../resources/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
</head>
<body>
<%@include file="componnet/header.jsp"%>
<%@include file="componnet/nav.jsp"%>
<a href="/member/save">회원가입</a>
<a href="/member/login">로그인</a>
<a href="/product/Productsave">상품등록</a>
<a href="/order/cart">장바구니</a>
<%@include file="componnet/footer.jsp"%>
</body>
</html>
