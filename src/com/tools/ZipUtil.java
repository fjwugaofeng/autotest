/**
 * 这是一个解压缩文件的工具类
 */
package com.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtil {
	    private static final int buffer = 2048;  
	    
	  /** 
	   * 解压Zip文件 
	   * @param path 文件目录 
	   */  
	  public static void unZip(String path)  
	      {  
	       int count = -1;  
	       String savepath = "";  

	       File file = null;  
	       InputStream is = null;  
	       FileOutputStream fos = null;  
	       BufferedOutputStream bos = null;  

//	       savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //保存解压文件目录  
//	       new File(savepath).mkdir(); //创建保存目录 
	       
	       
	       if (path.contains("/")) {
	    	   savepath=path.substring(0,path.lastIndexOf('/'))+ "/";
		}else if (path.contains("\\")) {
			savepath=path.substring(0,path.lastIndexOf('\\'))+ '\\';
		}
	       ZipFile zipFile = null;  
	       try  
	       {  
	           zipFile = new ZipFile(path,"UTF-8"); //解决中文乱码问题  
	           Enumeration<?> entries = zipFile.getEntries();  

	           while(entries.hasMoreElements())  
	           {  
	               byte buf[] = new byte[buffer];  

	               ZipEntry entry = (ZipEntry)entries.nextElement();  

	               String filename = entry.getName();  
	               boolean ismkdir = false;  
	               if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
	                  ismkdir = true;  
	               }  
	               filename = savepath + filename;  

	               if(entry.isDirectory()){ //如果是文件夹先创建  
	                  file = new File(filename);  
	                  file.mkdirs();  
	                   continue;  
	               }  
	               file = new File(filename);  
	               if(!file.exists()){ //如果是目录先创建  
	                  if(ismkdir){  
	                  new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
	                  }  
	               }  
	               file.createNewFile(); //创建文件  

	               is = zipFile.getInputStream(entry);  
	               fos = new FileOutputStream(file);  
	               bos = new BufferedOutputStream(fos, buffer);  

	               while((count = is.read(buf)) > -1)  
	               {  
	                   bos.write(buf, 0, count);  
	               }  
	               bos.flush();  
	               bos.close();  
	               fos.close();  

	               is.close();  
	           }  

	           zipFile.close();  

	       }catch(IOException ioe){  
	           ioe.printStackTrace();  
	       }finally{  
	              try{  
	              if(bos != null){  
	                  bos.close();  
	              }  
	              if(fos != null) {  
	                  fos.close();  
	              }  
	              if(is != null){  
	                  is.close();  
	              }  
	              if(zipFile != null){  
	                  zipFile.close();  
	              }  
	              }catch(Exception e) {  
	                  e.printStackTrace();  
	              }  
	          } 
	      }
	}
