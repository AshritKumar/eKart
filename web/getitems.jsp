<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>   
<%

HttpSession session = request.getSession(false);
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); //prevents caching at the proxy server
response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");// Set standard HTTP/1.1 no-cache headers.
response.addHeader("Cache-Control", "post-check=0, pre-check=0");// Set IE extended HTTP/1.1 no-cache headers (use addHeader) 
if(session==null){
	System.out.println("Get ites page - Not logged in !!");
	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	out.println("<center>Please login First</center>");
	rd.include(request, response);
}

else{
	
	out.println("Hello "+session.getAttribute("userName"));
	out.println(session.getId());
	String url = request.getContextPath()+ "/testSer1";
%>            
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="Pragma" content="no-cache">
 <meta http-equiv="Cache-Control" content="no-cache">
 <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="getitems.css">
<title>Get Items</title>
  
<script type="text/javascript" src = "jquery-2.2.3.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src = "getitems.js"></script>
</head>
<body>
<input type="hidden" name="session" value="<%=session.getId()%>" id="session"/>
<div class="container">
<a href="LogoutServlet;jsessionid=<%=session.getId()%>" class="btn btn-danger pull-right">Logout</a>
</div>

<div class="container">
<div class="form-group">
<select  id="catagory" class="form-control">
<option value="">--select catagory--</option>
<jsp:useBean id="dbs" class="servletsEx.dao.DBSelects" scope="page"></jsp:useBean>
<c:forEach var="catList"  items="${dbs.getCatagoryList(null)}">
<option value=${catList.key}>${catList.value}</option>
</c:forEach>
</select>
<select id="subCat" class="form-control">
<option>--Select SubCatagory--</option>
</select>

<select id="items" class="form-control">
<option>--Select Item--</option>
</select>
</div>
</div>
<div id="itemList" class="container-fluid">

</div>
<%} %>
</body>
</html>