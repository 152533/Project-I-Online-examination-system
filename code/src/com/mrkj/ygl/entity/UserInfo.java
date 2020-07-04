package com.mrkj.ygl.entity;

/**
 * 
 * @author yuguoliang
 *
 *	1、MySql数据库在很早的时候就已经支持中文字段，这是一个很不错的福利，但是并没有人愿意使用，大多数人不建议使用，害怕有些不必要的麻烦，但是没人说得清楚麻烦究竟来自哪里？
 *
 *	2、我觉得这样写很舒服，干脆把SQL语句也写进来好了。
 *	
 */
public enum UserInfo {

	TABLENAME("sys_userinfo"),
	
	ID("id"),
	
	用户名("USERNAME"),
	
	密码("PASSWORD"),
	
	姓名("NAME"),
	
	职务("JOB"),

	SELECTBY用户名("select `id`,`用户名`,`密码`,`姓名`,`职务` from `sys_userinfo` where `用户名` = ?"),
	
	INSERT("insert into `sys_userinfo` (`id`,`用户名`,`密码`,`姓名`,`职务`) values (?,?,?,?,?);");
	
	
	private String 字段;
	
	private UserInfo (String field){
		this.字段 = field;
	}

	/**
	 * @return the 字段
	 */
	public String get字段() {
		return 字段;
	}

	/**
	 * @param 字段 the 字段 to set
	 */
	public void set字段(String 字段) {
		this.字段 = 字段;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.字段;
	}
}
