package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 */
@WebServlet(urlPatterns="/main",asyncSupported=false)
public class BusinessMainServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("main");
		//返回试卷编辑
		if("getMain".equals(action)){
														//easyui datagrid组件传递过来的参数page代表当前页，默认1
			String pageStr = req.getParameter("page");
														//easyui datagrid组件传递过来的参数row代表每页显示多少行，我们设置为10
			String rowStr = req.getParameter("rows");
														//转换String为Integer,之所以这里不适用int是因为在某些特定情况下参数获取为null，
														//使用Integer避免了空指针异常，这是一个有趣的小技巧
			Integer page = Integer.parseInt(pageStr);
			Integer row = Integer.parseInt(rowStr);
														//获取总共有多少行数据
			Long count = BusinessService.getMainCount();
														//计算出page的计算出当前页数从多少航开始，理解这句话，需要了解MySql分页查询语句
			if (page > 0){
				page = (page-1)*row;
			}
														//把page与row作为参数传递，最终实现分页查询
			List<Map<String, String>> model = BusinessService.getMain(page, row);
			
			String result = null;
			try {
														//这里写了一个方法，向前台返回Easyui datagrid组件需要的Json模型
				result = MrksUtils.getEasyUIDataGridModel(model, count);
			} catch (Exception e) {
				e.printStackTrace();
			}
														//这里写了一个方法，使用response把Json返回给客户端
			MrksUtils.responseWriteJson(resp, result);
		}else{
			req.getRequestDispatcher("WEB-INF/view/main.jsp").forward(req, resp);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		
		if ("add".equals(action)){
			String ID = UUID.randomUUID().toString();
			String 标题 = req.getParameter(BusinessMain.标题.toString());
			String 外键 = req.getParameter(BusinessMain.外键.toString());
			String 答题时间 = req.getParameter(BusinessMain.答题时间.toString());
			int result = BusinessService.insertMain(ID,标题,外键,答题时间);
			if (result == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else {
				MrksUtils.responseWriteJson(resp, "{\"success\":\"NO\"}");
			}
		}else if ("edit".equals(action)){
			String ID = req.getParameter(BusinessMain.ID.toString());
			String 标题 = req.getParameter(BusinessMain.标题.toString());
			String 外键 = req.getParameter(BusinessMain.外键.toString());
			String 答题时间 = req.getParameter(BusinessMain.答题时间.toString());
			int result = BusinessService.updatetMain(标题,答题时间,外键,ID);
			if (result == 1){
				MrksUtils.responseWriteJson(resp, "{\"success\":\"YES\"}");
			}else {
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
