<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <ul>
        <li class="login-name" id="login-area">
        <li class="login-name">
            <c:choose>
                <c:when test="${sessionScope.loginEmail != null}">
                    <a>${sessionScope.loginEmail} 님</a>

                    <c:if test="${sessionScope.loginEmail != 'admin'}">
                        <a href="/member/myPage?loginEmail=${sessionScope.loginEmail}">마이페이지</a>
                        <a href="/order/cart">장바구니</a>
                    </c:if>
                    <a href="/member/logout">logout</a>
                </c:when>
                <c:otherwise>
                    <a href="/member/save">sign up</a>
                    <a href="/member/login">login</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li class="login-name">
            <c:if test="${sessionScope.loginEmail == 'admin'}">
                <a href="/member/managerPage">회원리스트</a>
                <a href="/product/productSave" class="left1">상품등록</a>
            </c:if>
        </li>
<%--        <li class="login-name">--%>
<%--            <c:if test="${sessionScope.loginEmail != 'admin'}">--%>
<%--                <a href="/member/myPage?loginEmail=${sessionScope.loginEmail}">마이페이지</a>--%>
<%--                <a href="/order/cart">장바구니</a>--%>
<%--            </c:if>--%>
<%--        </li>--%>
        </li>
    </ul>
    <h2><a href="/">icia shop</a></h2>
</div>
