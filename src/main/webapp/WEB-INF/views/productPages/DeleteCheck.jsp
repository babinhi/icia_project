<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-22
  Time: 오후 2:12
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
  <form action="/product/DeletePass" method="post" name="deleteForm">
    <input type="text" name="memberPassword" id="memberPassword" placeholder="현재 비밀번호를 입력해주세요">
    <input type="button" onclick="pass_check()" value="비밀번호 확인">
  </form>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
<script>
  // 비밀번호 확인 메소드
  const pass_check = () => {
    //  확인할 비밀번호
    const inputPass = document.getElementById("memberPassword").value;
    // 서버에 저장되있는 비밀번호
    const DBPass = '${member.memberPassword}';
    if (inputPass == DBPass) {
      document.deleteForm.submit();
    } else {
      alert("비밀번호가 일치하지 않습니다! 다시 입력해주세요");
    }
  }
</script>
</html>
