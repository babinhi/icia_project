<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-18
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<%@include file="../componnet/header.jsp" %>
<%@include file="../componnet/nav.jsp" %>
<div id="section">
    <h2>글쓰기</h2>
    <form action="/product/Productsave" method="post" enctype="multipart/form-data">
        <input type="text" name="productTitle" placeholder="제목을 입력하세요"> <br>
        <input type="text" name="productWriter" value="${sessionScope.loginEmail}" readonly> <br>
        <textarea name="productContents" cols="30" rows="10"></textarea> <br>
        <input type="file" name="productFile" multiple> <br>
        <input type="submit" value="작성">
    </form>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
</html>
