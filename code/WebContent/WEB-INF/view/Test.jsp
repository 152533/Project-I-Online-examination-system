<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<style>
.rn-guild {
  clear: both;
  position: fixed;
  left: 0px;
  top: 130px;
  z-index: 0;
}
</style>
</head>
<body >

<div class="rn-guild">
	<div class="panel panel-success">
		<div class="panel-heading">
			考试剩余时间
		</div>
		<div class="panel-body">
			<a href="#">倒计时 <span id="t_h"></span>时<span id="t_m"></span>分<span id="t_s"></span>秒</a>
		</div>
	</div>
</div>

    <script type="text/javascript">
		var basePath = '<%=basePath%>';
		var socketPath = '<%=socketPath%>';
	</script>
    <script type="text/javascript" src="<%=basePath %>js/websocket.js"></script>
</body>
</html>