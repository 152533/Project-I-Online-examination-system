<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px;vertical-align: bottom;display: table-cell;">
		
	</div>
	<div data-options="region:'west',split:true,title:'导航树'" style="width:250px;">
		<div class="easyui-accordion" >
			<div title="考试工具栏">
				<ul id="treeAction" class="easyui-tree">
			        <li>
			            <span>考试科目</span>
			        </li>
			        <li>
			        	<span>考分查询</span>
			        </li>
			    </ul>
			</div>
			<div title="出题工具栏">
				<ul id="treeQuestion" class="easyui-tree">
			        <li>
			            <span>试卷编辑</span>
			        </li>
			        <li>
			        	<span>科目编辑</span>
			        </li>
			    </ul>
			</div>
		</div>
	</div>
	<!-- <div data-options="region:'east',split:true,collapsed:false,title:'答题卡'" style="width:350px;">
		<div id="socketacc" class="easyui-accordion" ">
	    	答题卡区域
		</div>
	</div> -->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">底部</div>
	<div data-options="region:'center',title:'工作空间'" id="center">
		<div id="tt" class="easyui-tabs" >
	    	<div title="主页" style="padding:20px;">
				考试指南
			</div>
		</div>
	</div>
	<div id="tempdialog" class="easyui-dialog" title="" style="width:550px;height:200px;"
        data-options="closed:true">
    	<div  id="sendcontext"></div>
    	<div >
    		SYSTEM
    	</div>
	</div>	
	<%-- <script type="text/javascript" src="<%=basePath %>js/websocket.js?V=0.4"></script> --%>
	<script type="text/javascript">

		$("#treeQuestion").tree({
			onDblClick:function (node){
				var url = '';
				if (node.text == '试卷编辑'){
					url = '<%=basePath%>question?que=sjbj';
				}else if (node.text == '科目编辑'){
					url = '<%=basePath%>subject?sub=kmbj';
				}
				
				var tempTab = $('#tt').tabs('getTab',node.text);
				
				if (tempTab){
					var index = $('#tt').tabs('getTabIndex', tempTab);
					$('#tt').tabs('close', index);
				}
				
					$('#tt').tabs('add',{
					    title:node.text,
					    href:url,
					    closable:true,
					    tools:[{
					        iconCls:'icon-mini-refresh',
					        handler:function(){
					        	var pp = $('#tt').tabs('getSelected');
					        	pp.panel('refresh');
					        }
					    }]
					});
				
			}
		});
		
		
		$("#treeAction").tree({
			onDblClick:function (node){
				var url = '';
				if (node.text == '考试科目'){
					url = '<%=basePath%>action?act=kskm';
				}else if (node.text == '考分查询'){
					url = '<%=basePath%>action?act=gocx';
				}
				
				var tempTab = $('#tt').tabs('getTab',node.text);
				
				if (tempTab){
					var index = $('#tt').tabs('getTabIndex', tempTab);
					$('#tt').tabs('close', index);
				}
				
				$('#tt').tabs('add',{
				    title:node.text,
				    href:url,
				    closable:true,
				    tools:[{
				        iconCls:'icon-mini-refresh',
				        handler:function(){
				        	var pp = $('#tt').tabs('getSelected');
				        	pp.panel('refresh');
				        }
				    }]
				});
			}
		});
		
		function goStart (obj){
			var val = obj.value;
			if (val != 0){
			var pp = $('#tt').tabs('getSelected');
			$('#tt').tabs('update',{
				tab:pp,
				options:{
					href:"<%=basePath%>action?act=start&subject="+val
				}
			})
			pp.panel('refresh');
			}
		}
	</script>
</body>

</html>