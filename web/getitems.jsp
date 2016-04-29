<%@page import="servletsEx.beans.ItemBean"%>
<%@page import="servletsEx.utils.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>   
<%
//Checking if user session exists or not if not, forward request to login page

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
	//contining if user session exists
	out.println("Hello "+session.getAttribute("userName"));
	out.println(session.getId());
	String url = request.getContextPath()+ "/testSer1";
	Cart cart= (Cart)session.getAttribute("cart");
	
	
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

<!-- Hidden parm to maintain session ID if cookies are disabled. We use it while sending ajax request or any request by JS -->
<input type="hidden" name="session" value="<%=session.getId()%>" id="session"/>
<div class="container">

	<a href="LogoutServlet;jsessionid=<%=session.getId()%>" class="btn btn-danger pull-right">Logout</a>
    <a  data-toggle="modal" href="#cartDetails" 
		class="btn btn-info btn-md pull-right" id="btnCart"> <span
		class="glyphicon glyphicon-shopping-cart" id="spnCart"></span> 
		Cart<%if (cart != null) {out.print(" " + cart.getCartSize());}%>
	</a>

</div>

	<div class="container">
		<!-- Initial options populating -->
		<div class="form-group">
			<select id="catagory" class="form-control">
				<option value="">--select catagory--</option>
				<jsp:useBean id="dbs" class="servletsEx.dao.DBSelects" scope="page"></jsp:useBean>
				<c:forEach var="catList" items="${dbs.getCatagoryList(null)}">
					<option value=${catList.key}>${catList.value}</option>
				</c:forEach>
			</select> <select id="subCat" class="form-control">
				<option>--Select SubCatagory--</option>
			</select> <select id="items" class="form-control">
				<option>--Select Item--</option>
			</select>
		</div>
	</div>
	<div id="itemList" class="container-fluid"></div>

<!-- Display a model saying item has been added to cart -->

	<div id="addCartModel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<p>Item added to cart</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<!-- A model for cart details -->
	
	<div id="cartDetails" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
			
				 <div class="modal-header">
          			 <button type="button" class="close" data-dismiss="modal">&times;</button>
         			 <h4 class="modal-title">Item details</h4>
        		</div>
        		
        	    <div class="modal-body">
         			 <div class="row" id="cartDetails">
         			 		<%Cart cart1 =(Cart)session.getAttribute("cart");
								if(cart1!=null){
							%>
         			 		<%for(ItemBean item : cart1.getCartItems()){%>
         			 			<div class="col-sm-12"  id = <%=item.getItemID() %>  style="background-color:lavender;">
         			 				<h4><%= item.getItemName()%></h4><br>
         			 				<b>discription: </b> <i><%= item.getItemDesc()%></i><br>
         			 				<b>Stock: </b> <i><%=item.getStock() %> </i><br>
         			 				<b>price: </b> <i><%=item.getPrice()%></i><br>
         			 				<button type="button" class="btn btn-danger">remove Item</button>
         			 				<button type="button" class="btn btn-success">View More details</button>
         			 			</div>
         			 		<%}} %>
         			 		
         			 </div>
        		</div>
			</div>
		</div>
	</div>
	
	<c:forEach var="item" items="${cart.getCartItems() }">
         			 			<div class="col-sm-12"  id = ${item.getItemID()}  style="background-color:lavender;">
         			 			<h4>${item.getItemName() }</h4><br>
         			 			<b>discription: </b> <i>${item.getItemDesc() }</i><br>
         			 			<b>Stock: </b> <i>${item.getStock() }</i><br>
         			 			<b>price: </b> <i>${item.getPrice() }</i><br>
         			 			<button type="button" class="btn btn-danger">remove Item</button>
         			 			<button type="button" class="btn btn-success">View More details</button>
         			 			</div>
         			 		</c:forEach>
	<%
		}
	%>
</body>
</html>