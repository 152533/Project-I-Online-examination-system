package com.mrkj.ygl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.mrkj.ygl.Connection.DaoConnection;
import com.mrkj.ygl.entity.BusinessAnswer;
import com.mrkj.ygl.entity.BusinessInfo;
import com.mrkj.ygl.entity.BusinessMain;
import com.mrkj.ygl.entity.BusinessQuestion;
import com.mrkj.ygl.entity.BusinessSubject;
import com.mrkj.ygl.util.MrksUtils;

/**
 * The beauty of the code
 * 
 * @author yuguoliang
 *
 *
 *
 */

public class BusinessService {

	public static List<Map<String, String>> getSubPage (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement sp = dc.get预处理执行(BusinessSubject.select分页.toString(), page,row);
			ResultSet rs = sp.executeQuery();
			if (rs != null){
				while (rs.next()){
					Map<String, String> reaultMap = new HashMap<String, String>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					reaultMap.put(BusinessSubject.ID.toString(), rs.getString(1));
					reaultMap.put(BusinessSubject.科目.toString(),rs.getString(2));
					Long sqlDateLong = rs.getTimestamp(3).getTime();//rs.getDate(3).getTime();
					reaultMap.put(BusinessSubject.创建时间.toString(), sdf.format(new Date(sqlDateLong)));
					resultListMap.add(reaultMap);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultListMap;
	}
	
	public static List<Map<String, String>> getAllSubPage (){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement sp = dc.get预处理执行(BusinessSubject.selectAll.toString());
			ResultSet rs = sp.executeQuery();
			if (rs != null){
				while (rs.next()){
					Map<String, String> reaultMap = new HashMap<String, String>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					reaultMap.put(BusinessSubject.ID.toString(), rs.getString(1));
					String kskmAndJk = rs.getString(2);
					String[] kskmJks = kskmAndJk.split("-");
					if (kskmJks.length==2){
						reaultMap.put(BusinessSubject.科目.toString(),kskmJks[0]);
						reaultMap.put("jiankao",kskmJks[1]);
					}else{
						reaultMap.put(BusinessSubject.科目.toString(),"未知");
						reaultMap.put("jiankao","未知");
					}
					Long sqlDateLong = rs.getTimestamp(3).getTime();//rs.getDate(3).getTime();
					reaultMap.put(BusinessSubject.创建时间.toString(), sdf.format(new Date(sqlDateLong)));
					resultListMap.add(reaultMap);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultListMap;
	}
	
	public static Long getSubCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessSubject.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static int insert (String 科目){
		int resultInt = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			String sub_id = UUID.randomUUID().toString();
			PreparedStatement ps = dc.get预处理执行(BusinessSubject.insert.toString(),sub_id,科目);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultInt;
	}

	public static int deleteById (String sub_id){
		int resultInt = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessSubject.deleteById.toString(), sub_id);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultInt;
	}
	
	public static int updateById (String 科目,String sub_id){
		int resultInt = 0;
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessSubject.update科目ById.toString(),科目 ,sub_id);
			resultInt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultInt;
	}
	/***************************************************试卷编辑***************************************************/
	
	public static List<Map<String, String>> getQuestion (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessQuestion.select分页.toString(), page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String que_id = rs.getString(1);
				String 问题标题 = rs.getString(2);
				String 问题类型 = rs.getString(3);
				Long timestamp = rs.getTimestamp(4).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String 创建时间 = sdf.format(new Date(timestamp));
				String 分数 = rs.getInt(6)+"";
				String 外键 = rs.getString(7);
				resultMap.put(BusinessQuestion.ID.toString(), que_id);
				resultMap.put(BusinessQuestion.问题标题.toString(), 问题标题);
				resultMap.put(BusinessQuestion.问题类型.toString(), 问题类型);
				resultMap.put(BusinessQuestion.创建时间.toString(),创建时间);
				resultMap.put(BusinessQuestion.分数.toString(),分数);
				resultMap.put(BusinessQuestion.外键.toString(),外键);
				resultListMap.add(resultMap);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultListMap;
	}
	
	public static Long getQueCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessQuestion.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 
	 * @param 问题标题
	 * @param 问题类型
	 * @param 分数
	 * @param 答案编码
	 * @param 外键
	 * @param daanMap
	 * @return
	 */
	public static int InsertQuestionAndAnswers (String 问题标题,String 问题类型,String 分数,int 答案编码,String 外键,Map<String,String> daanMap){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			String questionSQL = BusinessQuestion.insert.toString();
			
			Connection connection = dc.getJDBC连接();
			
			Statement statement = connection.createStatement();
			String que_id = UUID.randomUUID().toString();
			statement.addBatch("insert into bus_question (`que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,main_id) "
					+ "VALUES ('"+que_id+"','"+问题标题+"','"+问题类型+"',NOW(),"+答案编码+","+分数+",'"+外键+"')");
			Set<Entry<String, String>> daanEntry = daanMap.entrySet();
			for (Entry<String, String> entry : daanEntry){
				statement.addBatch("INSERT into bus_answer (`ans_id`,`答案内容`,`创建时间`,`que_id`) VALUES ('"+entry.getKey()+"','"+entry.getValue()+"',NOW(),'"+que_id+"')");
			}
			result = statement.executeBatch()[0];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/***************************************************试卷主表service***************************************************/
	public static List<Map<String, String>> getMain (Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessMain.select分页.toString(), page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String main_id = rs.getString(1);
				String 标题 = rs.getString(2);
				long timestamp = rs.getTimestamp(3).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String 创建时间 = sdf.format(new Date(timestamp));
				long 答题时间 = rs.getLong(4);
				String 外键 = rs.getString(5);
				String 外键value = rs.getString(6);
				resultMap.put(BusinessMain.ID.toString(), main_id);
				resultMap.put(BusinessMain.标题.toString(),标题);
				resultMap.put(BusinessMain.创建时间.toString(),创建时间);
				resultMap.put(BusinessMain.答题时间.toString(),答题时间+"");
				resultMap.put(BusinessMain.外键.toString(),外键);
				resultMap.put(BusinessMain.外键value.toString(), 外键value);
				resultListMap.add(resultMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultListMap;
	}
	
	public static Long getMainCount (){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessMain.selectCount.toString());
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	
	public static int insertMain (String ID,String 标题,String 外键,String 答题时间){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessMain.insert.toString(),ID,标题,答题时间,外键);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int updatetMain (String 标题,String 答题时间,String 外键,String ID){
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessMain.updateById.toString(),标题,答题时间,外键,ID);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 根据sub_id获取科目下所有试卷
	 * @param sub_id
	 * @return
	 */
	public static List<Map<String,String>> getMainBySubject (String sub_id){
		List<Map<String,String>> mains = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			//SELECT `main_id`,`标题`,`创建时间`,`答题时间`,`sub_id` FROM bus_main where `sub_id` = ?
			PreparedStatement ps = dc.get预处理执行(BusinessMain.selectBySub_id.toString(),sub_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> entity = new HashMap<>();
				String main_id = rs.getString(1);
				String 标题 = rs.getString(2);
				entity.put(BusinessMain.ID.toString(), main_id);
				entity.put(BusinessMain.标题.toString(), 标题);
				mains.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mains;
	}
	/************************************************Action*************************************************************/
	/**
	 * 根据 main_id 获取一份完整的试卷
	 * @param parmMain_id
	 * @return
	 */
	public static Map<String,Object> getAllQuestion (String parmMain_id){
		
		//SELECT `main_id`,`标题`,`创建时间`,`答题时间`,`sub_id` FROM bus_main where `main_id` = ?
		String selectMainSQL = BusinessMain.selectById.toString();
		/*
		 *select `que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` 
		 *from bus_question where `main_id`=? ORDER BY `创建时间`
		 */
		String selectQuestionSQL = BusinessQuestion.selectByMainId.toString();
		/*
		 * SELECT answer.ans_id,answer.`答案内容`,answer.`创建时间`,answer.que_id FROM bus_main AS main 
		 * LEFT JOIN bus_question AS question on main.main_id=question.main_id 
		 * LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?
		 */
		String selectAnswerSQL =  BusinessAnswer.selectByMainId.toString();
		int result = 0;
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		PreparedStatement ps;
		ResultSet rs;
		Map<String,Object> resultMainMap = new HashMap<>();
		List<Map<String,String>> resultQuestionsMaps = new ArrayList<>();
		List<Map<String,String>> resultAnswerMaps = new ArrayList<>();
		try {
			//selectById ("SELECT `main_id`,`标题`,`创建时间`,`答题时间`,`sub_id` FROM bus_main where main_id = ?");
			ps = dc.get预处理执行(selectMainSQL,parmMain_id);
			rs = ps.executeQuery();
			rs.next();
			
			String main_id = rs.getString(1);
			String 标题 = rs.getString(2);
			Long 创建时间 = rs.getTimestamp(3).getTime();
			String 答题时间 = rs.getString(4);
			String sub_id = rs.getString(5);
			resultMainMap.put("main_id", main_id);
			resultMainMap.put(BusinessMain.标题.toString(), 标题);
			resultMainMap.put(BusinessMain.创建时间.toString(), MrksUtils.TrasformGetimeToString(创建时间));
			resultMainMap.put(BusinessMain.答题时间 .toString(), 答题时间);
			resultMainMap.put("sub_id",sub_id);     //main 数据封装完毕
			
			//select `que_id`,`问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` from bus_question where `main_id`=? ORDER BY `创建时间`
			ps = dc.get预处理执行(selectQuestionSQL,parmMain_id);
			rs = ps.executeQuery();
			
			while (rs.next()){
				Map<String,String> question = new HashMap<>();
			
				String que_id = rs.getString(1);
				String 问题标题 = rs.getString(2);
				String 问题类型 = rs.getString(3);
				创建时间 = rs.getTimestamp(4).getTime();
				String 答案编码 = rs.getInt(5)+"";
				String 分数 = rs.getInt(6)+"";
				main_id = rs.getString(7);
				question.put("que_id", que_id);
				question.put(BusinessQuestion.问题标题.toString(),问题标题);
				question.put(BusinessQuestion.问题类型.toString(), 问题类型);
				question.put(BusinessQuestion.创建时间.toString(), MrksUtils.TrasformGetimeToString(创建时间));
				question.put(BusinessQuestion.答案编码.toString(),答案编码);
				question.put(BusinessQuestion.分数.toString(),分数);
				question.put("main_id",main_id);
				resultQuestionsMaps.add(question);
			}
			resultMainMap.put("questions", resultQuestionsMaps);
			//SELECT answer.ans_id,answer.`答案内容`,answer.`创建时间`,answer.que_id FROM bus_main AS main LEFT JOIN bus_question AS question on main.main_id=question.main_id LEFT JOIN bus_answer AS answer ON question.que_id=answer.que_id WHERE main.main_id = ?
			ps = dc.get预处理执行(selectAnswerSQL,parmMain_id);
			rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> answer = new HashMap<>();
				
				String ans_id = rs.getString(1);
				String 答案内容 = rs.getString(2);
				创建时间 = rs.getTimestamp(3).getTime();
				String que_id = rs.getString(4);
				answer.put("ans_id", ans_id);
				answer.put(BusinessAnswer.答案内容.toString(), 答案内容);
				answer.put(BusinessAnswer.创建时间.toString(), MrksUtils.TrasformGetimeToString(创建时间));
				answer.put("que_id", que_id);
				
				resultAnswerMaps.add(answer);
			}
			resultMainMap.put("answers", resultAnswerMaps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMainMap;
	}
	
	/**
	 * 计算分数
	 * @param parmMap
	 * @return
	 */
	public static int countScore (Map<String,String[]> parmMap,String username){
		
		int result = 0;
		String main_id = parmMap.get("main_id")[0];
		DaoConnection dc = DaoConnection.initDaoConnection();
		Set<Entry<String, String[]>> entrySet = parmMap.entrySet();
		int count = 0;
		for(Entry<String, String[]> entry : entrySet){
			if ("action".equals(entry.getKey())){
				break ;
			}
			String que_id = entry.getKey();
			String[] values = entry.getValue();
			try {
				//"select `问题标题`,`问题类型`,`创建时间`,`答案编码`,`分数`,`main_id` from bus_question where `que_id`=?"
				PreparedStatement ps = dc.get预处理执行(BusinessQuestion.selectById.toString(), que_id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()){
					Integer 答案编码 = rs.getInt(4);
					Integer 分数 = rs.getInt(5);
					for(Object o:values){
						System.out.println("values="+o);
					}
					
					int 转换答案编码 = MrksUtils.statistics(values);
					if (转换答案编码 == 答案编码){
						count+=分数;
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//"insert into bus_info (`info_id`,`用户名`,`分数`,`main_id`) VALUES (?,?,?,?) "
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessInfo.insert.toString(),UUID.randomUUID().toString(),username,count,main_id);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Map<String, String>> getKsfs (String username,Integer page,Integer row){
		List<Map<String, String>> resultListMap = new ArrayList<>();
		
		DaoConnection dc = DaoConnection.initDaoConnection();
		
		try {
			
			PreparedStatement ps = dc.get预处理执行(BusinessInfo.select分页.toString(),username,page,row);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Map<String,String> resultMap = new HashMap<>();
				String main_id = rs.getString(1);
				String 标题 = rs.getString(2);
				long timestamp = rs.getTimestamp(3).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String 创建时间 = sdf.format(new Date(timestamp));
				long 答题时间 = rs.getLong(4);
				String 分数 = rs.getString(6);
				resultMap.put(BusinessMain.ID.toString(), main_id);
				resultMap.put(BusinessMain.标题.toString(),标题);
				resultMap.put(BusinessMain.创建时间.toString(),创建时间);
				resultMap.put(BusinessMain.答题时间.toString(),sdf.format(new Date(答题时间)));
				resultMap.put(BusinessInfo.分数.toString(),分数);
				resultListMap.add(resultMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultListMap;
	}
	
	public static Long getInfoCount (String username){
		DaoConnection dc = DaoConnection.initDaoConnection();
		try {
			PreparedStatement ps = dc.get预处理执行(BusinessInfo.selectCount.toString(),username);
			ResultSet rs = ps.executeQuery();
			if (rs!=null){
				rs.next();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
}
