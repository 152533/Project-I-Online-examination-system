package com.mrkj.ygl.thread;

import java.io.IOException;
import java.util.Date;

import javax.websocket.Session;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * The beauty of the code
 * 
 * @author Administrator
 *
 *	设计思想
 *
 *1、每个答题者，不是同一时间答题，无法统一计时，这里我们需要使用多线程为每个答题者开辟一个线程计时。
 *
 *2、该类构造时，把WebSocket Session传递进来，计时结束我们会使用它给客户机发送状态
 *
 */
public class Timekeeping implements Runnable {

	private Session session;
	
	//目标时间
	private Long target;
	
	private Long time;
	
	//构造
	public Timekeeping(Long target,Session session){
		this.session = session;
		this.target = target;
	}
	
	@Override
	public void run() {
		try {
			//发送消息给客户端，告诉客户端当前时间，与目标时间，这里我们不使用客户机本地时间，计时标准按照服务器端时间
			String resultStart = "{\"state\":\"start\"}";
			this.session.getBasicRemote().sendText(resultStart);
			while (true) {
				
				Thread.sleep(500);
				
				Date current = new Date();
				Long currentLong = current.getTime();
				ObjectMapper om = new ObjectMapper();
				
				if (currentLong >= this.target){
					//Map<String,String> resultMap = new HashMap<>();
					//resultMap.put("state", "online");
					//resultMap.put("message", "end");
					//om.writeValueAsString(resultMap);
					//给JS返回结束状态
					if (session.isOpen()){
						String resultEnd = "{\"state\":\"end\",\"message:\":\"end\"}";
						session.getBasicRemote().sendText(resultEnd);
						//跳出循环结束线程
					}
					break;	
				}else{
					if (session.isOpen()){
						String resultEnd = "{\"state\":\"time\",\"endtime\":"+this.target+",\"currentime\":"+currentLong+"}";
						session.getBasicRemote().sendText(resultEnd);
					}else {
						break;	
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    public void sendMessage(String message) throws IOException{
        //向客户机发送消息，如果计时完毕会向客户机发送一个状态，告诉客户机时间到了，需要提交试卷。
    	this.session.getBasicRemote().sendText(message);
        
    }
	
}
