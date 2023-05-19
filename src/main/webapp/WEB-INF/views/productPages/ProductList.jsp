<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-18
  Time: 오후 6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
  <table>
    <tr>
      <th>글번호</th>
      <th>제목</th>
      <th>내용</th>
      <th>조회수</th>
      <th>작성시간</th>
      <th>파일여부</th>
    </tr>
    <c:forEach items="${productlist}" var="product">
      <tr>
        <td>${product.id}</td>
        <td><a href="/product/detail?id=+${product.id}&page=${paging.page}&q=${q}&type=${type}"> ${product.productTitle}</a></td>
        <td>${product.productContents}</td>
        <td>${product.productHits}</td>
        <td>
          <fmt:formatDate value="${product.productCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
        </td>
        <td>${product.fileAttached}</td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@include file="../componnet/footer.jsp" %>
</body>
</html>
