<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript" src="<%=basePath %>bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px;vertical-align: bottom;display: table-cell;">
		
	</div>

	<div data-options="region:'east',split:true,collapsed:false,title:'答题卡'" style="width:350px;">
		<div id="socketacc" class="easyui-accordion" ">
	    	答题卡
    		<c:choose>
    			<c:when test="${not empty questions.questions }">
    				<table border="1">
    				<c:forEach items="${questions.questions }" var="question" varStatus="vs">
    					<c:if test="${(vs.index+1)%6 == 0 }"><tr></c:if>
    					<td id="td${question.que_id }" width="32" height="32" style="background-color: yellow;text-align: center;"><a href="#${question.que_id }">${vs.index+1}</a></td>
    					<c:if test="${(vs.index+1)%6 == 0 }"></tr></c:if>
    				</c:forEach>
    				</table>
    			</c:when>
    		</c:choose>
		</div>
	</div>
	
	<div data-options="region:'center',title:'试卷区域'" id="center">
		<!-- ************************************************** Start *************中间区域***********************************************************************-->
			<div class="container" style="padding-bottom: 70px;">
				<form id="actionSub" action="<%=basePath %>action" method="post">
					<table class="table">
						<tr class="danger">
							<th>
								<div class="row">
									<div class="col-xs-6">
										<h4 style="padding-left: 40px">${questions.BIAOTI}</h4>
									</div>
									<div class="col-xs-6">剩余
									    <span id="t_h">0</span>时
									    <span id="t_m">0</span>分
									    <span id="t_s">0</span>秒
									</div>
								</div>			
							</th>
						</tr>
						<c:choose>
							<c:when test="${not empty questions.questions }">
								<c:forEach items="${questions.questions }" var="question" varStatus="vs">
									<tr class="info">
										<th >
											<div class="row">
												<div class="col-xs-12">
													<a name="${question.que_id }"></a>
													<h4 style="padding-left: 40px">
													<small>问题 ${vs.index+1 }：${question.WENTI }</small>
													<p class="text-right"><a href="javascript:void(0)" onclick="tdflag('${question.que_id }')"><small>不确定记录</a></small></p>
													</h4>
												</div>
											</div>
										</th>
									</tr>
									<tr class="success">
										<c:choose>
											<c:when test="${not empty questions.answers }">
												<td style="padding-left: 40px"><%//答案开始咯 %>
												<br />
												<c:forEach items="${questions.answers}" var="answer" varStatus="vs">
													<c:if test="${question.que_id eq answer.que_id }">
														<c:if test="${question.LEIXING eq 'radio'}"><%//这里是单选 %>
															<div class="row">
																<div class="col-xs-12">
																	<label>
																		<input type="radio" value="${answer.ans_id }" name="${answer.que_id }" >
																		<span style="padding-right: 45px">${answer.DAAN}</span>
																	</label>
																</div>
															</div>
															<br>
														</c:if>
														<c:if test="${question.LEIXING eq 'checkbox'}"><%//这里是多选 %>
															<div class="row">
																<div class="col-xs-12">
																	<label>
																		<input type="checkbox" value="${answer.ans_id }" name="${answer.que_id }" >
																		<span style="padding-right: 45px">${answer.DAAN}</span>
																	</label>
																</div>
															</div>
															<br>
														</c:if>
													</c:if>
												</c:forEach>
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
					<div id = "send" >
						<button type="submit" class="btn btn-primary" >交卷</button>
					</div>
				</form>
			</div>
			
		<!-- ************************************************** End   *************中间区域***********************************************************************-->
	</div>
	<div id="tempdialog" class="easyui-dialog" title="" style="width:550px;height:200px;"
        data-options="closed:true">
    	<div  id="sendcontext"></div>
    	<div >
    		SYSTEM
    	</div>
	</div>	
	<script type="text/javascript" src="<%=basePath %>js/websocket.js?V=0.2"></script>
	<script type="text/javascript">
		function tdflag(id){
			var backgroundColor = $("#td"+id).css("background-color");
			if (backgroundColor == 'rgb(255, 255, 0)'){
				
				$("#td"+id).css("background-color","red");
			}else{
				$("#td"+id).css("background-color","yellow");
			}
		}
	</script>
</body>

</html>