package com.mrkj.ygl.entity;

public enum BusinessInfo {

	TABLENAME("bus_info"),
	
	ID("info_id"),
	
	用户名("USERNAME"),
	
	分数("FENSHU"),
	
	外键("main_id"),
	
	insert("insert into bus_info (`info_id`,`用户名`,`分数`,`main_id`) VALUES (?,?,?,?) "),
	
	select分页("select main.`main_id`,main.`标题`,main.`创建时间`,main.`答题时间`,main.`sub_id`,info.`分数` from bus_main as main RIGHT JOIN bus_info as info on main.main_id = info.main_id WHERE info.`用户名`=? ORDER BY `创建时间` LIMIT ?,?"),
	
	selectCount("select count(`info_id`) as count from bus_info where `用户名`= ?"),;
	
	
	private String 字段;
	
	private BusinessInfo(String field){
		this.字段 = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.字段;
	}
}
