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
    <input type="text" id="memberPassword" placeholder="삭제확인 비밀번호를 입력해주세요">
    <input type="button" onclick="pass_check()" value="비밀번호 확인">
  </form>
</div>
<%@include file="../componnet/footer.jsp" %>
</body>
<script>
  const pass_check = () => {
    // 확인할 비밀번호
    const inputPass = document.getElementById("memberPassword").value;
    // 서버에 저장되어 있는 비밀번호

    if (inputPass === '0000') {
      // 비밀번호 일치
      if (confirm("게시글을 삭제하시겠습니까?")) {
        document.deleteForm.submit();
      }
    } else {
      // 비밀번호 불일치
      alert("비밀번호가 일치하지 않습니다! 다시 입력해주세요");
    }
  }
</script>
</html>
