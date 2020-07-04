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
	
	<div id="formdialog" class="easyui-dialog" data-options="closed:true,width:430,height:300,title:'新增科目'" style="display: none;">
		<div >
			<form id="addSubiectForm" action="<%=basePath %>subject" method="post" >
				<input type="hidden" value="add" name="action" />
				<table style="text-align:center;padding:5px" >
					<tr>
						<td>科目名称:</td>
						<td><input type="text" id="KEMU" name="KEMU" /></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('addSubiectForm')">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('addSubiectForm')">重置</a>
	    	</div>
	    </div>
	</div>
	
	<div id="formEditdialog" class="easyui-dialog" data-options="closed:true,width:430,height:300,title:'修改科目'" style="display: none;">
		<div >
			<form id="editSubiectForm" action="<%=basePath %>subject" method="post" >
				<input type="hidden" value="" name="sub_id" id="sub_idedit" />
				<input type="hidden" value="edit" name="action" />
				<table style="text-align:center;padding:5px" >
					<tr>
						<td>科目名称:</td>
						<td><input type="text" id="KEMUedit" name="KEMU" /></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('editSubiectForm')">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('editSubiectForm')">重置</a>
	    	</div>
	    </div>
	</div>
		<script>
			function submitForm(parm){
				$('#'+parm).form('submit',{
					onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					},
					success:function(data){
						var evData = eval('(' + data + ')');
						if (evData.success === "YES" ){
							alert ('保存成功');
						}else{
							alert ('保存失败');
						}
					}
				});
			}
			function clearForm(parm){
				$('#'+parm).form('clear');
			}
			
			function openSubjectAddDialog(){
				$("#formdialog").show();
				$("#formdialog").dialog("open");
			}
		</script>

	
	<!-- *******************************************************  SUBJECT 列表        *********************************************************************************** -->
	
	<div class="easyui-panel" title="" style="width:100%">
		<table id="editSubjectDg" class="easyui-datagrid" title="列表"
					data-options="pagination:true,singleSelect:true,collapsible:true,method:'get',pageSize:10,rownumbers:true,singleSelect:true">
		</table>
		<div id="subjectBar" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="openSubjectAddDialog()">新增</a>
		</div>
	</div>	
	<script type="text/javascript">
			
				$("#editSubjectDg").datagrid({
					url:'<%=basePath %>subject?sub=getSubject',
					toolbar: '#subjectBar',
				    columns:[[
				              {field:'KEMU',title:'科目',width:270},
				              {field:'CHUANGJIAN',title:'创建时间',width:270},
				              {field:'sub_id',title:'编辑',width:300,formatter: function(value, row, index){
				            	  return "<a href='javascript:void(0)' onclick='editSubject("+'"'+value+'","'+row.KEMU+'"'+")'>编辑</a>"
				            	  		+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='deleteSubject("+'"'+value+'"'+")'>删除</a>";
				              }}
				          ]]
				});
			
				function deleteSubject(parm){
					$.ajax({
						url: basePath+'subject?sub_id='+parm,
						type: 'DELETE',
						success: function(data){
							if(data.success === 'YES') {
								alert ('删除成功');
							}else{
								alert ('删除失败');
							}
						},
						error: function(){
							alert ('服务器无应答。');
						}
						});
				}
				
				function editSubject(sub_id,KEMU){

					$("#KEMUedit").val(KEMU);
					$("#sub_idedit").val(sub_id)
					
					$("#formEditdialog").show();
					$("#formEditdialog").dialog("open");
					
				}
				
		</script>
	
</body>
</html>