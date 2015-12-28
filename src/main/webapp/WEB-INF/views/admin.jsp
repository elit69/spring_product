<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-striped table-hover">
			<thead>
				<tr class="info">
					<th>ID</th>
					<th>name</th>
					<th>price</th>
					<th>stock</th>
					<th>cdate</th>
					<th>udate</th>
					<th>cby</th>
					<th>uby</th>
					<th>action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${list}">
					<tr>
						<td><c:out value="${product.id}" /></td>
						<td><c:out value="${product.name}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><c:out value="${product.stock}" /></td>
						<td><c:out value="${product.cdate}" /></td>
						<td><c:out value="${product.udate}" /></td>
						<td><a href="${pageContext.request.contextPath}/user/${product.cby.id}">${product.cby.username}</a></td>
						<td><a href="${pageContext.request.contextPath}/user/${product.uby.id}">${product.uby.username}</a></td>
						<td><button class="btndelete" name="${product.id}">delete</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".btndelete").click(function(){
				if(confirm("Are you sure you want to delete this?")){
					var othis=$(this);		
		 			 $.ajax({
		 				 	url: '${pageContext.request.contextPath}/admin/product/' + $(this).attr('name') ,
			  	          	type: 'DELETE',
			  	            success: function(data) {
			  	            	othis.parents('tr').remove();	  	            	
			  	            }
			  	     });	 
				}
			});
		});
	</script>
</body>
</html>