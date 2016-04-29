<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>      
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
<title>example</title>

<script type="text/javascript" src = "jquery-2.2.3.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


<script type="text/javascript">

//gets all the TR tags of tabls
var j =  $("table tr");

//to get 2nd row from table
var i = $(j[1]);

//get all the td tags  of 2nd row
var c = i.children();

//to get the data from first td tag. replace the index for all td tag's
var name = $(c[0]).html()

</script>
</head>
<body>
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#cartDetails">Open Modal</button>

<c:out value="${sessionScope.cart.getCartSize() }"/>

<!-- A model for cart details -->
	<div id="cartDetails" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
			
				 <div class="modal-header">
          			 <button type="button" class="close" data-dismiss="modal">&times;</button>
         			 <h4 cl	ass="modal-title">Item details</h4>
        		</div>
        		
        	    <div class="modal-body">
         			 <div class="row" id="cartDetails">
         			 		<c:forEach var="item" items="${sessionScope.cart.getCartItems() }">
         			 			<div class="col-sm-12"  id = ${item.getItemID() }  style="background-color:lavender;">
         			 			<h4>${item.getItemName() }</h4><br>
         			 			<b>discription: </b> <i>${item.getItemDesc() }</i><br>
         			 			<b>Stock: </b> <i>${item.getStock() }</i><br>
         			 			<b>price: </b> <i>${item.getPrice() }</i><br>
         			 			<button type="button" class="btn btn-danger">remove Item</button>
         			 			<button type="button" class="btn btn-success">View More details</button>
         			 			<p><c:out value="${item.getStock()}"></c:out></p>
         			 			</div>
         			 		</c:forEach>
         			 </div>
        		</div>
			</div>
		</div>
	</div>

</body>
</html>