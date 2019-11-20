<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
DAO mem_dao = DAO.getInstance();
int custno = Integer.parseInt(request.getParameter("custno"));
String custname = request.getParameter("custname");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
String joindate = request.getParameter("joindate");
String grade = request.getParameter("grade");
String city = request.getParameter("city");
MemberVO vo = new MemberVO();
vo.setCustno(custno);
vo.setCustname(custname);
vo.setPhone(phone);
vo.setAddress(address);
vo.setJoindate(joindate);
vo.setGrade(grade);
vo.setCity(city);
mem_dao.insertMember(vo);
%>
<script type="text/javascript">
location.href="insert.jsp";
</script>
</body>
</html>