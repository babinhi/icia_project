<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-19
  Time: 오전 10:42
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
    ${sessionScope.loginEmail} 님의 회원정보
    <h2>회원 정보</h2>
    <table>
        <tr>
            <th>프로필사진</th>
            <th>
                <c:if test="${member.memberProfile == 1}">
                    <img src="${pageContext.request.contextPath}/upload/${memberFile.storedFileName}" alt="" width="100"
                         height="100">
                </c:if>
                <c:forEach items="${memberFileList}" var="memberFile">
                    <c:if test="${member.memberProfile ==1 }">
                        <img src="${pageContext.request.contextPath}/upload/${memberFile.storedFileName}"
                             alt="" width="150" height="150">
                    </c:if>
                </c:forEach>
            </th>
        </tr>
        <tr>
            <th>회원번호 : </th>
            <th>${member.id}</th>
        </tr>
        <tr>
            <th>이름    : </th>
            <th>${member.memberName}</th>
        </tr>
        <tr>
            <th>이메일   : </th>
            <th>${member.memberEmail}</th>
        </tr>
        <tr>
            <th>비밀번호 : </th>
            <th>${member.memberPassword}</th>
        </tr>
        <tr>
            <th>전화번호 : </th>
            <th>${member.memberMobile}</th>
        </tr>
        <tr>
            <th>주소    : </th>
            <th>${member.memberAddress}</th>
        </tr>
    </table>
    <br><br>

    <button onclick="member_update('${member.id}')">수정</button>
    <button onclick="member_delete('${member.id}')">삭제</button>
    <button onclick="board_list()">목록</button>
    <br>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
<script>
    const member_update = () => {
        const id = ${member.id};
        location.href = "/member/UpdatePass?id=" + id;
    }
    const member_delete = () => {
        const id = ${member.id};
        location.href = "/member/PasswordCheck?id=" + id;
    }
    const member_list = () => {
        const type = '${type}';
        const q = '${q}';
        const page = '${page}'
        location.href = "/member/paging";
    }

</script>
</html>
