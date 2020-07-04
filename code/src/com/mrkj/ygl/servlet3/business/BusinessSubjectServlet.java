package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrkj.ygl.entity.BusinessSubject;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *设计思想
 *
 *	这个类处理考试科目，主要功能有分页，增加，修改，删除
 *
 *1、分页采用easyui的datagrid组件，默认传递参数page，row
 *
 */

@WebServlet(urlPatterns="/subject",asyncSupported=false)
public class BusinessSubjectServlet extends HttpServlet{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//获取ID
		String sub_id = req.getParameter(BusinessSubject.ID.toString());
		//根据ID删除数据
		int result = BusinessService.deleteById(sub_id);
		if (result == 1){
			MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
		}else{
			MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("sub");
		if (action!=null){
			if (action.equals("kmbj")){		//获取科目编辑首页
				req.getRequestDispatcher("/editSubject.jsp").forward(req, resp);
			}else if (action.equals("getSubject") ){		//获取科目分页列表
															//easyui datagrid组件传递过来的参数page代表当前页，默认1
				String pageStr = req.getParameter("page");
															//easyui datagrid组件传递过来的参数row代表每页显示多少行，我们设置为10
				String rowStr = req.getParameter("rows");
															//转换String为Integer,之所以这里不适用int是因为在某些特定情况下参数获取为null，
															//使用Integer避免了空指针异常，这是一个有趣的小技巧
				Integer page = Integer.parseInt(pageStr);
				Integer row = Integer.parseInt(rowStr);
															//获取总共有多少行数据
				Long count = BusinessService.getSubCount();
															//计算出page的计算出当前页数从多少航开始，理解这句话，需要了解MySql分页查询语句
				if (page > 0){
					page = (page-1)*row;
				}
															//把page与row作为参数传递，最终实现分页查询
				List<Map<String, String>> model = BusinessService.getSubPage(page, row);
				
				String result = null;
				try {
															//这里写了一个方法，向前台返回Easyui datagrid组件需要的Json模型
					result = MrksUtils.getEasyUIDataGridModel(model, count);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
															//这里写了一个方法，使用response把Json返回给客户端
				MrksUtils.responseWriteJson(resp, result);
				
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String 科目 = req.getParameter(BusinessSubject.科目.toString());
		String action = req.getParameter("action");
		if ("add".equals(action)){
			int isResult = BusinessService.insert(科目);
			
			if (isResult == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else{
				MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
			}
		}else if ("edit".equals(action)){
			String sub_id = req.getParameter(BusinessSubject.ID.toString());
			int isResult = BusinessService.updateById(科目, sub_id);
			if (isResult == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else{
				MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	
	
}
