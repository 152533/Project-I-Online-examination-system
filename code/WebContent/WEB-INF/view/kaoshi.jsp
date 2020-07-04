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
  right: 0px;
  top: 130px;
  z-index: 0;
}

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

<div class="container bg-warning" style="padding-top: 65px;">
	<form id="actionSub" action="<%=basePath %>action" method="post">
		<h3 class="text-primary text-center"><span class="glyphicon glyphicon-tags"></span><span style="padding-left: 9px;">${questions.BIAOTI}</span></h3>
		<div class="well text-primary">
		<span class="glyphicon glyphicon-book">试卷说明:</span>
		<br />
		<ol>
			<li>本卷共分为三大题100小题,作答时间为120分钟,总分100分.</li>
			<li>试题年份:2016</li>
			<li>试卷来源:考试资料网在线考试中心.</li>
	        <li>鼠标左键双击标题可在答题卡制作标记.</li>
		</ol>
		</div>
		<table class="table table-hover" style="color: 000000;">
			<c:choose>
				<c:when test="${not empty questions.questions }">
					<tbody>
					<c:forEach items="${questions.questions }" var="question" varStatus="vs">
						<tr class="success" ondblclick="setFlag(${vs.index })" >
							<th >
								<a name="a${vs.index }" id="a${vs.index }" style="padding-top: 61px;"></a>
								<h4 style="color: #000000;"><span class="glyphicon glyphicon-paperclip text-danger"></span>${vs.index + 1 }.${question.WENTI }[<a href="javascript:void(0)" onclick="setFlag(${vs.index })">答题标记</a>]</h4>
							</th>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${not empty questions.answers }">
									<td style="padding-left: 40px"><%//答案开始咯 %>
									<ol class="text-primary" style="font-size: 14px;">
									<c:forEach items="${questions.answers}" var="answer" varStatus="vs">
										<c:if test="${question.que_id eq answer.que_id }">
											<c:if test="${question.LEIXING eq 'radio'}"><%//这里是单选 %>
										  			<li>
										  				<div class="radio">
										  					<label><input type="radio" value="${answer.ans_id }" name="${answer.que_id }" aria-label="...">${answer.DAAN}</label>
										  				</div>
										  			</li>
											</c:if>
											<c:if test="${question.LEIXING eq 'checkbox'}"><%//这里是多选 %>
													<li>
										  				<div class="checkbox">
										  					<label><input type="checkbox" value="${answer.ans_id }" name="${answer.que_id }" aria-label="...">${answer.DAAN}</label>
										  				</div>
										  			</li>
											</c:if>	
										</c:if>
									</c:forEach>
									</ol>
										<br />
									</td>
								</c:when>
								<c:otherwise>
									<td style="padding-left: 40px">
										<span>暂无任何答案</span>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr class="warning">
						<td>
							<span>暂无任何问题</span>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<input type="hidden" name="main_id" value="${questions.main_id}">
	</form>
</div>

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

<div id="card_body" class="ln-guild">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h5>答题卡</h5>
			<!-- <a href="#">倒计时 <span id="t_h"></span>时<span id="t_m"></span>分<span id="t_s"></span>秒</a> -->
		</div>
		<div class="panel-body">
			<c:choose>
    			<c:when test="${not empty questions.questions }">
    				<table class="table table-bordered">
    				<c:forEach items="${questions.questions }" var="items" varStatus="vs">
    					<c:if test="${vs.index % 6 == 0 }">
							<c:set var="flagIndex" value="${vs.index + 6}"></c:set>
							<tr>
						</c:if>
    					<td >
    						<a id="dtk${vs.index }" class="btn btn-info btn-xs" href="javascript:void(0);" onclick="goIndex(${vs.index})">
    							<c:if test="${(vs.index+1)<10 }">
    								0${vs.index+1}
    							</c:if>
    							<c:if test="${(vs.index+1)>=10 }">
    								${vs.index+1}
    							</c:if>
    						</a>
    					</td>
    					<c:if test="${(vs.index eq flagIndex)||vs.last}">
							</tr>
						</c:if>
    				</c:forEach>
    				</table>
    			</c:when>
    		</c:choose>
		</div>
		<div class="panel-footer" >
			<p class="text-center"><button type="button" class="btn btn-success btn-sm" onclick="submitGo();"><span class="glyphicon glyphicon-pencil" ></span>交卷</button></p>
		</div>
	</div>
</div>
<div style="background-color: #293448;position: fixed;bottom: 0;width: 100%;" id="footer">
	<div class="container-fluid text-center" style="height: 80px;line-height: 80px;" >
		<span style="color: #ffffff;">吉林省明日科技有限公司 <a href="www.mingrisoft.com">Copyright@www.mingrisoft.com</a> AllRights Reserved 备案号:吉ICP证07500273</span>
		<span><a href="javascript:void(0);" onclick="hideFooter();">隐藏</a></span>
	</div>
</div>

    <script src="./bootstrap-3.3.5-dist/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="./bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		var basePath = '<%=basePath%>';
		var socketPath = '<%=socketPath%>';
	</script>
    <script type="text/javascript" src="<%=basePath %>js/websocket.js?V=0.2"></script>
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
    	
    	function submitGo(){
    		websocket.close();
    		$("#actionSub").submit();
    	}
    	
    	function hideFooter (){
    		$("#footer").hide();
    	}
    </script>
</body>
</html>