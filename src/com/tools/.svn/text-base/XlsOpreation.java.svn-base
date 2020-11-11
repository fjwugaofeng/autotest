/**
 * 这个是一个操作excel的类
 */
package com.tools;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.write.*;

public class XlsOpreation {
	Workbook wb=null;
	WritableWorkbook wwk=null;
	
	/**
	 * 新建一个Excel文件
	 * @param path 为文件路径
	 * @param sheetName为sheet的名字
	 */
	public void newXLS(String path)
	{
		File file=new File(path);
		String fileName=getFileName(path);
		try {
			wwk=Workbook.createWorkbook(file);
			String temfileName=fileName;
			if(temfileName.contains("+")){
				temfileName=temfileName.substring(0,temfileName.indexOf("+"));
			}
			WritableSheet ws=wwk.createSheet(temfileName, 0);
			Label label=new  Label(0, 0, "");
			ws.addCell(label);
			wwk.write();
			wwk.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
	}
	
	/**
	 * 向指定的单元格输入内容
	 * @param i 横坐标，从0开始
	 * @param j 纵坐标，从0开始
	 * @param k 操作的sheet，从0开始
	 * @param path 操作的excel文件的路径（包括文件名）
	 * @param result 测试结果
	 */
	public void UpdateXls(int i,int j,int k,String path,String result)
	{
		try {
			File file=new  File(path);
			if(!file.exists()) {
				newXLS(path);
				}
	
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
	//		for(int count=0;count<al.size();count++)
	//		{
	//			String string[]=al.get(count);
	//			for(int stringNum=0;stringNum<string.length;stringNum++)
	//			{
	//				Label c=new Label();
	//			}
	//		}
			Label c=new Label(j,i,result);
			ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 把ArrayList<String []>的数据写入excel
	 * @param k 要操作的sheet的ID,从0开始
	 * @param path 要操作的excel文件的路径
	 * @param al 为要写入的数据集合
	 */
	public void UpdateXls_ArrayList(int k,String path,List<String []> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()){
				newXLS(path);
			}
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int count=0;count<al.size();count++)
			{
				String string[]=al.get(count);
				for(int stringNum=0;stringNum<string.length;stringNum++)
				{
					Label c=new Label(stringNum,count,string[stringNum]);
					ws.addCell(c);
				}
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void UpdateXls_ArrayList(String k,String path,List<String []> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()){
				newXLS(path);
			}
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int count=1;count<al.size()+1;count++)
			{
				String string[]=al.get(count);
				for(int stringNum=0;stringNum<string.length;stringNum++)
				{
					Label c=new Label(stringNum,count,string[stringNum]);
					ws.addCell(c);
				}
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 把ArrayList<ArrayList<String>>中的数据写入到excel
	 * @param k sheetID
	 * @param sheetName sheetName
	 * @param path file path
	 * @param al 要写入的ArrayList集合,集合中存放ArrayList,后面的存放String
	 */
	public void UpdateXls_ArrayList_List(int k,String sheetName,String path,List<List<String>> al)
	{
		
		try {
			File file=new  File(path);
			if(file.exists()) {
				file.delete();
			}
			newXLS(path);
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int count=0;count<al.size();count++)
			{
				List<String> al1=al.get(count);
				for(int stringNum=0;stringNum<al1.size();stringNum++)
				{
					Label c=new Label(stringNum,count,al1.get(stringNum));
					ws.addCell(c);
				}
				al1.clear();
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 在原来的excel上接着写入数据
	 * @param k
	 * @param path
	 * @param al
	 */
	public void UpdateXls_ArrayList_List_WriteNew(int k,String path,ArrayList<String[]> al)
	{
		
			File file=new  File(path);
			if (!file.exists()) {
				newXLS(path);
			}
			try {
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			int startIndex=ws.getRows();
			for(int count=startIndex,i=0;count<(al.size()+startIndex);count++,i++)
			{
				String  []al1=al.get(i);
				for(int stringNum=0;stringNum<al1.length;stringNum++)
				{
					Label c=new Label(stringNum,count,al1[stringNum]);
					ws.addCell(c);
				}
				
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 这个函数和上面的一样，只是判断写入的数
	 * @param k
	 * @param path
	 * @param al
	 */
	public void UpdateXls_ArrayList_List_WriteNew_Second(int k,String path,List<String[]> al)
	{
		
			File file=new  File(path);
			try {
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
//			Sheet sheet =wb.getSheet(k);
			WritableSheet ws=wwk.getSheet(k);
			int startIndex=ws.getRows();
			
			//得到第一行的数(即属性名)
			List<String> ls=new ArrayList<String>();
			int columnnum=ws.getColumns();
			for(int i=0;i<columnnum;i++){
				ls.add(ws.getCell(i, 0).getContents());
			}
			
			//判断新的属性是否在原来就有
			String newString[]=al.get(0);
			List<String> ls1=new ArrayList<String>();
			for(int i=0;i<newString.length;i++){
//				if(!ls.contains(newString[i])){
//					ls1.add(newString[i]);
//				}
				boolean isnew=true;
				for(int j=0;j<ls.size();j++){
					if(ls.get(j).equalsIgnoreCase(newString[i])){
						isnew=false;
						break;
					}
				}
				if(isnew){
					ls1.add(newString[i]);
				}
			}
			
			//定义一个所有属性的数组；
			String allData[]=new String[columnnum+ls1.size()];
			for(int i=0;i<ls.size();i++){
				allData[i]=ls.get(i);
			}
			
			//判断新增加的属性是否为空，不为空就把它加到新的列表
			if(ls1.size()!=0){
				for(int i=0;i<ls1.size();i++){
					Label c=new Label(columnnum,0,ls1.get(i));
					allData[ls.size()+i]=ls1.get(i);
					ws.addCell(c);
				}
			}
			//找到新旧数据的对应属性
			List<Integer> ys=new ArrayList<Integer>();			
			for(int i=0;i<newString.length;i++){
				Integer integer = null;
				for(int j=0;j<allData.length;j++){
					if(newString[i].equalsIgnoreCase(allData[j])){
						integer=j;
						break;
					}
				}
				if(integer!=null){
					ys.add(integer);
				}else{
					System.out.println("新旧属性没找到对应的关系，请确认");
				}
			}
			
			if(ys.size()==newString.length){
				for(int count=startIndex,i=1;count<(al.size()+startIndex-1);count++,i++){
					String  []al11=al.get(i);
					for(int j=0;j<ys.size();j++){
						Integer integer=ys.get(j);
						Label c=new Label(integer,count,al11[j]);
						ws.addCell(c);
					}
				}
			}else{
				System.out.println("有些数据没有找到映射，不能进行更新数据");
			}
			
/*			for(int count=startIndex,i=1;count<(al.size()+startIndex-1);count++,i++)
			{
				String  []al1=al.get(i);
				for(int stringNum=0;stringNum<al1.length;stringNum++)
				{
					Label c=new Label(stringNum,count,al1[stringNum]);
					ws.addCell(c);
				}
				
			}*/
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 在原来的excel上接着写入数据
	 * @param k
	 * @param path
	 * @param al
	 */
	public void UpdateXls_ArrayList_List_WriteNew1(int k,String path,List<List<String>> al)
	{
		
			File file=new  File(path);
			try {
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			int startIndex=ws.getRows();
			for(int count=startIndex,i=0;count<(al.size()+startIndex);count++,i++)
			{
				List<String> al1=al.get(i);
				for(int stringNum=0;stringNum<al1.size();stringNum++)
				{
					Label c=new Label(stringNum,count,al1.get(stringNum));
					ws.addCell(c);
				}
				al1.clear();
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 在原来的excel上更新数据
	 * @param k
	 * @param path
	 * @param al
	 */
	public void UpdateXls_ArrayList_List_new(int k,String filePath,List<List<String>> al)
	{
		try {
			File file=new  File(filePath);
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			int startRow=ws.getRows();
			for(int count=startRow,i=0;count<(al.size()+startRow);count++,i++)
			{
				List<String> list=al.get(i);
				for(int stringNum=0;stringNum<list.size();stringNum++)
				{
					Label c=new Label(stringNum,count,list.get(stringNum));
					ws.addCell(c);
				}
				list.clear();
			}
	//		Label c=new Label(j,i,result);
	//		ws.addCell(c);
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	* 写入指定的列(从第0行开始)
	* @param cloumnNum
	* @param k
	* @param path
	* @param al
	*/
	public void UpdateXls_Cloumn(int cloumnNum,int k,String path,List<String> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()) file.createNewFile();
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int count=0;count<al.size();count++)
			{
				String string=al.get(count);
				Label c=new Label(cloumnNum,count,string);
				ws.addCell(c);
			}
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	* 向指定的列写入测试结果,只能操作List
	*  @param k 要操作的sheet
	* @param path 要操作的文件路径
	* @param al 要写入的数据
	*/
	public void UpdateXls_Cloumn_smoke(int k,String path,List<String> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()) {
				newXLS(path);
			}
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			int count=Constants.Case_Start_Row;
			for(int i=Constants.Case_Start_Row;i<ws.getRows();i++){
				if(ws.getCell(Constants.Case_Result_Column_IsRun, i).getContents().equals(Constants.Exe_Case)){
					Label c=new Label(Constants.Case_Resutl_Column_Num, i, al.get(i-count));
					ws.addCell(c);
				}else {
					count++;
				}
			}
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	* 向指定的列写入测试结果,只能操作map
	*  @param k 要操作的sheet
	* @param path 要操作的文件路径
	* @param al 要写入的数据
	*/
	public void UpdateXls_Cloumn_smoke(int k,String path,Map<String, String> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()) {
				newXLS(path);
			}
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int i=0/*Constants.Case_Start_Row*/;i<ws.getRows();i++){
				String caseNo=ws.getCell(Constants.Case_CaseNO, i).getContents();
				if(ws.getCell(Constants.Case_Result_Column_IsRun, i).getContents().equals(Constants.Exe_Case)){
					Label c=new Label(Constants.Case_Resutl_Column_Num, i, al.get(caseNo));
					ws.addCell(c);
				}else if (ws.getCell(Constants.Case_Result_Column_IsRun, i).getContents().equals(Constants.No_Exe_Case)) {
					Label c=new Label(Constants.Case_Resutl_Column_Num, i, Constants.TestResult_NA);
					ws.addCell(c);
				}
			}
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	/**
	* 写入指定的列(从第row行开始)
	* @param cloumnNum
	* @param k
	* @param path
	* @param al
	*/
	
	public void UpdateXls_Cloumn(int row,int cloumnNum,int k,String path,List<String> al)
	{
		try {
			File file=new  File(path);
			if(!file.exists()) file.createNewFile();
			wb=Workbook.getWorkbook(file);
			wwk=Workbook.createWorkbook(file,wb);
			WritableSheet ws=wwk.getSheet(k);
			for(int count=0;count<al.size();count++)
			{
				String string=al.get(count);
				Label c=new Label(cloumnNum,count+row-1,string);
				ws.addCell(c);
			}
			wwk.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(wwk!=null) wwk.close();
				if(wb!=null) wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取某个值在哪个sheet里面
	 * @param path
	 * @param reg
	 * @param row 行坐标
	 * @param cloumn 列坐标
	 * @return
	 */
	public int getSheetNum(String path,String reg,int cloumn,int row)
	{
		int sheetNum=0;
		try {
			wb=Workbook.getWorkbook(new File(path));
			Sheet[] sheets = wb.getSheets();
			for(int i = 0 ; i < sheets.length; i++) {
				Sheet sheet = sheets[i];
				String tmp = "";
				try {
					tmp = sheet.getCell(cloumn,row).getContents();
				} catch (Exception e) {
					continue;
				}
				if (tmp.equals(reg)) {
					sheetNum = i;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
		return sheetNum;
	}
	
	
	/**
	 * 
	 * @param i 横坐标，从0开始
	 * @param j 纵坐标，从0开始
	 * @param k 操作的sheet，从0开始
	 * @param path 操作的excel文件的路径（包括文件名）
	 * @return
	 */
	public String getXls(int i,int j,int k,String path)
	{
		String xlsValues="";
		try {
			wb=Workbook.getWorkbook(new File(path));
			Sheet sheet=wb.getSheet(k);
			xlsValues=sheet.getCell(j, i).getContents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
		return xlsValues;
	}
	
	public List<String[]> getXls_ArrayList(String path,int k)
	{
		List<String[]> al=new  ArrayList<String[]>();
		try {
			wb=Workbook.getWorkbook(new File(path));
			Sheet sheet=wb.getSheet(k);
			for(int i=0;i<sheet.getRows();i++)
			{
				String hang[]=new  String[sheet.getColumns()];
				for(int j=0;j<sheet.getColumns();j++)
				{
					hang[j]=sheet.getCell(j, i).getContents();
				}
				al.add(hang);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
		return al;
	}
	
	public void getXls_ArrayList(String path,int k,List<String[]> al)
	{
		try {
			wb=Workbook.getWorkbook(new File(path));
			Sheet sheet=wb.getSheet(k);
			for(int i=0;i<sheet.getRows();i++)
			{
				String hang[]=new  String[sheet.getColumns()];
				for(int j=0;j<sheet.getColumns();j++)
				{
					hang[j]=sheet.getCell(j, i).getContents();
				}
				al.add(hang);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
	}
	
	public void getXls_ArrayList(String path,int k,int row,List<String[]> al)
	{
		try {
			wb=Workbook.getWorkbook(new File(path));
			Sheet sheet=wb.getSheet(k);
			for(int i=row;i<sheet.getRows();i++)
			{
				String hang[]=new  String[sheet.getColumns()];
				for(int j=0;j<sheet.getColumns();j++)
				{
					hang[j]=sheet.getCell(j, i).getContents();
				}
				al.add(hang);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(wb!=null) wb.close();
		}
	}
	
	/**
	 * 得到绝对路径下的文件名
	 * @param path 文件的全路径，例如："e:/wk/wl/ww.xls"
	 * @return
	 */
	public String getFileName(String path){
		String string[]=path.split("/");
		String temString=string[string.length-1];
		int fileLength=temString.length();
		String fileName=temString.substring(0, fileLength-4);
		return fileName;
	}
}