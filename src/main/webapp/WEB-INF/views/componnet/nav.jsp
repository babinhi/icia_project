<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<div id="container">
    <ul class="myMenu">
        <li class="menu1">메뉴</li>
        <li class="menu2">
            메뉴 2
            <ul class="menu2_s submenu">
                <li><a href="/product/productList">원두</a></li>
                <li><a href="#">커피용품</a></li>
                <li><a href="#">잔</a></li>
            </ul>
        </li>
        <li class="menu3">
            메뉴 3
            <ul class="menu3_s submenu">
                <li>메뉴 3-1</li>
                <li>메뉴 3-2</li>
                <li>메뉴 3-3</li>
            </ul>
        </li>
        <li class="menu4">메뉴 4</li>
        <li class="menu5">메뉴 5</li>
    </ul>
</div>

<%--<script>--%>
<%--    const loginArea = document.getElementById("login-area");--%>
<%--    const loginEmail = '${sessionScope.loginEmail}';--%>
<%--    console.log(loginEmail.length);--%>
<%--    if (loginEmail.length != 0) {--%>
<%--        loginArea.innerHTML = "<a href='/mypage' style='color: black;'>"+loginEmail +"님 환영해요!</a>"+--%>
<%--            "<a href='/logout'>logout</a>";--%>
<%--    } else {--%>
<%--        loginArea.innerHTML = "<a href='/login'>login</a>";--%>
<%--    }--%>
<%--</script>--%>