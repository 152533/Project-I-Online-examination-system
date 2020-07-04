<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE >
<html>
<head>
	<title>功能权限设置</title>
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
		var socketPath = '<%=socketPath%>';
	</script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/themes/color.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/themes/icon.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui-lang-zh_CN.js"></script>
	
	 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>明日科技</title>
    <!-- Bootstrap -->
    <link href="<%=basePath %>bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/ityks.css" rel="stylesheet">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href='<%=basePath %>js/fullcalendar2_7/dist/fullcalendar.css' rel='stylesheet' />
	<link href='<%=basePath %>js/fullcalendar2_7/dist/fullcalendar.print.css' rel='stylesheet' media='print' />
	<script type="text/javascript" src="<%=basePath %>bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container text-center" style="padding-bottom: 70px;">
		<c:choose>
			<c:when test="${not empty subjects }">
				<input type="hidden" value="kskm" name="act">
				<select name="subject" onchange="goStart(this)">
				<option value="0">请选择</option>
				<c:forEach items="${subjects }" var="subject" varStatus="vs">
					<option value="${subject.sub_id }">${subject.KEMU }</option>
				</c:forEach>
				</select>
			</c:when>
		</c:choose>
	</div>
</body>
</html>