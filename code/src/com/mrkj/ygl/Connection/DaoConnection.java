package com.mrkj.ygl.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *         单例模式，初始化与数据的连接.
 */
public class DaoConnection {

	private static DaoConnection dc;

	private Connection JDBC连接;

	private static final String 数据库连接路径 = "jdbc:mysql://127.0.0.1:3306/mrks";
	private static final String 数据库用户名 = "root";
	private static final String 数据库密码 = "root";
	private static final String 数据库驱动类 = "com.mysql.jdbc.Driver";

	// 私有构造函数
	private DaoConnection() {
	}

	// 匿名内部类加载驱动类
	{
		try {
			Class.forName(数据库驱动类);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初始化
	public static DaoConnection initDaoConnection() {
		if (dc == null) {
			dc = new DaoConnection();
		}
		return dc;
	}

	/**
	 * @return the jDBC连接
	 * @throws SQLException
	 */
	public Connection getJDBC连接() throws SQLException {
		JDBC连接 = DriverManager.getConnection(数据库连接路径, 数据库用户名, 数据库密码);
		return JDBC连接;
	}

	public PreparedStatement get预处理执行(String sql, Object... 参数集合) throws SQLException {

		DaoConnection dc = initDaoConnection();

		PreparedStatement ps = dc.getJDBC连接().prepareStatement(sql);
		int i = 1;
		if (参数集合 != null & 参数集合.length > 0) {
			for (Object 参数 : 参数集合) {
				ps.setObject(i, 参数);
				i++;
			}
		}
		return ps;
	}

}