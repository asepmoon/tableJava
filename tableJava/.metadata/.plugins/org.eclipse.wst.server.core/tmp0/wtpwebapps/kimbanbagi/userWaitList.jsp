<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userWaitList</title>
</head>
<body>
	<form method="post" action="userwait">
		<input type="submit" value="submit">
	</form>
	<h1>웨이팅 내역</h1>
	<ul>
		<c:forEach items="${waitList}" var="wait">
			<div>
				<li>
					웨이팅한 식당 : ${wait.restaurantName} <br>
					인원수 : ${wait.headCount } <br>	
					웨이팅 상태 : ${wait.waitingStatus } <br>
					웨이팅숫자 : ${wait.waitingNumber} <br>
					
				</li>
			</div>
		</c:forEach>
	</ul>
</body>
</html>