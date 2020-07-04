package com.mrkj.ygl.entity;

public enum BusinessSubject {

	TABLENAME("bus_subject"),
	
	ID("sub_id"),
	
	科目("KEMU"),
	
	创建时间("CHUANGJIAN"),
	
	select分页("select `sub_id`,`科目`,`创建时间` from bus_subject ORDER BY `创建时间` LIMIT ?,?"),
	
	selectAll("select `sub_id`,`科目`,`创建时间` from bus_subject ORDER BY `创建时间`"),
	
	selectCount("select count(`sub_id`) as count from bus_subject"),
	
	insert("insert into bus_subject (`sub_id`,`科目`,`创建时间`) VALUES (?,?,NOW())"),
	
	deleteById("delete FROM bus_subject where sub_id = ?"),
	
	update科目ById("UPDATE bus_subject set `科目` = ? where `sub_id` = ?");
	
	
	private String 字段;
	
	private BusinessSubject(String field){
		this.字段 = field;
	}
	
	@Override
	public String toString() {
		return this.字段;
	}
}
