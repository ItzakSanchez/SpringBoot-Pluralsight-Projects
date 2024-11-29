<%@ page contentType="text/html; ISO-8859-1" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>REGISTRATION JSP (JAVA SERVER PAGE)</title>	
		<style>
			.error{
				color: red;
			}
			.errorBlock{
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
	<head>
	<body>
		<h1>Registration Page</h1>
		<form:form modelAttribute="registration">
			<form:errors path="*" cssClass='errorBlock' element="div" />
			<spring:message code="name"/>
			<form:input path="name" />
			<form:errors path="name" cssClass="error" />
			<input type="submit" value='<spring:message code="save.changes"/>'>
		</form:form>
	<body>
</html>