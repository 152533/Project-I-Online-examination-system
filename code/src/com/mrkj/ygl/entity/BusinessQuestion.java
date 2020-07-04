package com.mrkj.ygl.entity;

public enum BusinessQuestion {

	TABLENAME("bus_question"),
	
	ID("que_id"),
	
	问题标题("WENTI"),
	
	问题类型("LEIXING"),
	
	创建时间("CHUANGJIAN"),
	
	答案编码("BIANMA"),
	
	分数("FENSHU"),
	
	外键("main_id"),
	
	select分页("select `que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` from bus_question ORDER BY `创建时间` LIMIT ?,?"),
	
	selectCount("select count(`que_id`) as count from bus_question"),
	
	insert("insert into bus_question (`que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,main_id) VALUES (?,?,?,NOW(),?,?,?)"),
	
	selectByMainId("select `que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` from bus_question where `main_id`=? ORDER BY `创建时间`"),
	
	selectById("select `问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` from bus_question where `que_id`=?");
	
	private String 字段;
	
	private BusinessQuestion (String field){
		this.字段 = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.字段;
	}
}
