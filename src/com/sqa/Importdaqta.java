package com.sqa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.tools.Writestring;
import com.tools.XlsOpreation;

public class Importdaqta {
	
	public String  line_break = "\r\n";
	public String string_split = ",";
	public String string_space ="";
	String separator = "/|\\\\";
	//设置一行来存放对应的数据
	public String line[] = new String[19];
	SimpleDateFormat sdf=null;
	XlsOpreation xo = null;
	List<String> allfile = null;

	public static void main(String[] args) {
		Importdaqta id = new Importdaqta();
		id.done("D:\\Test\\SVN\\R5X - 测试用例\\Upgrade");
//		id.complete_xls_data("D:\\Test\\SVN\\R5X - 测试用例\\web\\network\\advance\\VLAN\\VLAN-PC测试.xls",0);
//		String tmp = id.xo.getXls(8, 6, 1, "D:\\Test\\SVN\\R5X - 测试用例\\Upgrade\\advanced\\URL优先级.xls");
//		System.out.println(tmp);
//		System.out.println(tmp.replaceAll("^\\d\\.|((\r|\n)\\d\\.)", "\r\n"));
//		System.out.println(tmp.replaceAll("((^\\d)|\r|\n)\\.", "\r\n"));
	}
	
	public Importdaqta() {
		this.sdf=new SimpleDateFormat("yyyy-MM-dd");
		xo = new XlsOpreation();
		allfile = new ArrayList<String>();
	}
	
	public void complete_xls_data(String path,int sheetnum) {
		List<String[]> excelDatas = xo.getXls_ArrayList(path, sheetnum);
		List<String[]> newDatas = new ArrayList<String[]>(); 
		for(int k =8 ;k < excelDatas.size(); k++) {
			String array[] = new String[3];
			String linedata[] = excelDatas.get(k);
//			String tmp1 = linedata[2];			
//			if (!tmp1.equals(string_space)) {
//				tmp1 = "A话机(PC口接B话机) :"+line_break+tmp1;
//			}
//			array[0] = tmp1;
//			
//			String tmp2 = linedata[2];
//			if (!tmp2.equals(string_space)) {
//			tmp2 = "R59 Tiptel:"+line_break+tmp2;
//		}
//			array[1] = tmp2;
			
			String tmp = "";
			for (int i = 1; i < 4; i++) {
				tmp += linedata[i]+line_break;
			}
			array[0]=tmp;
			
			newDatas.add(array);
		}
		xo.UpdateXls_ArrayList(0, "D:\\Test\\SVN\\R5X - 测试用例\\web\\network\\advance\\VLAN\\tmp.xls", newDatas);
	}
	
	public void done(String path) {
		get_all_file_name(path, allfile);
		for(String filename:allfile) {
			int sheetnum = xo.getSheetNum(filename, "用例编号", 0, 1);
			System.out.println(filename);
			File testfile = new File(filename);
			String  name = testfile.getAbsolutePath();
			List<String> list_string = Arrays.asList(name.split(separator));
			int names_length  = list_string.size();
			name = list_string.get(names_length-2)+"-"+list_string.get(names_length-1);
			name = name.substring(0, name.lastIndexOf("."));
			
			Writestring test = new Writestring("C:\\Users\\u\\Desktop\\importdata\\"+name+".csv");
			test.write(first_line_data());
			add_line();		
			operate_data(filename,sheetnum);
			test.write(covert_data(line));
			test.closeFile();
		}
	}
	
	public void  get_all_file_name(String path,List<String> ls) {
		File f = new File(path);
		File[] filenames = f.listFiles();
		for(File ff:filenames) {
			if (ff.isDirectory()) {
				get_all_file_name(ff.getPath(),ls);
			}else {
				if (!ff.getName().endsWith(".xls")) {
					continue;
				}
				if (ff.getName().endsWith(".xls")) {
					ls.add(ff.getAbsolutePath());
				}
			}
		}
	}
	
