package com.mrkj.ygl.entity;

public enum BusinessMain {

	TABLENAME("bus_main"),
	
	ID("main_id"),
	
	标题("BIAOTI"),
	
	创建时间("CHUANGJIAN"),
	
	答题时间("DATISHIJIAN"),
	
	外键("SUB_ID"),
	
	外键value("SUB_VALUE"),
	
	select分页("select main.`main_id`,main.`标题`,main.`创建时间`,main.`答题时间`,main.`sub_id`,subject.`科目` from bus_main as main LEFT JOIN bus_subject as subject on main.sub_id = subject.sub_id ORDER BY `创建时间` LIMIT ?,?"),
	
	selectCount("select count(`main_id`) as count from bus_main"),
	
	insert ("insert into bus_main (`main_id`,`标题`,`创建时间`,`答题时间`,`sub_id`) values (?,?,NOW(),?,?)"),
	
	updateById ("UPDATE bus_main SET `标题` = ? ,`答题时间` = ? , `sub_id` = ? WHERE `main_id` = ?"),
	
	selectById ("SELECT `main_id`,`标题`,`创建时间`,`答题时间`,`sub_id` FROM bus_main where `main_id` = ?"),
	
	selectBySub_id ("SELECT `main_id`,`标题`,`创建时间`,`答题时间`,`sub_id` FROM bus_main where `sub_id` = ?");
	
	private String 字段;
	
	private BusinessMain (String field){
		this.字段 = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.字段;
	}
}
