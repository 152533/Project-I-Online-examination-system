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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>明日科技</title>
    <!-- Bootstrap -->
    <link href="./bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container-fluid" style="background-color: #293448;">
	<div class="container">
		<table>
			<tr>
				<td style="width: 160px;padding-left: 80px;">
					<img alt="" src="<%=basePath%>imgs2/logo.png">
				</td>
				<td style="width: 110px;">
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
						<a href="<%=basePath%>main" style="color: #ffffff;">首页</a>
					</div>
					<div style="height: 3px;">
						
					</div>
				</td>
				<td style="width: 110px;">
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
					<a href="<%=basePath%>action?act=kskm" style="color: #73ca33;">在线考试</a>
					</div>
					<div style="background-color: green;height: 3px;">
						
					</div>
				</td>
				<td style="width: 110px;">
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
						<a href="<%=basePath%>action?act=kfcx" style="color: #ffffff;">成绩查询</a>
					</div>
					<div style="height: 3px;">
						
					</div>
				</td>
				<td >
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
						<a href="#" style="color: #ffffff;">你好,尊敬的会员:${USERNAME }</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>

<div class="container" style="padding-top: 20px;">
	<img alt="" src="<%=basePath%>imgs2/shijuan01.png" class="img-responsive">
</div>

	<div class="container text-center" style="background-color: #F1FAEA;height: 150px;padding-top: 20px;">
			<c:choose>
				<c:when test="${not empty mains }">
					<form action="<%=basePath %>action" method="get" style="padding-top: 50px;">
						<input type="hidden" name="act" value="action">
						<div class="input-group">
						<span class="input-group-addon">
							请选择试卷
						</span>
						<select name="main_id" class="form-control">
							<option value="0">请选择</option>
						<c:forEach items="${mains }" var="main" varStatus="vs">
							<option value="${main.main_id }">${main.BIAOTI }</option>
						</c:forEach>
						</select>
						<span class="input-group-btn">
							<button type="submit" class="btn" style="background-color:#73CA33; ">开始考试</button>
						</span>
						</div>
					</form>
				</c:when>
			</c:choose>
	</div>
	<div class="container" style="padding-top: 20px;">
		<table class="table table-bordered">
			<tr>
				<th style="background-color: #F1FAEA;"><h4>考场规定</h4></th>
				<th style="background-color: #F1FAEA;">考场提示</th>
			</tr>
			<tr>
				<td style="height: 360px;">
					<dl>
						<dd>1.不准携带手机,耳机</dd>
						<dd>2.不准交头接耳</dd>
						<dd>3.不准携带与考试无关物品</dd>
					</dl>
				</td>
				<td style="height: 360px;">
					<dl>
						<dd>1.考试发现违纪,本次考试作废</dd>
						<dd>2.如有问题,请举手</dd>
						<dd>3.保管好私人物品</dd>
					</dl>
				</td>
			</tr>
		</table>
	</div>
	
	<div style="background-color: #293448;position: fixed;bottom: 0;width: 100%;">
		<div class="container-fluid text-center" style="height: 80px;line-height: 80px;" >
			<span style="color: #ffffff;">吉林省明日科技有限公司 <a href="www.mingrisoft.com">Copyright@www.mingrisoft.com</a> AllRights Reserved 备案号:吉ICP证07500273</span>
		</div>
	</div>
	<script src="./bootstrap-3.3.5-dist/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="./bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</body>
</html>