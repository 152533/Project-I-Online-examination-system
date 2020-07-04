=<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</head>
<body>
	

	
	<!-- *******************************************************  SUBJECT 列表        *********************************************************************************** -->
	
	<div class="easyui-panel" title="" style="width:100%">
		<table id="kscsDG" class="easyui-datagrid" title="列表"
					data-options="pagination:true,singleSelect:true,collapsible:true,method:'get',pageSize:10,rownumbers:true,singleSelect:true">
		</table>

	</div>	
	<script type="text/javascript">
			
				$("#kscsDG").datagrid({
					url:'<%=basePath %>action?act=kfcx',
					columns:[[
				              {field:'BIAOTI',title:'试卷名称',width:270},
				              {field:'DATISHIJIAN',title:'答题时间',width:100,formatter:function(value, row, index){
				            	  return value+"分钟";
				              }},
				              {field:'CHUANGJIAN',title:'创建时间',width:200},
				              {field:'FENSHU',title:'分数',width:180}
				          ]]
				});
			
		</script>
	
</body>
</html>