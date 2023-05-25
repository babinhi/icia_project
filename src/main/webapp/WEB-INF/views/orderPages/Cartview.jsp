<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-23
  Time: 오전 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="com.icia.shop.dto.CartDTO" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

</head>
<body>
<%@include file="../componnet/header.jsp" %>
<%@include file="../componnet/nav.jsp" %>
<div id="section">
    <form action="/order/cart" name="form1" id="form1" method="post">
        <h2>장바구니</h2>
        <c:choose>
            <c:when test="${map.count == 0}">
                장바구니가 비어있습니다.
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>상품명</th>
                        <th>단가</th>
                        <th>수량</th>
                        <th>금액</th>
                        <th>취소</th>
                    </tr>
                    <c:forEach var="row" items="${map.list}" varStatus="status">
                        <tr>
                            <td>${row.productTitle}</td>
                            <td style="width: 80px; text-align: right">
                                <fmt:formatNumber pattern="###,###,###" value="${row.productPrice}"/>
                            </td>
                            <td style="text-align: right">
                                <input type="number" style="width: 40px" name="amount" value="${row.productCnt}" min="1">
                                <input type="hidden" name="productId" value="${row.productId}">
                            </td>
                            <td style="width: 100px; text-align: right">
                                <fmt:formatNumber pattern="###,###,###" value="${row.money}"/>
                            </td>
                            <td>
                                <a href="/order/delete?id=${row.id}">장바구니 삭제</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" style="text-align: right">
                            장바구니 금액: <fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}"/><br>
                            배송비: ${map.fee}<br>
                            합계: <fmt:formatNumber pattern="###,###,###" value="${map.allSum}"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="count" value="${map.count}">
                <button type="submit" id="btnUpdate">수정</button>
            </c:otherwise>
        </c:choose>
    </form>
</div>
<button type="button" onclick="cart_list()">상품 목록</button>
<%@include file="../componnet/footer.jsp" %>
</body>
<script>
    const cart_list = () => {
        location.href = "/product/paging";
    }
</script>
</script>
</html>
