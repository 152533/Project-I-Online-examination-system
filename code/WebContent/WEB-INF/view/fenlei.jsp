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
<style>
.ln-guild {
  clear: both;
  position: fixed;
  right: 0;
  top: 78px;
  z-index: 0;
}

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



<div class="container" style="padding-top: 65px;">
	<img alt="" src="<%=basePath%>imgs2/fenlei01.png" class="img-responsive">
</div>

<div class="container" style="padding-top: 50px;">
	<img alt="" src="<%=basePath%>imgs2/fenlei02.png" class="img-responsive">
</div>
<div class="container" >
	<!-- !important -->
	<table class="table table-bordered " style="border-spacing: 30px;" >
		<c:choose>
			<c:when test="${not empty subjects }">
				<c:forEach items="${subjects }" var="subject" varStatus="vs">
					<c:if test="${vs.index % 4 == 0 }">
						<c:set var="flagIndex" value="${vs.index + 4}"></c:set>
						<tr>
					</c:if>
						<td >
							<div style="height: 80px;padding-top: 25px;">
								<span class="glyphicon glyphicon-pencil"></span>
								&nbsp;&nbsp;&nbsp;
								<a href="<%=basePath%>action?act=start&subject=${subject.sub_id }"><strong class="text-danger" style="font-size: 18px;">${subject.KEMU }</strong></a>
							</div>
							<div style="background-color: #F1FAEA;height: 24px;" >
								<span style="padding-left: 30px;">监考-${subject.jiankao }</span>
							</div>
						</td>
					<c:if test="${(vs.index eq flagIndex)||vs.last}">
						</tr>
					</c:if>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</div>
<div style="background-color: #293448;position: fixed;bottom: 0;width: 100%;">
	<div class="container-fluid text-center" style="height: 80px;line-height: 80px;" >
		<span style="color: #ffffff;">吉林省明日科技有限公司 <a href="www.mingrisoft.com">Copyright@www.mingrisoft.com</a> AllRights Reserved 备案号:吉ICP证07500273</span>
		<span><a href="javascript:void(0);" onclick="hideFooter();">隐藏</a></span>
	</div>
</div>
    <script src="./bootstrap-3.3.5-dist/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="./bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function setFlag(flag){
    		if ($("#dtk"+flag).hasClass('btn-info')){
    			$("#dtk"+flag).removeClass('btn-info').addClass('btn-warning');
    		}else{
    			$("#dtk"+flag).removeClass('btn-warning').addClass('btn-info');
    		}
    	}
    	function goIndex (flag){
    		window.location.hash = "a"+flag;
    		window.location.href = "#a"+flag;
    	}
    	function hideFooter (){
    		$("#footer").hide();
    	}
    </script>
</body>
</html>