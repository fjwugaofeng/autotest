/**
 * 这是一个如何解析Json文件的公共类
 * @author wugf
 *
 */
package com.tools;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class JsonUtil {
	/**
	 * 取得json字符串指定属性的值（非json数组）
	 * @param JsonString json的字符串信息
	 * @param JsonId json的属性
	 * @return 返回JsonString中JsonId对应的Value
	 */
	public static String getJsonValue(String jsonString, String JsonId) {
		if (jsonString==null) {
			return null;
		}else if (jsonString.equals("")) {
			return "";
		}else {
			//解析json的json字符串为jsonobject对象
			JSONObject json=JSON.parseObject(jsonString);
			//获取json指定属性的值
			return json.get(JsonId).toString();
		}
		
	}
	
	/**
	 * 解析jason，可以传入多个属性
	 * @param jsonString
	 * @param JsonId
	 * @return
	 */
	public static String getJsonValue(String jsonString, String JsonId[]) {
		if (jsonString==null) {
			return null;
		}else if (jsonString.equals("")) {
			return "";
		}else {
			String jsonstr = jsonString;
			for(String tmp:JsonId) {
				//解析json的json字符串为jsonobject对象
				JSONObject json=JSON.parseObject(jsonstr);
				jsonstr = json.get(tmp).toString();
			}
			return jsonstr;
		}
		
	}
	
	/**
	 * 返回json字符串中json数组中的指定属性的值
	 * @param jsonString json字符串
	 * @param mainJsonId json主属性
	 * @param secondJsonid json副属性
	 * @return
	 */
	public static List<String> getJsonList(String jsonString, String mainJsonId,String secondJsonid){
		List<String> jsonValues=new ArrayList<String>();
		if (jsonString==null) {
			return null;
		}
		
		//解析json的json字符串为jsonobject对象		
		JSONObject json=JSON.parseObject(jsonString);
		//获取json指定属性的值
		String jsonList=json.get(mainJsonId).toString();
		JSONArray jarray=JSONArray.parseArray(jsonList);
		for(Iterator<Object> iterator=jarray.iterator();iterator.hasNext();){
			JSONObject j=(JSONObject) iterator.next();
			jsonValues.add(j.get(secondJsonid).toString());
		}
		return jsonValues;
	}
	
	/**
	 * 得到特定属性的的json数组
	 * @param jsonString json字符串
	 * @param mainJsonId 想要得到json数组的字符串
	 * @return
	 */
	public static List<Map<String,String>> getJsonLists(String jsonString, String mainJsonId){
		List<Map<String,String>> lists=new ArrayList<Map<String,String>>();
		if (jsonString==null) {
			return null;
		}
		//假如要保留null字段的值，可以用如下的方法保留
//		jsonString  = jsonString.replaceAll("null", "'null'");
		
		//解析json的json字符串为jsonobject对象
		JSONObject json=JSON.parseObject(jsonString);
		//获取json指定属性的值
		String jsonList=json.get(mainJsonId).toString();

		//转换成json数组
		JSONArray jarray=JSONArray.parseArray(jsonList);
		for(Iterator<Object> iterator=jarray.iterator();iterator.hasNext();){
			JSONObject j=(JSONObject) iterator.next();
			Set<String> keyValues=j.keySet();
			Map<String, String> lineValues=new HashMap<String, String>();
			for(String string:keyValues){
				lineValues.put(string, j.get(string).toString());
			}
			lists.add(lineValues);
		}
		
		return lists;
	}
	
	/**
	 * 得到json数组的数据的键值对
	 * @param jsonString json字符串
	 * @return
	 */
	public static List<Map<String,String>> getJsonMapLists(String jsonString){
		List<Map<String,String>> lists=new ArrayList<Map<String,String>>();
		if (jsonString==null) {
			return null;
		}
		
		//转换成json数组
		JSONArray jarray=JSONArray.parseArray(jsonString);
		for(Iterator<Object> iterator=jarray.iterator();iterator.hasNext();){
			JSONObject j=(JSONObject) iterator.next();
			Set<String> keyValues=j.keySet();
			Map<String, String> lineValues=new HashMap<String, String>();
			for(String string:keyValues){
				lineValues.put(string, j.get(string).toString());
			}
			lists.add(lineValues);
		}
		
		return lists;
	}
	
	/**
	 * 得到json的键值对（没有json数组情况）
	 * @param jsonString json字符串
	 * @param mainJsonId 想要得到json数组的字符串
	 * @return
	 */
	public static Map<String,String> getJsonMap(String jsonString){
		Map<String,String> lists=new HashMap<String, String>();
		if (jsonString==null) {
			return null;
		}
		
		//解析json的json字符串为jsonobject对象
		JSONObject json=JSON.parseObject(jsonString);
		//得到json的key值
		Set<String> keyValues=json.keySet();
		for(String string:keyValues){
			lists.put(string, json.get(string).toString());
		}			
		return lists;
	}
	
	/**
	 * json字符串转json对象
	 * @param jsonstring
	 * @return
	 */
	public static JSONObject stringToJSON(String jsonstring) {
		return JSONObject.parseObject(jsonstring);
	}
	
	/**
	 * map转json字符串
	 * @param map
	 * @return
	 */
	public static String mapToJsonString(Map<String, Object> map) {		
		return JSON.toJSONString(map);
	}
	
	/**
	 * json字符串转map
	 * @param jsonstring
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String jsonstring){
		JSONObject  jsonObject = JSONObject.parseObject(jsonstring);
		Map<String,Object> map = (Map<String,Object>)jsonObject;
		return map;
	}
	
	/**
	 * map转json对象
	 * @param map
	 * @return
	 */
	public static JSONObject mapToJsonObject(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json;
	}
}
