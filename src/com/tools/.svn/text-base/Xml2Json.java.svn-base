package com.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by chengsheng on 2015/8/19.
 */
public class Xml2Json {
//    public static void main(String[] args) throws Exception {
//    	get_xml_json("C:\\Users\\wuy\\Desktop\\test.xml", "Record");
//
//    }
    
    public static void get_xml_json(String xmlfile,String tag) {
    	String xmlStr= readFile(xmlfile);
        Document doc= null;
        try {
			doc= DocumentHelper.parseText(xmlStr);
			@SuppressWarnings("unchecked")
			List<Element> eles = doc.getRootElement().elements(tag);
			for(Element e:eles) {
				if (e.getName().equals(tag)) {
					JSONObject json=new JSONObject();
					dom4j2Json(e,json);
//					System.out.println(json.toJSONString());
//					System.out.println(XmlJsonUtil.JsonToXml(json.toJSONString()));
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * 读取文件
     * @param path
     * @return
     */
    public static String readFile(String path){
        File file=new File(path);
        String str = null;
        FileInputStream fis = null;
        FileChannel fc = null;
        ByteBuffer bb = null;
        try {
			fis = new FileInputStream(file);
			fc = fis.getChannel();
			bb = ByteBuffer.allocate(new Long(file.length()).intValue());
			//fc向buffer中读入数据
			fc.read(bb);
			str=new String(bb.array(),"UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bb.flip();
				if (fc != null) {
					 fc.close();
				}
				if (fis != null) {
				     fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return str;
    }
    
    /**
     * xml转json
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException{
        Document doc= DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element,JSONObject json){
        //如果是属性
        for(Object o:element.attributes()){
            Attribute attr=(Attribute)o;
            if(!isEmpty(attr.getValue())){
                json.put("@"+attr.getName(), attr.getValue());
            }
        }
        @SuppressWarnings("unchecked")
		List<Element> chdEl=element.elements();
        if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for(Element e:chdEl){//有子元素
            if(!e.elements().isEmpty()){//子元素也有子元素
                JSONObject chdjson=new JSONObject();
                dom4j2Json(e,chdjson);
                Object o=json.get(e.getName());
                if(o!=null){
                    JSONArray jsona=null;
                    if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
                        JSONObject jsono=(JSONObject)o;
                        json.remove(e.getName());
                        jsona=new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if(o instanceof JSONArray){
                        jsona=(JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }else{
                    if(!chdjson.isEmpty()){
                        json.put(e.getName(), chdjson);
                    }
                }


            }else{//子元素没有子元素
                for(Object o:element.attributes()){
                    Attribute attr=(Attribute)o;
                    if(!isEmpty(attr.getValue())){
                        json.put("@"+attr.getName(), attr.getValue());
                    }
                }
                if(!e.getText().isEmpty()){
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
}