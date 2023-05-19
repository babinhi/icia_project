<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-19
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<<head>
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
      <th>id</th>
      <th>회원 이메일</th>
      <th>회원 이름</th>
      <th>회원 전화번호</th>
      <th>회원 주소</th>
      <th>회원 삭제</th>
    </tr>
    <c:forEach items="${memberList}" var="member">
      <tr>
        <td>${member.id}</td>
        <td>${member.memberEmail}</td>
        <td>${member.memberName}</td>
        <td>${member.memberaddress}</td>
        <td>${member.memberMobile}</td>
        <td>
          <button onclick="manager('${member.id}')">회원삭제</button>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
<script>
  const manager = (id) => {
    location.href="/member/memberDelete?id="+id;

  }
</script>
</html>