	public void operate_data(String path,int index) {
		
		List<String[]> ll = xo.getXls_ArrayList(path, index);
		File f = new File(path);
		String name =f.getName();
		name = name.substring(0,name.indexOf("."));
		line[3] = name;
		//特殊处理前置条件
//		line[4] = "";
		line[4] = ll.get(7)[0]+line_break;
		int start = 8;
		int end = ll.size();
//		int end = 41;
		//每个大步骤的条件
		int []p1 = get_coordinate(ll, "模块/条件");
		//每个小步骤的条件
		int []p2 = get_coordinate(ll, "测试步骤");
		//每个结果的步骤
		int []p3 = get_coordinate(ll, "预期结果");
		//每个结果的步骤
//		int []p4 = get_coordinate(ll, "等级");
		System.out.println(p1[0]+"\t"+p1[1]);
		System.out.println(p2[0]+"\t"+p2[1]);
		System.out.println(p3[0]+"\t"+p3[1]);
		int first_index = 0;
		int second_index = 0;
//		String prestr = "start_index";
		//步骤的值
		String vv1 = "";
		//结果的值
		String vv2 = "";
		String pre_str = "";
		for(int i=start; i< end; i++) {
			String ll_line[] = ll.get(i);
			String value1 = ll_line[p1[1]];
			String value2 = ll_line[p2[1]];
			value2 = value2.replaceAll("^\\d\\.|((\r|\n)\\d\\.)", "\r\n");
			String value3 = ll_line[p3[1]];
			value3 = value3.replaceAll("^\\d\\.|((\r|\n)\\d\\.)", "\r\n");
			String tmp = value1+value2+value3;
			if (tmp.equals("")) {
				String tmp_vvv = ll_line[0];
				if (!tmp_vvv.equals("")) {
					line[4] = line[4] +line_break+tmp_vvv;
				}
				continue;
			}
			if (value1.equals(string_space)/*&&!value2.equals(string_space)&&!value3.equals(string_space)*/) {
				//这种情况是有步骤，有结果,没有前置条件
				if (i==start) {
					first_index++;
				}
				second_index++;
				if (!value2.equals("")) {
					pre_str = value2;
				}else {
					value2 = pre_str;
				}
				vv1 += first_index+"."+second_index+". "+value2+line_break;
				vv2 += first_index+"."+second_index+". "+value3+line_break;
			}else {
				//这种情况是有步骤，有结果，有前置条件
				second_index = 0;
				first_index++;
				second_index++;
				if (!value2.equals("")) {
					pre_str = value2;
				}else {
					value2 = pre_str;
				}
				vv1 += first_index+". "+value1+line_break;
				//假如操作步骤为空的话，会被忽略掉，假如操作步骤的值为空，取上一个的值
				vv1 += first_index+"."+second_index+". "+value2+line_break;
				vv2 += first_index+"."+second_index+". "+value3+line_break;
			}
		}
		vv1=vv1.substring(0,vv1.length()-2);
		vv2=vv2.substring(0,vv2.length()-2);
		vv1 = vv1.replace(",", "，");
		vv2 = vv2.replace(",", "，");
		line[5] = vv1;
		line[6] = vv2;
//		System.out.println(name);
	}
	
	/**
	 * 把对应的数据不全
	 * @param src
	 * @return
	 */
	public String complete_data(String src) {
		String dst = "";
		dst = "\""+src+"\"";
		return dst;
	}
	
	/**
	 * 数据结束后添加连接符
	 * @param value
	 * @param type
	 * @return
	 */
	public String add_cloumn_string(String value,String type) {
		return complete_data(value)+type;
	}
	
	public String first_line_data() {
		String dst = "";
		dst = add_cloumn_string("所属产品", string_split)+add_cloumn_string("平台", string_split)+add_cloumn_string("所属模块", string_split)+add_cloumn_string("用例标题", string_split)+add_cloumn_string("前置条件", string_split)+
				add_cloumn_string("步骤", string_split)+add_cloumn_string("预期", string_split)+add_cloumn_string("关键词", string_split)+add_cloumn_string("优先级", string_split)+add_cloumn_string("用例类型", string_split)+
				add_cloumn_string("适用阶段", string_split)+add_cloumn_string("用例状态", string_split)+add_cloumn_string("结果", string_split)+add_cloumn_string("由谁创建", string_split)+add_cloumn_string("创建日期", string_split)
				+add_cloumn_string("最后修改者", string_split)+add_cloumn_string("修改日期", string_split)+add_cloumn_string("用例版本", string_split)+add_cloumn_string("相关用例", string_space)+line_break;
		return dst;
	}
	
	/**
	 * 把一行数据转换成csv能接受的格式
	 * @param line
	 * @return
	 */
	public String covert_data(String line[]) {
		String retuString = "";
		for (int i = 0; i < line.length; i++) {
			if (i == line.length-1) {
				retuString += add_cloumn_string(line[i],string_space);
			}else {
				retuString += add_cloumn_string(line[i],string_split);
			}
		}
		return retuString;
	}
	
	/**
	 * 完善一行数据
	 */
	public void add_line() {
		line[0]="嵌入式 - 话机(#2)";
		line[1]="所有平台(#0)";
		line[2]="Upgrade模块(#622)";
//		line[2]="Advanced(#1791)";
//		line[2]="Basic(#1790)";
//		line[3]="嵌入式 - 话机(#2)";
//		line[4]="嵌入式 - 话机(#2)";
//		line[5]="嵌入式 - 话机(#2)";
//		line[6]="嵌入式 - 话机(#2)";
		line[7]="";
		line[8]="2";
		line[9]="功能测试";
		line[10]="";
		line[11]="正常";
		line[12]="";
		line[13]="吴高峰";
		line[14]=sdf.format(new Date());
		line[15]="";
		line[16]="";
		line[17]="";
		line[18]="";
		
	}
	
	public int[] get_coordinate(List<String[]> ll, String name) {
		int [] position = new int[2];
		
		for (int i = 0; i < ll.size(); i++) {
			String line[] = ll.get(i);
			boolean flag =false;
			for(int j = 0; j< line.length; j++) {
				if (name.equals(line[j])) {
					position[0] = i;
					position[1] = j;
					flag = true;
					break;
				}
			}
			if (flag) {
				return position;
			}
		}
		return null;
	}

}
