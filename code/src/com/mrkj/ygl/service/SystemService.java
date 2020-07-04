package com.mrkj.ygl.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mrkj.ygl.Connection.DaoConnection;
import com.mrkj.ygl.entity.UserInfo;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *	1、系统服务层处理系统层面的操作，如登陆，注册，权限等等
 *
 *	2、这里我们使用的JDK7，JDBC4.1标准，该标准，Connection、ResultSet和Statement都实现了Closeable借口，所有在try-with-resources语句中调用，就可以自动关闭资源。
 */
public class SystemService{

	public Map<String,Object> selectUserInfoBy用户名 (String 用户名){
		
		Map<String,Object> 返回结果集 = new HashMap<String, Object>();
		
		try {
			DaoConnection dc = DaoConnection.initDaoConnection();
			PreparedStatement 预处理执行 = dc.get预处理执行(UserInfo.SELECTBY用户名.toString(),用户名);
			ResultSet 数据库结果集 = 预处理执行.executeQuery();
			while (数据库结果集.next()){
				返回结果集.put("id", 数据库结果集.getString(1));
				返回结果集.put(UserInfo.用户名.toString(), 数据库结果集.getString(2));
				返回结果集.put(UserInfo.密码.toString(), 数据库结果集.getString(3));
				返回结果集.put(UserInfo.姓名.toString(), 数据库结果集.getString(4));
				返回结果集.put(UserInfo.职务.toString(), 数据库结果集.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 返回结果集;
	}
	
	public int insert (String id,String 用户名,String 密码,String 姓名,String 职务){
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		int resultInt = 0;
		try {
			PreparedStatement 预处理执行 = dc.get预处理执行(UserInfo.INSERT.toString(),id,用户名,密码,姓名,职务);
			resultInt = 预处理执行.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultInt;
	}

	
}
