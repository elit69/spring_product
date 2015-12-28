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
										<td><c:out value="${product.cby.username}" /></td>
										<td><c:out value="${product.uby.username}" /></td>
										<td>delete</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
</body>
</html>