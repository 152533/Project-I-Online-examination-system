package com.mrkj.ygl.servlet3.business;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.service.BusinessService;
import com.mrkj.ygl.standard.Util;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 */
@WebServlet(urlPatterns="/action",asyncSupported=false)
public class ActionQuestionServlet extends HttpServlet{

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
		
		String action = req.getParameter("act");
		
		if ("action".equals(action)){
			//获取完整试卷
			String main_id = req.getParameter("main_id");
			Map<String, Object> questions = BusinessService.getAllQuestion(main_id);
			req.setAttribute("questions", questions);
			HttpSession session = req.getSession();
			session.setAttribute(BusinessMain.答题时间.toString(), questions.get(BusinessMain.答题时间.toString()));
			req.getRequestDispatcher("/WEB-INF/view/kaoshi.jsp").forward(req, resp);
		}else if ("kskm".equals(action)){
			//获取所有科目
			List<Map<String,String>> subjects = BusinessService.getAllSubPage();
			req.setAttribute("subjects", subjects);
			req.getRequestDispatcher("/WEB-INF/view/fenlei.jsp").forward(req, resp);
		}else if ("start".equals(action)){
			//选择好科目,在选择试卷
			String subject = req.getParameter("subject");
			List<Map<String, String>> mains = BusinessService.getMainBySubject(subject);
			req.setAttribute("mains", mains);
			
			req.getRequestDispatcher("/actionStart.jsp").forward(req, resp);
		}else if ("kfcx".equals(action)){
			HttpSession session = req.getSession();
			String username = (String)session.getAttribute(UserInfo.用户名.toString());
			String pageStr = req.getParameter("page");
			String rowStr = req.getParameter("rows");
			
			if (pageStr == null){
				pageStr = "1";
			}
			
			if (rowStr == null){
				rowStr = "100";
			}
			
			Integer page = Integer.parseInt(pageStr);
			Integer row = Integer.parseInt(rowStr);
			Integer tempPage = 0;
			if (page > 0){
				tempPage = (page-1)*row;
			}
			
			List<Map<String, String>> ksfs = BusinessService.getKsfs(username, tempPage, row);
			Long count = BusinessService.getInfoCount(username);
			//分页
			Integer lastPage = 0;
			
			if (count % row >0){
				lastPage = Integer.parseInt((count / row + 1)+"");
			}else{
				lastPage = Integer.parseInt((count / row)+"");
			}
			
			String HtmlPage = Util.page(page, lastPage);
			
			req.setAttribute("HtmlPage", HtmlPage);
			req.setAttribute("ksfs", ksfs);
			req.getRequestDispatcher("WEB-INF/view/chaxun.jsp").forward(req, resp);
		}else if ("gocx".equals(action)){
			req.getRequestDispatcher("/actionKfcx.jsp").forward(req, resp);
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//把所有参数转换为Map,name为Key,values为值
		Map<String, String[]> parmMap = req.getParameterMap();
		
		HttpSession session = req.getSession();
		String username = (String)session.getAttribute(UserInfo.用户名.toString());
		
		if (BusinessService.countScore(parmMap, username)==1){

			resp.sendRedirect("action?act=kfcx");
		}else{
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
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
