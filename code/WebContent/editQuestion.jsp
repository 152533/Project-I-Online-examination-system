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
<!-- ****************************************************弹出框***************************************************************** -->
	<div id="questionFormDialog" class="easyui-dialog" data-options="closed:true,width:430,height:300,title:'新增试题'" style="display: none;">
		<div >
			<form id="addQuestionForm" action="<%=basePath %>question" method="post" >
				<input type="hidden" value="add" name="action" />
				<input type="hidden" value="" name="main_id" id="mainIdAdd" />
				<table style="text-align:center;padding:5px" >
					<tr>
						<td>问题标题:</td>
						<td><input type="text" id="WENTI" name="WENTI" /></td>
					</tr>
					<tr>
						<td>问题类型:</td>
						<td>
							<select name="LEIXING">
								<option value="radio">单选题</option>
								<option value="checkbox">多选题</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>分数:</td>
						<td><input type="text" id="FENSHU" name="FENSHU" /></td>
					</tr>
					<tr>
						<td>答案1:</td>
						<td><input type="text" id="DAAN1" name="DAAN1" /><input type="checkbox" name="DAAN1CHECK">正确</td>
					</tr>
					<tr>
						<td>答案2:</td>
						<td><input type="text" id="DAAN2" name="DAAN2" /><input type="checkbox" name="DAAN2CHECK">正确</td>
					</tr>
					<tr>
						<td>答案3:</td>
						<td><input type="text" id="DAAN3" name="DAAN3" /><input type="checkbox" name="DAAN3CHECK">正确</td>
					</tr>
					<tr>
						<td>答案4:</td>
						<td><input type="text" id="DAAN4" name="DAAN4" /><input type="checkbox" name="DAAN4CHECK">正确</td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionForm('addQuestionForm')">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionForm('addQuestionForm')">重置</a>
	    	</div>
	    </div>
	</div>
	
	<div id="questionFormEditDialog" class="easyui-dialog" data-options="closed:true,width:430,height:300,title:'修改试题'" style="display: none;">
		<div >
			<form id="editQuestionForm" action="<%=basePath %>subject" method="post" >
				<input type="hidden" value="" name="sub_id" id="sub_idedit" />
				<input type="hidden" value="edit" name="action" />
				<inputtype="hidden" value="" name="main_id" />
				<table style="text-align:center;padding:5px" >
					<tr>
						<td>科目名称:</td>
						<td><input type="text" id="KEMUedit" name="KEMU" /></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionForm('editQuestionForm')">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearQuestionForm('editQuestionForm')">重置</a>
	    	</div>
	    </div>
	</div>
	
	<div id="mainFormDialog" class="easyui-dialog" data-options="closed:true,width:430,height:300,title:'增加试卷'" style="display: none;">
		<div >
			<form id="editMainForm" action="<%=basePath %>main" method="post" >
				<input type="hidden" value="" name="action" />
				<input type="hidden" value="" name="main_id" class="id">
				<table style="text-align:center;padding:5px" >
					<tr>
						<td>试卷名称:</td>
						<td><input type="text" id="BIATOadd" name="BIAOTI" /></td>
					</tr>
					<tr>
						<td>答题时间:</td>
						<td><input type="text" id="SHIJIANadd" name="DATISHIJIAN" /></td>
					</tr>
					<tr>
						<td>隶属于科目:</td>
						<td>
							<c:choose>
								<c:when test="${not empty AllSubject }">
									<select name="SUB_ID" id="selectSubject">
										<c:forEach items="${AllSubject }" var="item" varStatus="vs">
											<option value="${item.sub_id }">${item.KEMU }</option>
										</c:forEach>
									</select>
								</c:when>
							</c:choose>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionForm('editMainForm')">保存</a>
	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearQuestionForm('editMainForm')">重置</a>
	    	</div>
	    </div>
	</div>
	
		<script>
			
		
			//EasyUI 方式提交表单
			function submitQuestionForm(parm){
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
			
			function clearQuestionForm(parm){
				$('#'+parm).form('clear');
			}
			//新增窗口
			function openAddDialog(dialog){
				$("#"+dialog).find("[name='action']").val('add');
				$("#"+dialog).show();
				$("#"+dialog).dialog("open");
			}
			//编辑窗口
			function openEditDialog(dialog,id,selectid){
				//jquery 选择器 
				$("#"+dialog).find("[name='action']").val('edit');
				$("#"+dialog).find("input.id").val(id);
				$("#selectSubject").val(selectid);
				$("#"+dialog).show();
				$("#"+dialog).dialog("open");
				
			}
			//增加问题
			function openAddQuestionDialog (mainId){
				$('#questionFormDialog').show();
				$('#questionFormDialog').dialog("open");

				$("#mainIdAdd").val(mainId);
				
			}
		</script>
		
<!-- ************************************************列表********************************************************** -->
	<div class="easyui-panel" title="" style="width:100%">
		<table id="editQuestionDg" class="easyui-datagrid" title="列表"
					data-options="pagination:true,singleSelect:true,collapsible:true,method:'get',pageSize:10,rownumbers:true,singleSelect:true">
		</table>
		<div id="questionBar" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="openAddDialog('mainFormDialog')">新增</a>
		</div>
	</div>	
	<script type="text/javascript">
		$("#editQuestionDg").datagrid({
			url:'<%=basePath %>main?main=getMain',
			toolbar: '#questionBar',
		    columns:[[
		              {field:'BIAOTI',title:'试卷名称',width:270},
		              {field:'DATISHIJIAN',title:'答题时间',width:100,formatter:function(value, row, index){
		            	  return value+"分钟";
		              }},
		              {field:'CHUANGJIAN',title:'创建时间',width:200},
		              {field:'SUB_VALUE',title:'科目',width:270},
		              {field:'main_id',title:'编辑',width:270,formatter:function(value, row, index){
		            	  return "<a href='javascript:void(0)' onclick='openEditDialog("+'"mainFormDialog","'+value+'","'+row.SUB_ID+'"'+")'>编辑</a>"
	            	  		+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='deleteMain("+'"'+value+'"'+")'>删除</a>"
	            	  		+"&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='openAddQuestionDialog("+'"'+value+'"'+")'>增加问题</a>";
		              }}
		          ]]
		});
	
		function deleteQuestion(parm){
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
	</script>
</body>
</html>