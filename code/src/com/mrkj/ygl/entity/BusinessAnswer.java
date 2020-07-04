package com.mrkj.ygl.entity;

public enum BusinessAnswer {

	TABLENAME("bus_answer"),
	
	ID("ans_id"),
	
	答案内容("DAAN"),
	
	创建时间("CHUANGJIAN"),
	
	外键("que_id"),
	
	selectByMainId("SELECT answer.ans_id,answer.`答案内容`,answer.`创建时间`,answer.que_id FROM bus_main AS main LEFT JOIN bus_question AS question on main.main_id=question.main_id LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?");
	
	private String 字段;
	
	private BusinessAnswer(String field){
		this.字段 = field;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.字段;
	}
}