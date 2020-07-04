package com.mrkj.ygl.servlet3.system;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.service.SystemService;

/***
 * 设计思想
 * 
 * 类 SystemLogin
 * 
 * 该类负责用户相关的操作，登陆、注册、退出、操作
 * 
 * 这里我们使用4种请求方式 put、delete、get、post，对每种请求各司其职。
 * 1、get请求负责路径转发，查找
 * 2、delete请求负责删除数据
 * 3、put请求负责更新
 * 4、post请求负责新增
 * 
 * 
 *
 */

@WebServlet(urlPatterns="/syslogin",asyncSupported=false)
public class SystemLogin extends HttpServlet{

	private static final SystemService 系统服务= new SystemService();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parmMap = req.getParameterMap();
		for (Entry<String, String[]> entry:parmMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue()[0]);
			
		}
	}

	/* 
	 *	
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if (session.getAttribute(UserInfo.用户名.toString())!=null){
			req.getRequestDispatcher("WEB-INF/view/main.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("login.jsp?msg=1");
		}
	}

	/* The beauty of the code
	 * 
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String 用户名 = req.getParameter(UserInfo.用户名.toString());
		String 密码 = req.getParameter(UserInfo.密码.toString());
		
		Map<String, Object> userInfoMap = 系统服务.selectUserInfoBy用户名(用户名);
		if (userInfoMap != null){
			String 取出的密码 = (String)userInfoMap.get(UserInfo.密码.toString());
			if (密码!=null&&密码.equals(取出的密码)){
				req.getSession().setAttribute("userInof", userInfoMap);
				req.getSession().setAttribute(UserInfo.用户名.toString(),userInfoMap.get(UserInfo.用户名.toString()));
				//转发视图
				req.getRequestDispatcher("WEB-INF/view/main.jsp").forward(req, resp);
			}else{
				resp.sendRedirect("login.jsp?msg=1");
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parmMap = req.getParameterMap();
		for (Entry<String, String[]> entry:parmMap.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue()[0]);
			
		}
	}

	
	
}
