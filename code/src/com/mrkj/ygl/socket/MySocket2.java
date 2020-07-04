package com.mrkj.ygl.socket;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.mrkj.ygl.base.BaseContext;
import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.entity.UserInfo;
import com.mrkj.ygl.thread.Timekeeping;

@ServerEndpoint(value = "/mysocketTest",configurator=BaseContext.class)
public class MySocket2 {
    
	private static final long serialVersionUID = 79990006013872453L;
	
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MySocket2> webSocketSet = new CopyOnWriteArraySet<MySocket2>();
    public static java.util.concurrent.ConcurrentHashMap<String , String> useronline = new java.util.concurrent.ConcurrentHashMap<String , String>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private ServletContext context = null;
    private HttpSession httpSession = null;
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @throws IOException 
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) throws IOException{
    	//WebSocket Session
        this.session = session;
        webSocketSet.add(this);
        //Servlet Application context
        context = (ServletContext)config.getUserProperties().get(ServletContext.class.getName());
        //Servlet Session
        httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        //获取Session里的用户信息
        Map<String,Object> userInfoMap = (Map<String,Object>)httpSession.getAttribute("userInof");
        //把当前用户的的的连接对象存入Context
        String 用户名 = (String)userInfoMap.get(UserInfo.用户名.toString());
        context.setAttribute(用户名, this);
       
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        //断开连接，如果断开连接我们需要把用户答题数据持久化
    	//1、清除context里的属性对象
    	//2、理想状态下计算分数
    	//3、
    	try {
    		context.removeAttribute((String)httpSession.getAttribute(UserInfo.用户名.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //用户答题时，每次回答一道题都会发送一条信息与服务器通信，这里接收用户发过来的信息。
        if (message != null){
        	switch (message) {
        	//case 1 从这步开始考试正式开始。
			case "start":
				//httpSession中存放了当前试卷的时间
				String time = (String)httpSession.getAttribute(BusinessMain.答题时间.toString());
				//分钟计算 这里是一个小时，考虑到网络延迟，这里多加了3秒，JS计时正常计算
				Long targetMinute = 1000L*60L*Long.parseLong(time);//1000L*60L*60L+3000L;
				//获取当前时间，用当前时间+上预期分钟，得到考试结束时间
				Date curretn = new Date();
				
				Long target = curretn.getTime()+targetMinute;
				
				//开始考试 ，倒计时开始时，会向客户端发送一个start状态标记，客户端使用js计时也同步开始。
				//结束考试，计时结束，会向客户端发送一个end状态吧iaoji，客户端使用js提交表单，完成一次考试。

				Timekeeping timekeeping = new Timekeeping(target,session);
				
				Thread thread = new Thread(timekeeping);
				//启动线程
				thread.start();
				break;
			//case 2 这里预期效果是用户每次选择答案都保存在内存当中
			case "question":
			//case 3 用户关闭了浏览器，会向服务器发送一条数据，这里可做持久化算分处理	
			case "close":
				System.out.println("关闭连接");
				onClose();
			default:
					break;
			}
        }
    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        //发生连接异常时
    	
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        //向客户机发送消息，如果计时完毕会向客户机发送一个状态，告诉客户机时间到了，需要提交试卷。
    	this.session.getBasicRemote().sendText(message);
        
    }
}
