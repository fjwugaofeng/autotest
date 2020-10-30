package com.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	CloseableHttpClient httpclient;
    CloseableHttpResponse httpResponse;
    
 /*   public static void main(String[] args) {
    	String url = "http://api.tianapi.com/txapi/mobilelocal/index?key=8f1c18c1897be53a0aa22c260e707f62&phone=13599920063";
    	HttpUtils hu = new HttpUtils();
    	hu.sendHttpGet(url);
    	System.out.println(hu.getCodeInNumber());
    	String json = hu.getBodyInJSON().toString();
    	System.out.println(json);
    	System.out.println(JsonUtil.getJsonMapLists(JsonUtil.getJsonValue(json, "newslist")).get(0).get("mobilearea"));
//    	System.out.println(JsonUtil.getJsonValue(JsonUtil.getJsonMapLists(JsonUtil.getJsonValue(json, "newslist")).get(0).toString(), "mobilearea"));
    	System.out.println(hu.getHeaderInHash().toString());
    	
    	//构建post请求
    	//用NameValuePair的list来添加请求主体参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key", "8f1c18c1897be53a0aa22c260e707f62"));
        params.add(new BasicNameValuePair("phone", "15959713461"));
      //用哈希图准备请求头部信息
        Map<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        
        hu.sendHttpPost("http://api.tianapi.com/txapi/mobilelocal/index", params, hashHead);
        System.out.println(hu.getCodeInNumber());
    	json = hu.getBodyInJSON().toString();
    	System.out.println(json);
    	System.out.println(JsonUtil.getJsonMapLists(JsonUtil.getJsonValue(json, "newslist")).get(0).get("mobilearea"));
//    	System.out.println(JsonUtil.getJsonValue(JsonUtil.getJsonMapLists(JsonUtil.getJsonValue(json, "newslist")).get(0).toString(), "mobilearea"));
    	System.out.println(hu.getHeaderInHash().toString());
    	
    	String str = "{\"age\":\"24\",\"name\":\"cool_summer_moon\"}";
    	System.out.println(JsonUtil.stringToJSON(str).toString());
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("a", "123");
    	map.put("b", "456");
    	System.out.println(JsonUtil.mapToJsonString(map));
    	System.out.println(JsonUtil.jsonToMap(JsonUtil.stringToJSON(str).toString()));
    	
    }*/
    
    //通过httpclient获取请求的反馈
//    public void getResponse(String url) throws ClientProtocolException, IOException{        
//        httpclient = HttpClients.createDefault();
//        httpGet = new HttpGet(url);
//        httpResponse = httpclient.execute(httpGet);
//    }
    
    public HttpUtils() {
    	httpclient = HttpClients.createDefault();
    }
    
    /**
     * 发送http post请求
     * @param url 请求的url
     * @param params 请求url的参数
     * @param headers 请求的url的头信息
     */
    public void sendHttpPost(String url, List<NameValuePair> params, Map<String, String> headers) {
    	try {
			//创建post请求对象
			HttpPost httpPost = new HttpPost(url);
			//设置请求主体格式
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			//设置头部信息
	        Set<String> set = headers.keySet();
	        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
	            String key = iterator.next();
	            String value = headers.get(key);
	            httpPost.addHeader(key, value);
	        }
	        httpResponse = httpclient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 发送http get请求
     * @param url
     */
    public void sendHttpGet(String url) {
    	try {
    		HttpGet httpGet = new HttpGet(url);
			httpResponse = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 得到响应实体
     * @return
     */
    public JSONObject getBodyInJSON(){
        HttpEntity entity;
        String entityToString;
        JSONObject responseBody =null;
        entity = httpResponse.getEntity();
        try {
			entityToString = EntityUtils.toString(entity);
			responseBody = JSON.parseObject(entityToString);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return responseBody;
    }
    
    /**
     * 得到头信息，返回一个map
     * @return
     */
    public Map<String, String> getHeaderInHash(){
        Header[] headers;
        headers = httpResponse.getAllHeaders();
        
        Map<String , String>responseHeads = new HashMap<String, String>();
        
        for(Header header:headers){
            responseHeads.put(header.getName(), header.getValue());
        }
        
//        System.out.println("This is your response header" + responseHeads);
        
        return    responseHeads;
    }
    
    /**
     * 得到状态码
     * @return
     */
    public int getCodeInNumber(){
        int responseCode = httpResponse.getStatusLine().getStatusCode();
        
//        System.out.println("This is your response code" + responseCode);
        
        return responseCode;
    }

}
