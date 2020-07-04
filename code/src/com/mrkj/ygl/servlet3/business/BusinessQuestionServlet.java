package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrkj.ygl.entity.BusinessQuestion;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *设计思想
 *
 *	试卷编辑主要核心需求是一个问题有四个答案，答案分多选或单选，还要给每个答案设置正确答案，难点在于如何巧妙的设计出答案所对应正确答案存入数据库，
 *另一个难点在于分数计算，分数是用户给出，如何计算用户分数。
 *
 *解决方案
 *
 *	这里使用答案吗，答案码使用正确答案的id字符串，获取每个字符串的ASCⅡ码相加所得。
 *
 */

@WebServlet(urlPatterns="/question",asyncSupported=false)
public class BusinessQuestionServlet extends HttpServlet{

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
		
		String action = req.getParameter("que");
		//返回试卷编辑
		if ("sjbj".equals(action)){
			req.setAttribute("AllSubject", BusinessService.getAllSubPage());
			req.getRequestDispatcher("/editQuestion.jsp").forward(req, resp);
		}else if("getQuestion".equals(action)){
														//easyui datagrid组件传递过来的参数page代表当前页，默认1
			String pageStr = req.getParameter("page");
														//easyui datagrid组件传递过来的参数row代表每页显示多少行，我们设置为10
			String rowStr = req.getParameter("rows");
														//转换String为Integer,之所以这里不适用int是因为在某些特定情况下参数获取为null，
														//使用Integer避免了空指针异常，这是一个有趣的小技巧
			Integer page = Integer.parseInt(pageStr);
			Integer row = Integer.parseInt(rowStr);
														//获取总共有多少行数据
			Long count = BusinessService.getQueCount();
														//计算出page的计算出当前页数从多少航开始，理解这句话，需要了解MySql分页查询语句
			if (page > 0){
				page = (page-1)*row;
			}
														//把page与row作为参数传递，最终实现分页查询
			List<Map<String, String>> model = BusinessService.getQuestion(page, row);
			
			String result = null;
			try {
														//这里写了一个方法，向前台返回Easyui datagrid组件需要的Json模型
			result = MrksUtils.getEasyUIDataGridModel(model, count);
			} catch (Exception e) {
			
			e.printStackTrace();
			}
														//这里写了一个方法，使用response把Json返回给客户端
			MrksUtils.responseWriteJson(resp, result);
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		if ("add".equals(action)){		//增加问题
			
			String 问题标题 = req.getParameter(BusinessQuestion.问题标题.toString());  //问题元素
			String 问题类型 = req.getParameter(BusinessQuestion.问题类型.toString());	//问题元素
			String 分数 = req.getParameter(BusinessQuestion.分数.toString());	//问题元素
			String 外键 = req.getParameter(BusinessQuestion.外键.toString());	//问题元素
			
			String 答案1 = req.getParameter("DAAN1");		//答案元素
			String 答案2 = req.getParameter("DAAN2");		//答案元素
			String 答案3 = req.getParameter("DAAN3");		//答案元素
			String 答案4 = req.getParameter("DAAN4");		//答案元素
			String 答案1正确 = req.getParameter("DAAN1CHECK");		//计算答案编码
			String 答案2正确 = req.getParameter("DAAN2CHECK");		//计算答案编码
			String 答案3正确 = req.getParameter("DAAN3CHECK");		//计算答案编码
			String 答案4正确 = req.getParameter("DAAN4CHECK");		//计算答案编码
			List<String> check = new ArrayList<>();
			Map<String,String> daanMap= new HashMap<>();
			
			String UUID1 = UUID.randomUUID().toString();
			daanMap.put(UUID1, 答案1);
			String UUID2 = UUID.randomUUID().toString();
			daanMap.put(UUID2, 答案2);
			String UUID3 = UUID.randomUUID().toString();
			daanMap.put(UUID3, 答案3);
			String UUID4 = UUID.randomUUID().toString();
			daanMap.put(UUID4, 答案4);
			if (答案1正确!=null){
				check.add(UUID1);
			}
			
			if (答案2正确!=null){
				check.add(UUID2);
			}
			
			if (答案3正确!=null){
				check.add(UUID3);
			}
			
			if (答案4正确!=null){
				check.add(UUID4);
			}
			//List转换为String[],这里我们使用的是答案编码，答案编码的实现是通过UUID生成字符串，
			//转换为char[]，把char[]里的每一个元素转换为对应的ascⅡ码，把ascⅡ码相加得出答案编码
			Integer 答案编码 = MrksUtils.statistics(check.toArray(new String[check.size()]));
			
			BusinessService.InsertQuestionAndAnswers(问题标题, 问题类型, 分数, 答案编码, 外键, daanMap);
			
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
