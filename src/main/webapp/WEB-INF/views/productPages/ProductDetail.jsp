<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-19
  Time: 오전 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

</head>
<style>

</style>
<body>
<%@include file="../componnet/header.jsp" %>
<%@include file="../componnet/nav.jsp" %>

<div id="section" class="table table-striped table-hover text-center rounded-3">
    <h2>상품 상세정보</h2>
    <div class="login-name" style="float: left; ">
        <c:if test="${sessionScope.loginEmail == 'admin'}">
            <button class="btn btn-default btn-update" data-id="${product.id}" style="display: inline-block;">수정하기
            </button>
            <form action="/product/DeletePass" method="post" name="deleteForm" style="display: inline-block;">
                <input type="button" onclick="pass_check()" value="게시글 삭제">
            </form>
        </c:if>
    </div>
    <table>
        <br><br>
        <tr>
            <td style="text-align: center; width: 50%;">
                <c:forEach items="${productFileList}" var="productFile">
                    <c:if test="${product.fileAttached == 1}">
                        <img src="${pageContext.request.contextPath}/upload/${productFile.storedFileName}"
                             style="max-width: 100%; max-height: 300px;">
                    </c:if>
                </c:forEach>
            </td>
            <td style="vertical-align: top; width: 50%;">
                <table style="width: 100%;" class="container">
                    <tr style="text-align: center;">
                        <th>상품번호</th>
                        <td>${product.id}</td>
                    </tr>
                    <tr style="text-align: center">
                        <th>상품명</th>
                        <td>${product.productTitle}</td>
                    </tr>
                    <tr style="text-align: center">
                        <th>상품가격</th>
                        <td>${String.format("%,d", product.productPrice)}원</td>
                    </tr>
                    <tr style="text-align: center">
                        <th>남은 갯수</th>
                        <td>${product.productQuantity}</td>
                    </tr>
                    <tr style="text-align: center">
                        <th>상품소개</th>
                        <td>${product.productContents}</td>
                    </tr>
                    <tr style="text-align: center">
                        <th>작성시간</th>
                        <td><fmt:formatDate value="${product.productCreatedDate}"
                                            pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    </tr>
                    <tr style="text-align: center">
                        <th>조회수</th>
                        <td>${product.productHits}</td>
                    </tr>
                    <tr style="text-align: center">
                        <td colspan="2">
                            <form action="/order/cart" name="form1" method="post" onsubmit="return cartForm('${product.id}')">
                                <input type="hidden" name="id" value="${product.id}">
                                <select name="amount" onchange="cartForm(this.value)">
                                    <c:forEach begin="1" end="10" var="i">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>
                                </select>&nbsp;
                                <button type="submit" class="btn btn-default btn-cart">장바구니에 담기</button>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <button class="btn btn-default btn-order">주문하기</button>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<br><br>
<div style="float: left">
    <%--    <button onclick="product_list()">목록</button>--%>
    <button class="btn btn-default btn-list">목록</button>


    <button class="btn btn-default btn-wishlist">위시리스트</button>
</div>

<br>
<h2>──────────────────────────────────────────────────────────────────────────────────────────────</h2>
<div id="commentOk" style="margin: 20px">
    <div id="comment-write-area" >
        리뷰 <input type="text" name="commentWriter" id="comment-writer" value="${sessionScope.loginEmail}"
                  style="display: inline-block" readonly>
        <input type="text" name="commentContents" id="comment-contents" placeholder="댓글" style="display: inline-block">
        <button onclick="comment_write()" style="display: inline-block">댓글</button>
    </div>
</div><br>
<div id="comment-list" class="table table-striped table-hover text-center rounded-3 ms-5">
    <c:choose>
        <c:when test="${commentList == null}">
            <h5>현재 작성된 댓글이 없습니다.</h5>
        </c:when>
        <c:otherwise>
            <table>
                <tr>

                    <th>작성자</th>
                    <th>내용</th>
                    <th>작성시간</th>
                </tr>
                <c:forEach items="${commentList}" var="comment">
                    <tr>
                        <td>${comment.commentWriter}</td>
                        <td>${comment.commentContents}</td>
                        <td>
                            <fmt:formatDate value="${comment.commentCreatedDate}"
                                            pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="../componnet/footer.jsp" %>
