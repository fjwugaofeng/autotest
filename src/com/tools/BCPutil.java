package com.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.*;


public class BCPutil {

	public static void main(String[] args) {
		BCPutil.getBCPdata("D:/bcpToxls/137-705420237-000000-000000-1486486791-00001-MY/", "D:/bcpToxls/xls/");
	}
	
	/**
	 * 依据bcp中GAB_ZIP_INDEX.xml映射关系把bcp文件写入到excel
	 * @param path bcp解压后所在的目录，目录结尾要多一个/
	 * @param xlsPath 把bcp写入到的目录，目录结尾也要添加一个/
	 */
	public static void getBCPdata(String path,String xlsPath){
//		List<String[]> bcpValues=null;
		try {
			 SAXReader reader = new SAXReader();
			 Document   document = reader.read(new File(path+"GAB_ZIP_INDEX.xml"));
			 //目前bcp的格式为这样
			 List list=document.getRootElement().element("DATASET").element("DATA").element("DATASET").elements("DATA");
			 System.out.println(list.size());
			 for (Iterator it = list.iterator(); it.hasNext();) { 
				 List<String[]> bcpValues=new ArrayList<String[]>();
				 Element elm = (Element) it.next();
				 List list2=elm.elements("ITEM");
//				 System.out.println(list2.size());
				 List<String> baseConfire=new ArrayList<String>();
				 List<String> fileConfire=new ArrayList<String>();
				 List<String> atts=new ArrayList<String>();
				 List<String> attschn=new ArrayList<String>();
				 List<String> attskey=new ArrayList<String>();
				 for(int i=0;i<list2.size();i++){
					 baseConfire.add(((Element)list2.get(i)).attributeValue("val"));
				 }
				 
				 List list3=elm.elements("DATASET");
				 
				 List list5= ((Element)list3.get(0)).element("DATA").elements();
				 for(int i=0;i<list5.size();i++){
					 fileConfire.add(((Element)list5.get(i)).attributeValue("val"));
				 }
				 
				 List list4= ((Element)list3.get(1)).element("DATA").elements();
				 for(int i=0;i<list4.size();i++){
					 atts.add(((Element)list4.get(i)).attributeValue("eng"));
					 attschn.add(((Element)list4.get(i)).attributeValue("chn"));
					 attskey.add(((Element)list4.get(i)).attributeValue("key"));
				 }
//				 bcpValues.add((String[])atts.toArray());
//				 System.out.println(fileConfire.get(1));
				 
				 File f=new File(path+fileConfire.get(1));
				 if (!f.exists()) {
					 System.out.println(path+fileConfire.get(1)+"文件不存在");
					continue;
				}
				 InputStreamReader isr=new InputStreamReader(new FileInputStream(f), "utf-8");
				 BufferedReader br=new BufferedReader(isr);
				 
				 int startNum=1;
				 try {
					startNum=Integer.parseInt(baseConfire.get(5));
				} catch (Exception e) {
					e.printStackTrace();
				}				 
				 
				 //判断从第几行开始读
				 for(int i=0;i<startNum-1;i++){
					 br.readLine();
				 }
				 
				 //加入属性到第一行
				 bcpValues.add(atts.toArray(new String[atts.size()]));
				//加入chn属性到第二行
				 bcpValues.add(attschn.toArray(new String[attschn.size()]));
				//加入key属性到第三行
				 bcpValues.add(attskey.toArray(new String[attskey.size()]));
				 
				 String readline="";
				 while((readline=br.readLine())!=null){
					 String attValues[]=readline.split(baseConfire.get(0));
					 //判断数据的列数是否和配置的一样
					 if(attValues.length>atts.size()){
						 String newAttValues[]=new String[atts.size()];
						 for(int j=0;j<atts.size();j++){
							 newAttValues[j]=attValues[j];
						 }
						 attValues=newAttValues;
						 System.out.println("index中的数据型值和数据中的不一致，只取前面"+atts.size()+"个");
					 }else {
						 bcpValues.add(attValues);
					}					 
				 }
				 
//				 for(int i=0;i<bcpValues.size();i++){
//					 String tem[]=bcpValues.get(i);
//					 for(String string:tem){
//						 System.out.print(string+"\t");
//					 }
//					 System.out.println();
//				 }

				 XlsOpreation xo=new XlsOpreation();
				 File bcpDir=new File(xlsPath);
				 if(!bcpDir.exists()){
					 bcpDir.mkdirs();
				 }
				 xo.UpdateXls_ArrayList(0, xlsPath+baseConfire.get(2)+".xls", bcpValues);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
