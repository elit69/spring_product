<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<form id="frmLogin" action="${pageContext.request.contextPath}/login" method="POST">

	username : <input type="text" name="username" /> <br/>
	password : <input type="test" name="password" /> <br/>
	<input type="submit" value="Login"  />

</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

 <script type="text/javascript">
        
        $(function() {
        	
        	$("#frmLogin").submit(function(e){
       		
       		  e.preventDefault();
       			
       		  $.ajax({
  	            url: "${pageContext.request.contextPath}/login",
  	            type: "POST",
  	            data: $("#frmLogin").serialize(),
//   	            beforeSend: function (xhr) {
//   	                xhr.setRequestHeader("X-Ajax-call", "true");
//   	            },
  	            success: function(data) {
  	            	if(data == "User account is locked"){
  	            		alert(data);
  	            	}else if(data == "User is disabled"){
  	            		alert(data);
  	            	}else if(data == "Bad credentials"){
  	            		alert(data);
  	            	}else{
  	            		alert("Logined success.");
  	            		location.href = "${pageContext.request.contextPath}/"+data;
  	            	}
  	            	
  	            },
  	         	error: function(data){
  	         		console.log(data);
  				}
  	        });
       			
       		});
	      
        
        });
        </script>
        
</body>
</html>