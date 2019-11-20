<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="style.css" type="text/css">
	<script type="text/javascript">
		function update(num) {
			document.updateFrm.custno.value = num;
			document.updateFrm.action = "updateMember.jsp";
			document.updateFrm.submit();
		}
	</script>
</head>
<body>
<%
DAO mem_dao = DAO.getInstance();
ArrayList<MemberVO> memlist = mem_dao.listCust();
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
		<h4>회원목록조회/수정</h4>
		<table border="1">
			<tr>
				<th>회원번호</th>
				<th>회원성명</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일자</th>
				<th>고객등급</th>
				<th>거주지역</th>
			</tr>
			<%for(MemberVO e: memlist) {%>
			<tr>
				<td>
				<%int num = e.getCustno(); %>
					<a href="javascript:update(<%=num %>)"><%=e.getCustno() %></a>
				</td>
				<td><%=e.getCustname() %></td>
				<td><%=e.getPhone() %></td>
				<td><%=e.getAddress() %></td>
				<td><%=e.getJoindate() %></td>
				<td><%=e.getGrade() %></td>
				<td><%=e.getCity() %></td>
			</tr>
			<%} %>
		</table>
	</section>
	<footer>
		<h3>
			HRDKOREA Copyrightⓒ2015 All right reserved. Human Resource Development Service of Korea
		</h3>
	</footer>
	<form name="updateFrm" method="get">
		<input type="hidden" name="custno">
	</form>
</body>
</html>