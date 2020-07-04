<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
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
    <style type="text/css">
    
    </style>
</head>
<body >

<div class="container-fluid" style="background-color: #293448;">
	<div class="container">
		<table>
			<tr>
				<td style="width: 160px;padding-left: 80px;">
					<img alt="" src="<%=basePath%>imgs2/logo.png">
				</td>
				<td style="width: 110px;">
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
						<a href="<%=basePath%>main" style="color: #73ca33;">首页</a>
					</div>
					<div style="background-color: green;height: 3px;">
						
					</div>
				</td>
				<td style="width: 110px;">
					<div class="text-center" style="padding-bottom: 10px;padding-top: 30px;">
					<a href="<%=basePath%>action?act=kskm" style="color: #ffffff;">在线考试</a>
					</div>
					<div style="height: 3px;">
						
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

<div >
  <img alt="" src="<%=basePath%>imgs2/index01.png" class="img-responsive">
</div>
<div class="container">
  <img alt="" src="<%=basePath%>imgs2/index02.png" class="img-responsive">
</div>
<div class="container">
  <img alt="" src="<%=basePath%>imgs2/index03.png" class="img-responsive">
</div>
<div class="container">
  <img alt="" src="<%=basePath%>imgs2/index04.png" class="img-responsive">
</div>
<div class="container" style="padding-bottom: 30px;padding-top: 15px;">
  <div ><button style="background-color:#73ca33;min-width:980px;min-height:50px;" type="button" onclick="goFL();">立即考试</button></div>
</div>
<div class="container-fluid" style="background-color: #293448;">
	<div class="container-fluid text-center" style="height: 80px;line-height: 80px;" >
		<span style="color: #ffffff;">吉林省明日科技有限公司 <a href="www.mingrisoft.com">Copyright@www.mingrisoft.com</a> AllRights Reserved 备案号:吉ICP证07500273</span>
	</div>
</div>
    <script src="./bootstrap-3.3.5-dist/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="./bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function goFL (){
    		window.location.href = "<%=basePath%>action?act=kskm";
    	}
    </script>
</body>
</html>