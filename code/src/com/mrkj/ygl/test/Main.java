package com.mrkj.ygl.test;

import java.util.Date;

import com.mrkj.ygl.thread.Timekeeping;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Long l = 1000L*60L;
		System.out.println(new Date().getTime()+l);
	}

}
