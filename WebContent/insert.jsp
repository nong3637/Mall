<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>멤버 등록</title>
	<link rel="stylesheet" href="style.css" type="text/css">
	<script type="text/javascript">
		function check() {
			if(frm.custname.value=="") {
				alert("회원성명이 입력되지 않았습니다.");
				frm.custname.focus();
				return false;
			} else if(frm.phone.value=="") {
				alert("회원전화가 입력되지 않았습니다.");
				frm.phone.focus();
				return false;
			} else if(frm.address.value=="") {
				alert("회원주소 입력되지 않았습니다.");
				frm.address.focus();
				return false;
			} else if(frm.joindate.value=="") {
				alert("가입일자가 입력되지 않았습니다.");
				frm.joindate.focus();
				return false;
			} else if(!(frm.grade.value=="A"||frm.grade.value=="B"||frm.grade.value=="C")) {
				alert("회원등급이 옳바르지 않습니다. (A, B, C)");
				frm.grade.focus();
				return false;
			} else if(frm.city.value=="") {
				alert("도시코드가 입력되지 않았습니다.");
				frm.city.focus();
				return false;
			} else
				alert("회원등록이 완료되었습니다!");
				return true;
		}
	</script>
</head>
<body>
<%
DAO dao = DAO.getInstance();
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
%>
	<header>
		<h3>쇼핑몰 회원관리 ver 1.0</h3>
	</header>
	<nav>
		<ul>
			<li><a href="insert.jsp">회원등록</a></li>
			<li><a href="listMember.jsp">회원목록조회/수정</a></li>
			<li><a href="sale.jsp">회원매출조회</a></li>
			<li><a href="index.jsp">홈으로</a></li>
		</ul>
	</nav>
	<section>
		<h4>홈쇼핑 회원 등록</h4>
		<form action="insertPro.jsp" method="post" name="frm" onsubmit="return check()">
			<table border="1">
				<tr>
					<th>회원번호(자동발생)</th>
					<td>
						<input type="text" name="custno" id="custno" value="<%=dao.getCustno() %>">
					</td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td>
						<input type="text" name="custname" id="custname">
					</td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td>
						<input type="text" name="phone" id="phone">
					</td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td>
						<input type="text" name="address" id="address">
					</td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td>
						<input type="text" name="joindate" id="joindate" value="<%=sf.format(nowTime) %>">
					</td>
				</tr>
				<tr>
					<th>고객등급(A:VIP,B:일반,C:직원)</th>
					<td>
						<input type="text" name="grade" id="grade">
					</td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td>
						<input type="text" name="city" id="city">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="btn">
						<input type="submit" value="등록">&nbsp;<input type="button" value="조회" onclick="location.href='listMember.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</section>
	<footer>
		<h3>
			HRDKOREA Copyrightⓒ2015 All right reserved. Human Resource Development Service of Korea
		</h3>
	</footer>
</body>
</html>