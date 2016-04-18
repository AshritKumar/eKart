<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>