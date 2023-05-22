<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-18
  Time: 오후 3:04
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
    <h2>상품등록</h2>
    <form action="/product/productSave" method="post" enctype="multipart/form-data">
        <div class="inputArea">
            <label>1차 분류</label>
            <select class="category1">
                <option value="">전체</option>
                <option value="">원두</option>
                <option value="">용품</option>
            </select>
        </div>

        <div class="inputArea">
            <label for="productTitle">상품명</label>
            <input type="text" id="productTitle" name="productTitle"/>
        </div>

        <div class="inputArea">
            <label for="productPrice">상품가격</label>
            <input type="text" id="productPrice" name="productPrice"/>
        </div>

        <div class="inputArea">
            <label for="productQuantity">상품수량</label>
            <input type="text" id="productQuantity" name="productQuantity"/>
        </div>

        <div class="inputArea">
            <label for="productContents">상품소개</label><br>
            <textarea rows="5" cols="50" id="productContents" name="productContents"></textarea>
        </div>
        <input type="file" name="productFile" multiple> <br>
        <div class="inputArea">
            <button type="submit" id="register_Btn" class="btn btn-primary">등록</button>
        </div>
        <%--        <input type="text" name="productTitle" placeholder="상품이름"> <br>--%>
        <%--        <input type="text" name="productWriter" value="${sessionScope.loginEmail}" readonly> <br>--%>
        <%--        <textarea name="productContents" cols="30" rows="10" placeholder="상품설명"></textarea> <br>--%>

        <%--        <input type="submit" value="작성">--%>
    </form>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
</html>
