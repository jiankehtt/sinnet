<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ERROR 404 page</title>
	</head>

	<body>

		<div>
			<p>
				<%
					Enumeration<String> enums = request.getParameterNames();
					while(enums.hasMoreElements()){
					  String key = enums.nextElement();
					  out.println(key+"="+request.getParameter(key));
					  out.println("<br>");
					}
				%>
			</p>
		</div>

	</body>
</html>