</body>
<script>
    const productPrice = ${product.productPrice}; // 가격을 동적으로 가져옴
    const calculatedPrice = Math.floor(productPrice / 0.9); // 가격을 0.9로 나눈 후 소수점 아래를 제거하여 정수로 변환
    const discountedPrice = productPrice.toLocaleString(); // 할인가에 1000단위 쉼표를 붙임

    <%--const product_update = () => {--%>
    <%--    const id = '${product.id}';--%>
    <%--    location.href = "/product/update?id=" + id;--%>
    <%--}--%>
    <%--const product_delete = () => {--%>
    <%--    const id = '${product.id}';--%>
    <%--    location.href = "/product/DeletePass?id=" + id;--%>
    <%--}--%>
    <%--const product_list = () => {--%>
    <%--    const type = '${type}';--%>
    <%--    const q = '${q}';--%>
    <%--    const page = '${page}'--%>
    <%--    location.href = "/product/paging?page=" + page + "&type=" + type + "&q=" + q;--%>
    <%--}--%>
    $(".btn-update").click(function () {
        const id = '${product.id}';
        location.assign("/product/update?id=" + id);
    });

    <%--$(".btn-delete").click(function () {--%>
    <%--    const id = '${product.id}';--%>
    <%--    location.assign("/product/DeletePass?id=" + id);--%>
    <%--});--%>
    const pass_check = () => {
        const confirmResult = confirm("게시글을 삭제하시겠습니까?");
        if (confirmResult) {
            const id = '${product.id}';
            location.assign("/product/DeletePass?id=" + id);
        } else {
            alert("게시글 삭제를 취소했습니다.");
        }
    }
    $(".btn-list").click(function () {
        const type = '${type}';
        const q = '${q}';
        const page = '${page}';
        location.assign("/product/paging?page=" + page + "&type=" + type + "&q=" + q);
    });

    $(".btn-order").click(function () {
        location.assign("/order/cart");
    });

    // $(".btn-cart").click(function () {
    //     var productId = $(this).data('id'); // productId를 가져옵니다.
    //
    //     $.ajax({
    //         type: "POST",
    //         url: "/order/cart/add",
    //         data: {
    //             productId: productId
    //         },
    //         dataType: "text",
    //         success: function (result) {
    //             if (result.trim() === 'add_success') {
    //                 var check = confirm("장바구니에 등록되었습니다.");
    //                 if (check) {
    //                     var memberId = "{{memberId}}"; // 적절한 유저 아이디 값을 가져와서 사용합니다. 수정이 필요합니다.
    //                     location.assign("/order/mycart/" + memberId);
    //                 }
    //             } else if (result.trim() === 'already_existed') {
    //                 alert("이미 카트에 등록된 상품입니다.");
    //             }
    //         },
    //         error: function (xhr, status, error) {
    //             console.log(xhr.responseText);
    //             alert("카트 등록 중 오류가 발생했습니다.");
    //             location.assign("/product/paging")
    //         }
    //     });
    // });

    const cartForm = (id) => {
        console.log(id); // id 값을 확인하기 위한 출력문 추가

        const productCnt = document.getElementsByName("productCnt")[0].value;
        if (confirm("장바구니에 추가하시겠습니까?")) {
            if ('<%= session.getAttribute("loginEmail") %>' == null) {
                alert("로그인을 해주세요");
                location.href = "/member/login";
            } else {
                location.href = "/order/cart?id=" + id + "&productCnt=" + productCnt;
            }
        } else {
            location.reload();
        }
    };
    const cartForm = (id) => {
        <%--const id = '<%= product.id %>'; // 상품의 ID 값을 직접 삽입--%>
        console.log('<%= product.id %>');
        console.log(id); // id 값을 확인하기 위한 출력문 추가

        const productCnt = document.getElementsByName("amount")[0].value; // "amount"으로 수정

        if (confirm("장바구니에 추가하시겠습니까?")) {
            if ('<%= session.getAttribute("loginEmail") %>' == null) {
                alert("로그인을 해주세요");
                location.href = "/member/login";
            } else {
                location.href = "/order/cart?id=" + id + "&productCnt=" + productCnt;
            }
        } else {
            location.reload();
        }
    };


    const comment_write = () => {
        const commentWriter = document.getElementById("comment-writer").value;
        const commentContents = document.getElementById("comment-contents").value;
        const productId = '${product.id}';
        const result = document.getElementById("comment-list");

        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                "commentWriter": commentWriter,
                "commentContents": commentContents,
                "productId": productId
            },
            success: function (res) {
                let output = "<table>";
                output += "<tr>";
                output += "<th>작성자</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th>";
                output += "</tr>";
                for (let i in res) {
                    output += "<tr>";
                    output += "<td>" + res[i].commentWriter + "</td>";
                    output += "<td>" + res[i].commentContents + "</td>";
                    output += "<td>" + moment(res[i].commentCreatedDate).format("YYYY-MM-DD HH:mm:ss") + "</td>";
                    output += "</tr>";
                }
                output += "</table>";
                result.innerHTML = output;
                // 인풋태그를 비우는 역할
                document.getElementById("comment-contents").value = "";
            },
            error: function (res) {
                console.log("실패");
            }
        });
    };


</script>
</html>
