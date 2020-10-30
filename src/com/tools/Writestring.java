/**
 * 编写日志类
 */
package com.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writestring {
	File log=null;
	FileWriter fw=null;
	BufferedWriter bw=null;
//	SimpleDateFormat sdf=null;
//	String filePath=null;
	public Writestring(String filePath){
//		this.filePath=filePath;
//		this.sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		//这个文件没有关闭，要在调用这个类结束之后关闭log文件
		this.log=new File(filePath);
		try {
			if(this.log.exists()){							
				this.log.delete();
			}/*else{
				this.log.createNewFile();
			}*/
			this.fw=new FileWriter(this.log);
			this.bw=new BufferedWriter(this.fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile(){
		try {
			if(this.bw!=null) this.bw.close();
			if(this.fw!=null) this.fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String string){
		try {
			this.bw.write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
