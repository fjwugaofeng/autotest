package com.sqa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tools.*;

public class Main {

//	public static String url = "jdbc:mysql:192.168.88.99:3306/zantao";
	public static String url = "jdbc:mysql://192.168.10.27:89/zentao?useUnicode=true&characterEncoding=UTF-8";
	public static String account = "root";
	public static String password = "";
	public static String file1 = "qa/qa_sql.xls";
	public static String file2 = "qa/qa_type.xls";
	public static int NUMBER_END = 0; //统计时间段距离目前结束多少个月
	public static int NUMBER_START =3;//统计时间段距离目前开始多少个月
	public static int OFFSET = 10;
	public static String qa = "质量概述";
	public static String appType = "'App - VBell','App - SmartPlus','App - VFone'";
	public static String zhuanyi = "&amp;";
	public static String FILTER = "当月预期交付需求已交付数";
	public static String Remove_Branch="'A05','R50V3','X915','X916','XL10','R29C-B','R20B-T30','X933','R49'";
	public static boolean WRITE_TO_EXCEL = true;
	public String src_file_name="";
	public String dst_file_name="";
	public static int []starts = {1,3,4};
	public static int []offsets = {1,2,3};
	public static int [] start_month = {6,7,7,6};//表示开始统计的月份，第一个表示福州软件部，第二个表示嵌入式软件部，第三个表示系统软件部，第四个表示只能软件部
	public static int branch_1 = 3;
	public static int branch_2= 2;
	public  static String First_Type_Name = "产品问题数";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		set_num_end();
		starts = set_number();
		List<Integer> mm = get_current_quarter();
		Map<String, List<Integer>> am = get_all_month();
		Main m= new Main();
		for (int i = 0; i < starts.length; i++) {
			NUMBER_START = starts[i];
			OFFSET = offsets[i];
			//写入季度
			m.create_file(i);
		}
		m.improving_data(mm, am);
		NUMBER_START = starts[0];
		m.write_detail_data_to_old();

		//调试信息
//		NUMBER_END = 3;
//		NUMBER_START= 4;
//		OFFSET =1;
//		m.create_file(0);
//		m.write_detail_data_to_old();
		
//		m.new_to_old();
	}
	
	public void  new_to_old() {
		int currmonthday = Integer.parseInt(get_tongjidate());
		int lastmonthday = currmonthday -1;
		int month = currmonthday % 100;
		int lastmonth = month -1;
		File files = new File("qa");
		for (File f : files.listFiles()) {
			if (f.getName().endsWith(".xlsx")/*&&!f.getName().contains("20")*/) {
				String fileName = f.getName();
				if (fileName.contains(currmonthday+"")) {
					fileName=fileName.replace(currmonthday+"", "");
				}else {
					continue;
				}
				String project = fileName.substring(0,fileName.indexOf("."));
				//上个月文件的文件名
				String lastFileName = "qa/"+project + lastmonthday + ".xlsx";
				//读取数据
				List<String[]> this_month_data = ExcelHelper.readExcel(f.getAbsolutePath(), qa, 1);	
				List<String[]> last_month_data = ExcelHelper.readExcel(lastFileName, qa, 1);	
				
				//更新月数据
				int[] strat_p1 =get_coordinate(this_month_data, First_Type_Name);
				int[] strat_p2 =get_coordinate(last_month_data, lastmonth+"月");
				int[] strat_p3 =get_coordinate(last_month_data, First_Type_Name);
				List<String[]> monthData = get_line_data(this_month_data, strat_p1[0], strat_p1[1]+offsets[0]);
				ExcelHelper.updatacell(lastFileName, qa, monthData, strat_p3[0], strat_p2[1]+1);
				
				//更新季度数据
				List<String[]> jiduData = get_line_data(this_month_data, strat_p1[0], strat_p1[1]+offsets[1]);
				int q = Math.floorDiv(month, 3);
				String jidu = "";
				if (q==0) {
					jidu = "Q1";
				}else if (q==1) {
					jidu = "Q2";
				}else if (q==2) {
					jidu = "Q3";
				}else if (q==3) {
					jidu = "Q4";
				}
				int[] strat_p4 =get_coordinate(last_month_data, jidu);
				ExcelHelper.updatacell(lastFileName, qa, jiduData, strat_p3[0], strat_p4[1]);
				
				//更新总数据
				List<String[]> allData = get_line_data(this_month_data, strat_p1[0], strat_p1[1]+offsets[2]);
				int[] strat_p5 =get_coordinate(last_month_data, "合计");
				ExcelHelper.updatacell(lastFileName, qa, allData, strat_p3[0], strat_p5[1]);
			}
		}
		
		
		
	}
	
	public List<String[]> get_line_data(List<String[]> datas, int rowNum,int cloumn) {
		List<String[]> nnnn = new ArrayList<String[]>();
		for (int i = rowNum; i < datas.size(); i++) {
			String line[] = new String[1];
			line[0] = datas.get(i)[cloumn];
			nnnn.add(line);
		}
		return nnnn;
	}
	
	public void write_detail_data_to_old() {

		File files = new File("qa/lastmonth");
		for (File f : files.listFiles()) {
			if (f.getName().endsWith(".xlsx")/*&&!f.getName().contains("20")*/) {
				String fileName = f.getName();
				String new_file_name = "qa/"+fileName;
				String project = fileName.substring(fileName.indexOf("】")+1,fileName.indexOf("."));
				project = project.substring(0,project.length()-6);
				try {
					copyFile(f.getAbsolutePath(), new_file_name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				write_detail_data(project, new_file_name);
			}
		}
				
				
	
	}
	
	public void improving_data(List<Integer> mm,Map<String, List<Integer>> am) {
		String time = get_tongjidate();
		File files = new File("qa");
		for (File f : files.listFiles()) {
			if (f.getName().endsWith(".xlsx")/*&&!f.getName().contains("20")*/) {
				String fileName = f.getName();
				if (fileName.contains(time)) {
					fileName=fileName.replace(time, "");
				}
				List<String[]> this_month_data = ExcelHelper.readExcel(f.getAbsolutePath(), qa, 1);		
				String project = fileName.substring(fileName.indexOf("】")+1,fileName.indexOf("."));
				File tmp_files = new File("qa/lastmonth");
				String file_ab = "";
				for (File f1 : tmp_files.listFiles()) {
					if (f1.getName().contains(project)) {
						file_ab = f1.getAbsolutePath();
						break;
					}
				}
				XlsOpreation xo = new XlsOpreation();
				List<String[]> ls = xo.getXls_ArrayList(file1, 0);
				List<String[]> last_month_data = ExcelHelper.readExcel(file_ab, qa, 1);		
				int allsize = 0;
				if (project.equals("智能软件部")||project.equals("系统软件部")||project.equals("嵌入式软件部")) {
					allsize=branch_1;
				}else if (project.equals("福州软件部")) {
					allsize=branch_2;
				}
				int zuobiao1[] = new int[2];
				int zuobiao2[] = new int[2];
				
				for(int i = 0; i < ls.size(); i++) {//遍历所有的统计类别-
					String sheetName = ls.get(i)[0];
					if (mm.size() == 0||sheetName.contains("delete")||!sheetName.contains("-统计")||sheetName.contains("版本通过率")||sheetName.contains("交付版本质量分")||sheetName.contains("问题需求比")||sheetName.contains("总遗留bug数")||sheetName.contains("总遗留需求数")||sheetName.contains("每月遗留需求")) {
						continue;
					}
					sheetName = sheetName.replace("-统计", "");
					System.out.println(sheetName);
					// 获取当前统计信息的开始位置
					int [] p1 = get_coordinate(last_month_data, sheetName);
					int [] p2 = get_coordinate(last_month_data, mm.get(0)+"月");
					zuobiao1[0] = p1[0];
					zuobiao1[1] = p2[1];
					int [] p3 = get_coordinate(this_month_data, sheetName);
					zuobiao2[0] = p3[0];
					zuobiao2[1] = 2;
					List<String[]> newdata = new ArrayList<String[]>(); 
					if (mm.size() == 0) {
						for (int j = 0; j < allsize+1; j++) {
							int sum = sum_single(this_month_data.get(zuobiao2[0]+j), zuobiao2[1]);
							String tmp_array[] = {sum+""};
							newdata.add(tmp_array);
						}
					}else {
						for (int j = 0; j < allsize+1; j++) {
							int sum = 0;
							for (int k = 0; k < mm.size(); k++) {
								String tttt[] = last_month_data.get(zuobiao1[0]+j);
								sum+=sum_count(tttt, zuobiao1[1]+k);
							}
							sum += sum_single(this_month_data.get(zuobiao2[0]+j), zuobiao2[1]);
							String tmp_array[] = {sum+""};
							newdata.add(tmp_array);
						}
					}
					ExcelHelper.updatacell(f.getAbsolutePath(), qa, newdata, zuobiao2[0], zuobiao2[1]+1);
				}
				
				for(int i = 0; i < ls.size(); i++) {//遍历所有的统计类别
					String sheetName = ls.get(i)[0];
					if (am.get(project).size()==0||sheetName.contains("delete")||!sheetName.contains("-统计")||sheetName.contains("版本通过率")||sheetName.contains("交付版本质量分")||sheetName.contains("问题需求比")||sheetName.contains("总遗留bug数")||sheetName.contains("总遗留需求数")||sheetName.contains("每月遗留需求")) {
						continue;
					}
					sheetName = sheetName.replace("-统计", "");
					System.out.println(sheetName);
					// 获取当前统计信息的开始位置
					int [] p1 = get_coordinate(last_month_data, sheetName);
					int [] p2 = get_coordinate(last_month_data, am.get(project).get(0)+"月");
					zuobiao1[0] = p1[0];
					zuobiao1[1] = p2[1];
					int [] p3 = get_coordinate(this_month_data, sheetName);
					zuobiao2[0] = p3[0];
					zuobiao2[1] = 2;
					List<String[]> newdata = new ArrayList<String[]>(); 
					if (am.get(project).size() == 0) {
						for (int j = 0; j < allsize+1; j++) {
							int sum = sum_single(this_month_data.get(zuobiao2[0]+j), zuobiao2[1]);
							String tmp_array[] = {sum+""};
							newdata.add(tmp_array);
						}
					}else {
						for (int j = 0; j < allsize+1; j++) {
							int sum = 0;
							for (int k = 0; k < am.get(project).size(); k++) {
								String tttt[] = last_month_data.get(zuobiao1[0]+j);
								sum+=sum_count(tttt, zuobiao1[1]+k);
							}
							sum += sum_single(this_month_data.get(zuobiao2[0]+j), zuobiao2[1]);
							String tmp_array[] = {sum+""};
							newdata.add(tmp_array);
						}
					}
					ExcelHelper.updatacell(f.getAbsolutePath(), qa, newdata, zuobiao2[0], zuobiao2[1]+2);
				}
				
			}
		}
	}
	
	public int  sum_single(String tmp[], int n) {
		int sum = 0;
		String tmpv = tmp[n];
		try {
			sum = Integer.parseInt(tmpv);
		} catch (NumberFormatException e) {
			sum = 0;
		}
		return sum;
	}
	
	public int sum_count(String tmp[], int n) {
		int sum = 0;
		String ttt = tmp[n];
		if (ttt == null || ttt.equals("")) {
			sum = 0;
		}else {
			try {
				sum = Integer.parseInt(ttt);
			} catch (NumberFormatException e) {
				sum = 0;
			}
		}
		return sum;
	}
	
	public void create_file(int count) {
		String time = get_tongjidate();
		if (count == 0) {
			//先删除qa下原来穿在的文件
			File  need_delte_file = new File("qa");
			for(File f : need_delte_file.listFiles()) {
				if (f.getName().endsWith(".xlsx")&&f.getName().contains("SQA质量月报")){
					f.delete();
				}
			}
			//复制文件到qa下
			File  srcfile = new File("qa/model");
			for(File f : srcfile.listFiles()) {
				String fn = f.getName();
				if (f.getName().endsWith(".xlsx")) {
					try {
						copyFile(f.getAbsolutePath(), "qa/"+fn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		File files = new File("qa");
		for (File f : files.listFiles()) {
			if (f.getName().endsWith(".xlsx")/*&&!f.getName().contains("20")*/) {
				//只处理xlsx的文件
				String fileName = f.getName();
				src_file_name= f.getAbsolutePath();
				if (src_file_name.contains(time)) {
					fileName=src_file_name.replace(time, "");
					f.renameTo(new File(fileName));
					f=new File(fileName);
					src_file_name= f.getAbsolutePath();
				}
				
				String prefix = fileName.substring(0, fileName.indexOf("】")+1);
				String project = fileName.substring(fileName.indexOf("】")+1,fileName.indexOf("."));
				String suffix = fileName.substring(fileName.indexOf(".")+1);
				System.out.println(prefix + "\t"+project+"\t"+suffix);
				
				//设置全部开始统计的数据
				int m = Integer.parseInt(time.substring(4));
				if (count == 2) {
					if (project.equals("福州软件部")) {
						NUMBER_START = m - start_month[0] + 1;
					}else if (project.equals("嵌入式软件部")) {
						NUMBER_START = m - start_month[1] + 1;
					}else if (project.equals("系统软件部")) {
						NUMBER_START = m - start_month[2] + 1;
					}else if (project.equals("智能软件部")) {
						NUMBER_START = m - start_month[3] + 1;
					}
				}
				
				//拼接新的文件名
				String newFileName = prefix+project+time+"."+suffix;
				if (!newFileName.contains("qa\\")) {
					newFileName = "qa\\"+newFileName;
				}
				
				dst_file_name = newFileName;
				
				System.out.println(newFileName);
				System.out.println(f.getAbsolutePath());
				try {
//					if (WRITE_TO_EXCEL) {
						copyFile(src_file_name, newFileName);
//					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				写入数据
//				done(project, newFileName);
				write_statistical_data(project, newFileName);
				
				//删除旧的文件
				f.delete();
			}else {
				continue;
			}
		}
	}
	
	public static void done(String xiangmu,String file){
		List<String[]> jichudata = ExcelHelper.readExcel(file, qa, 1);
		XlsOpreation xo = new XlsOpreation();
		SqlHelper sq = new SqlHelper(url, account, password);
		List<String[]> ls = xo.getXls_ArrayList(file1, 0);
//		List<String[]> ls = ExcelHelper.readExcel(file1, "qa_type", cloumnnum)
		ResultSet realdata = null;
		
		
		//获取各个项目组的项目配置
		List<String[]> app_types = xo.getXls_ArrayList(file2, 0);
		Map<String, String> apptypes = new HashMap<String, String>();
		//组装机型类型
		for (String [] app:app_types) {
			String type=app[0];
			String ppp = "";
			for (int i = 1; i < app.length; i++) {
				if (app[i].equals("")) {
					continue;
				}
				if(i==1) {
					ppp = "'" + app[i] + "',";
				}else if (i == app.length-1) {
					ppp = ppp + "'" +  app[i] +"'";
				}else {
					ppp = ppp + "'" +  app[i] +"',";
				}
			}
//			System.out.println(type + "\t" + ppp);
			if (ppp.contains("&")) {
				ppp = ppp.replace("&", "&amp;");
			}
			
			if (ppp.endsWith(",")) {
				ppp = ppp.substring(0,ppp.length()-1);
			}
			
			System.out.println(type + "\t" + ppp);
			apptypes.put(type, ppp);
		}
		
		//这边做一个很简单的影视关系
		Map<String, String> old_new = new HashMap<String, String>();
		int jiange=5;
		for (int i = 0; i < 4; i++) {
			String ttt[] = app_types.get(i);
			for (int j = 1; j < ttt.length; j++) {
				//取当前行的下面5行对应单元格的内容，此内容为映射的内容
				old_new.put(ttt[j], app_types.get(i+jiange)[j]);
			}
		}
				
		
//		String tmpsql = "SELECT zb.`name` FROM zt_product zp LEFT JOIN zt_branch zb ON zp.id = zb.product WHERE zb.`name` IS NOT NULL AND zp.`name` IN (?) AND zb.deleted = '0' ORDER BY zp.id,zb.id";
//		tmpsql = tmpsql.replace("?", apptypes.get(xiangmu));
//		String tmpparas[] = {apptypes.get(xiangmu)};
//		ResultSet reset = sq.sql_Query(tmpsql);
//		List<String> list = new ArrayList<String>();
//		list.add("所有平台");
	/*	try {
			while(reset.next()) {
				list.add(reset.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				reset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
//		for(String ttttt:list) {
//			System.out.println(ttttt);
//		}
		
//		System.out.println(ls.size());
//		System.out.println(list.size());
		
		for(String [] lll:ls) {
			
			//这边定义一些还要二次处理的数据
			List<String> second_opreate = new ArrayList<String>();
			
			String detail_sql = lll[3];
			
			String sql= lll[1];
			//替换掉sql中的？
			sql = sql.replace("?", apptypes.get(xiangmu));
			
			//替换sql中的(xx)，原因是要剔除新品
			sql = sql.replace("(xx)", "("+Remove_Branch+")");
			
			String sheetName = lll[0];
			
			//这边判断是否执行此次操作
//			if (!(FILTER.contains(sheetName.replace("-统计", "").trim())||FILTER.contains(sheetName.replace("Y", "").trim()))||(sheetName.equals("Y当月预期交付需求已交付需求")&&!FILTER.contains("当月预期交付需求已交付数"))) {
//				continue;
//			}
			
			//这边特殊处理下一些sql语句已经不查询了
			if (sheetName.contains("delete")) {
				continue;
			}
			
//			if (!sheetName.contains("总遗留需求数")) {
//				continue;
//			}
			
			
			System.out.println(sheetName);
			//需求对应时间段的日期
			/*if ((NUMBER_END!=0||NUMBER_START!=1)&&NUMBER_START>NUMBER_END&&!sheetName.equals("总遗留需求数-统计")) {
				if (NUMBER_START==0&&NUMBER_END==-1) {
					String tmp="tmpt";
					sql=sql.replace("0 MONTH", tmp+" MONTH");
					sql=sql.replace("1 MONTH", NUMBER_START+" MONTH");
					sql=sql.replace(tmp+" MONTH", NUMBER_END+" MONTH");
				}else {
					sql=sql.replace("1 MONTH", NUMBER_START+" MONTH");
					sql=sql.replace("0 MONTH", NUMBER_END+" MONTH");
				}
			}else if (NUMBER_END!=0&&NUMBER_START>NUMBER_END&&(sheetName.equals("总遗留需求数-统计")||sheetName.equals("总遗留bug数-统计")||sheetName.equals("Y总遗留BUG数"))) {
				sql=sql.replace("0 MONTH", NUMBER_END+" MONTH");
			}*/
			
			//这个证明要修改统计时间
			if (NUMBER_END!=0||NUMBER_START!=1) {
				String tmp1 = "sssss";
				String tmp2 = "ttttt";
				sql = sql.replace("0 MONTH", tmp1+" MONTH");
				sql = sql.replace("1 MONTH", tmp2+" MONTH");
				sql = sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				sql = sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
				
				detail_sql = detail_sql.replace("0 MONTH", tmp1+" MONTH");
				detail_sql = detail_sql.replace("1 MONTH", tmp2+" MONTH");
				detail_sql = detail_sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				detail_sql = detail_sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
			}
			
			if (sheetName.endsWith("-统计")) {
				
//				//特定条件下写入数据
//				if (!sheetName.contains(FILTER)||FILTER.equals("")) {
//					continue;
//				}
				
				//这边判断再统计季度数据与总数据的时候只需要统计个别数据
				if (NUMBER_START-NUMBER_END!=1 && !(sheetName.contains("版本通过率")||sheetName.contains("交付版本质量分")||sheetName.contains("问题需求比")||sheetName.contains("总遗留bug数")||sheetName.contains("总遗留需求数"))) {
					continue;
				}
				
				String total = "";
				//从数据库获取对应的app的数据
				realdata = sq.sql_Query(sql);
				
				//用一个map来存放对应的数据
				Map<String, String>  map = new HashMap<String, String>();
				try {
//					realdata.next();
					while(realdata.next()) {
//						System.out.println(realdata.getString(1)+"\t"+realdata.getString(2));
						String temp1 = realdata.getString(1);
						String temp2 = realdata.getString(2);
						if (temp1.contains(zhuanyi)) {
							temp1=temp1.replace(zhuanyi, "&");
						}
//						System.out.println(old_new.get(temp1)+"\t"+temp2+"\t");
						//产品质量分药算平均值
						if (sheetName.equals("交付版本质量分-统计")) {
							int countnum=realdata.getInt(3);
							if (temp2.contains(",")) {
								temp2=temp2.replace(",", "");
							}
							float fff=Float.parseFloat(temp2);
							String floatformat = String.format("%.2f", fff/countnum);
							map.put(old_new.get(temp1), floatformat);
						}else {
							map.put(old_new.get(temp1), temp2);
						}
						if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
							second_opreate.add(realdata.getString(3));
							second_opreate.add(realdata.getString(4));
						}else if (sheetName.equals("交付版本质量分-统计")) {
							second_opreate.add(realdata.getString(3));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					if (realdata!=null) {
						try {
							realdata.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//这边假如是只能软件部或者系统软件部还需要再查一次数据
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
					
					String sql1 = lll[3];
					sql1 = sql1.replace("?", appType);
					
					if (NUMBER_END!=0||NUMBER_START!=1) {
						String tmp1 = "sssss";
						String tmp2 = "ttttt";
						sql1 = sql1.replace("0 MONTH", tmp1+" MONTH");
						sql1 = sql1.replace("1 MONTH", tmp2+" MONTH");
						sql1 = sql1.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
						sql1 = sql1.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
					}
					
					realdata = sq.sql_Query(sql1);
					try {
						while(realdata.next()) {
//							System.out.println(realdata.getString(1)+"\t"+realdata.getString(2));
							String tempstr = realdata.getString(2);
							if (sheetName.equals("交付版本质量分-统计")) {
								int countnum=realdata.getInt(3);
								if (tempstr.contains(",")) {
									tempstr=tempstr.replace(",", "");
								}
								float fff=Float.parseFloat(tempstr);
								tempstr = String.format("%.2f", fff/countnum);
							}
							String ios_android=realdata.getString(1);
							if (ios_android==null) {
								continue;
							}
							if (xiangmu.equals("智能软件部")&&ios_android.equals("Android")) {								
								map.put("APP", tempstr);
								if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
									second_opreate.add(realdata.getString(3));
									second_opreate.add(realdata.getString(4));
								}else if (sheetName.equals("交付版本质量分-统计")) {
									second_opreate.add(realdata.getString(3));
								}
							}else if (xiangmu.equals("系统软件部")&&ios_android.equals("IOS")) {
								map.put("APP", tempstr);
								if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
									second_opreate.add(realdata.getString(3));
									second_opreate.add(realdata.getString(4));
								}else if (sheetName.equals("交付版本质量分-统计")) {
									second_opreate.add(realdata.getString(3));
								}
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						if (realdata!=null) {
							try {
								realdata.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
				
				//这边开始构建数据,现在数据库查出来的数据是不包括总数，需要自己计算				
				float tmpfloat=0;
				int tmpint=0;
				boolean t=false;
				int q_1=0;
				int q_2=0;
						
				for(String vvv : map.values()) {
					if (sheetName.equals("问题需求比-统计")) {
						String qqq[]=vvv.split("/");
						q_1 += Integer.parseInt(qqq[0]);
						q_2 += Integer.parseInt(qqq[1]);
						continue;
					}else if (sheetName.equals("版本通过率-统计")) {
						continue;
					}else {
						if(vvv.contains(".")) {
							t=true;
							if (vvv.contains(",")) {
								vvv = vvv.replace(",", "");
							}
							tmpfloat += Float.parseFloat(vvv);
						}else {
							tmpint += Integer.parseInt(vvv);
						}
					}
 				}
				
				String bug_fenlei="";
				
				//这边对不能直接相加来算综合的进行处理：例如版本通过率，质量分等
				if (sheetName.equals("版本通过率-统计")) {
					float all =0;
					float pass=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						if (i % 2 ==0) {
							all += Float.parseFloat(second_opreate.get(i));
//							all += Integer.parseInt(second_opreate.get(i));
						}else {
//							pass += Integer.parseInt(second_opreate.get(i));
							pass += Float.parseFloat(second_opreate.get(i));
						}
					}
					if (all == 0) {
						all = 1;
					}
					float rate = pass/all;
					DecimalFormat df = new DecimalFormat("0.00%");
					total = df.format(rate);
				}else if (sheetName.equals("交付版本质量分-统计")) {
					int c=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						c += Integer.parseInt(second_opreate.get(i));
					}
					tmpfloat = tmpfloat/c; 
					String floatformat = String.format("%.2f", tmpfloat);
					tmpfloat = Float.parseFloat(floatformat);
				}else if (sheetName.equals("总遗留bug数-统计")) {
					int resolved =0;
					int unresolved=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						if (i % 2 ==0) {
							resolved += Integer.parseInt(second_opreate.get(i));
						}else {
							unresolved += Integer.parseInt(second_opreate.get(i));
						}
					}
					
					bug_fenlei = "("+resolved+"/"+unresolved+")";
					
				}
				
				//假如是float类型的，转换成string类型
				if (t) {
					total = tmpfloat+"";
				}else {
					if (!sheetName.equals("版本通过率-统计")) {
						total=tmpint+"";
					}
				}
				
				//问题需求比的形式是这样的10/15
				if (sheetName.equals("问题需求比-统计")) {
//					total = q_1+"/"+q_2;
//					total =(float)(Math.round(q_1/q_2*100))/100+"";
					total = String.format("%.2f", (float)q_1/q_2);
				}
				
				//特殊拼接所有遗留bug
				if (sheetName.equals("总遗留bug数-统计")) {
					total +=bug_fenlei;
				}
				
				sheetName = sheetName.replace("-统计", "");
				// 获取当前统计信息的开始位置
				int [] p = get_coordinate(jichudata, sheetName);
				System.out.println(p[0]+"\t"+p[1]);
				
				/*if (sheetName.equals("版本通过率")) {
					
					if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
						//拼接只能软件部和系统软件部所有的branch类别
						String branch_name = "";
						for (int i = 0; i < list.size(); i++) {
							branch_name +="'" + list.get(i) + "',";
						}
						
						if (xiangmu.equals("智能软件部")) {
							branch_name += "'" +"Android" + "'";
						}else {
							branch_name += "'" +"IOS" + "'";
						}
						
						//通过新的branch来查询对应总的版本通过率
						String passratestr=lll[4];
						passratestr = passratestr.replace("?", branch_name);
						ResultSet passrate = sq.sql_Query(passratestr);
						
						try {
							passrate.next();
							map.put(jichudata.get(p[0])[p[1]], passrate.getString(1));
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}finally {
							if (passrate!=null) {
								try {
									passrate.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
				
						
					}else {
						String tmpvalue = lll[2];
						tmpvalue = tmpvalue.replace("?", apptypes.get(xiangmu));
						
						if ((NUMBER_END!=0||NUMBER_START!=1)&&NUMBER_START>NUMBER_END) {
							tmpvalue=tmpvalue.replace("1 MONTH", NUMBER_START+" MONTH");
							tmpvalue=tmpvalue.replace("0 MONTH", NUMBER_END+" MONTH");
						}
						
						ResultSet tmpset =sq.sql_Query(tmpvalue);
						try {
							tmpset.next();
							map.put(jichudata.get(p[0])[p[1]], tmpset.getString(1));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							if (tmpset!=null) {
								try {
									tmpset.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}else {
					map.put(jichudata.get(p[0])[p[1]], total);
				}*/
				
				// 组装数据
				List<String[]> newdata = new ArrayList<String[]>();
				String tmp[] = {total};
				newdata.add(tmp);
				// 这边进行组装数据的长度
				int allsize=0;
				/*for (int i = 0; i < app_types.size(); i++) {
					if (app_types.get(i)[0].equals(xiangmu)) {
						if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
							allsize = app_types.get(i).length;
						}else {
							allsize = app_types.get(i).length-1;
						}
						break;
					}
				}*/
				
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")||xiangmu.equals("嵌入式软件部")) {
					allsize=branch_1;
				}else if (xiangmu.equals("福州软件部")) {
					allsize=branch_2;
				}
//			    System.out.println(p[0]+"\t"+map.keySet().size()+"\t"+(p[0]+map.keySet().size()+1));
				System.out.println(sheetName);
				for(int i = p[0]+1; i<p[0]+allsize+1;i++) {
//					System.out.println();
//					System.out.println(i+"\t"+(i+));
					//这边有个简单的影视
					String curry = jichudata.get(i)[p[1]];
					String sqlvalue="0";
					if (map.get(curry)!=null) {
						sqlvalue = map.get(curry);
					}
					//问题需求比转换成小数显示
					if (sheetName.equals("问题需求比")&&!sqlvalue.equals("0")&&sqlvalue.contains("/")) {
						int indexxxxxx = sqlvalue.indexOf("/");						
						int qian = Integer.parseInt(sqlvalue.substring(0, indexxxxxx));
						int hou = Integer.parseInt(sqlvalue.substring(indexxxxxx+1));	
						if (hou==0) {
							hou=1;
						}
						sqlvalue=String.format("%.2f", (float)qian/hou);
						
					}
					
//					System.out.println(curry+"\t"+sqlvalue);
					
//					System.out.println(curry+"\t"+sqlvalue);
					String tmpshuzu[] = {sqlvalue};
					newdata.add(tmpshuzu);
					
//					ExcelHelper.updatacell(file, qa, sqlvalue, i, p[1]+pianyi);
					
					//如下是写入Q数据和总数据
//					ExcelHelper.updatacell(file, qa, sqlvalue, i, p[1]+pianyi+3);
//					ExcelHelper.updatacell(file, qa, sqlvalue, i, p[1]+pianyi+5);
				}		
				
//				ExcelHelper.importData(file, qa, newdata, p[0], p[1]+pianyi);
				if (WRITE_TO_EXCEL) {
					ExcelHelper.updatacell(file, qa, newdata, p[0], p[1]+OFFSET);
				}else {
					print_result(newdata);
				}
				
			}else {
				
				if (NUMBER_START-NUMBER_END!=1||NUMBER_START>1) {
					//假如统计的时间段超过1个月，或者距离当前统计时间段超过1个月
					continue;
				}
				
				//先删除之前遗留的数据，保留之前的首行
				ExcelHelper.clear_data(file, sheetName, 1);
				
				int length = Integer.parseInt(lll[2]);
//				用来定义存放数据的变量
				List<String[]> data= new ArrayList<String[]>();
				
				realdata = sq.sql_Query(sql);
				try {
					while (realdata.next()) {
						String line[]= new String[length];
						for (int i = 0; i <length; i++) {
//							System.out.print(rs.getString(i+1)+"\t");
							String tmp;
							try {
								tmp = realdata.getString(i+1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
//								e.printStackTrace();
								tmp = "";
							}
							if (tmp == null) {
//								System.out.println("输出口查出来是空的");
								line[i] = "";
							}else {
								line[i] = tmp;
							}
						}
						data.add(line);
//						System.out.println();
//						System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (realdata != null) {
						try {
							realdata.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//假如是智能软件部和系统软件部还需要再查一次库
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
					detail_sql = detail_sql.replace("?", appType);
					
					if (xiangmu.equals("系统软件部")) {
						detail_sql = detail_sql.replace("Android", "IOS");
					}
					
					realdata = sq.sql_Query(detail_sql);
					
					try {
						while (realdata.next()) {
							String line[]= new String[length];
							for (int i = 0; i <length; i++) {
//								System.out.print(rs.getString(i+1)+"\t");
								String tmp;
								try {
									tmp = realdata.getString(i+1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
//									e.printStackTrace();
									tmp = "";
								}
								if (tmp == null) {
//									System.out.println("输出口查出来是空的");
									line[i] = "";
								}else {
									line[i] = tmp;
								}
							}
							data.add(line);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (realdata != null) {
							try {
								realdata.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				
				if (WRITE_TO_EXCEL) {
					ExcelHelper.importData(file, sheetName, data, 1, 0,xiangmu);
				}else {
					print_result(data);
				}
				
			}
		}
	}
	
	/**
	 * 写入详细数据
	 * @param xiangmu
	 * @param file
	 */
	public static void write_detail_data(String xiangmu,String file) {

//		List<String[]> jichudata = ExcelHelper.readExcel(file, qa, 1);
		XlsOpreation xo = new XlsOpreation();
		SqlHelper sq = new SqlHelper(url, account, password);
		List<String[]> ls = xo.getXls_ArrayList(file1, 0);
//		List<String[]> ls = ExcelHelper.readExcel(file1, "qa_type", cloumnnum)
		ResultSet realdata = null;
		
		
		//获取各个项目组的项目配置
		List<String[]> app_types = xo.getXls_ArrayList(file2, 0);
		Map<String, String> apptypes = new HashMap<String, String>();
		//组装机型类型
		for (String [] app:app_types) {
			String type=app[0];
			String ppp = "";
			for (int i = 1; i < app.length; i++) {
				if (app[i].equals("")) {
					continue;
				}
				if(i==1) {
					ppp = "'" + app[i] + "',";
				}else if (i == app.length-1) {
					ppp = ppp + "'" +  app[i] +"'";
				}else {
					ppp = ppp + "'" +  app[i] +"',";
				}
			}
//			System.out.println(type + "\t" + ppp);
			if (ppp.contains("&")) {
				ppp = ppp.replace("&", "&amp;");
			}
			
			if (ppp.endsWith(",")) {
				ppp = ppp.substring(0,ppp.length()-1);
			}
			
			System.out.println(type + "\t" + ppp);
			apptypes.put(type, ppp);
		}
		
		//这边做一个很简单的影视关系
		Map<String, String> old_new = new HashMap<String, String>();
		int jiange=5;
		for (int i = 0; i < 4; i++) {
			String ttt[] = app_types.get(i);
			for (int j = 1; j < ttt.length; j++) {
				//取当前行的下面5行对应单元格的内容，此内容为映射的内容
				old_new.put(ttt[j], app_types.get(i+jiange)[j]);
			}
		}		
		for(String [] lll:ls) {
			
			//这边定义一些还要二次处理的数据
//			List<String> second_opreate = new ArrayList<String>();
			
			String detail_sql = lll[3];
			
			String sql= lll[1];
			//替换掉sql中的？
			sql = sql.replace("?", apptypes.get(xiangmu));
			
			//替换sql中的(xx)，原因是要剔除新品
			sql = sql.replace("(xx)", "("+Remove_Branch+")");
			
			String sheetName = lll[0];
			
			//这边特殊处理下一些sql语句已经不查询了
			if (sheetName.contains("delete")) {
				continue;
			}
					
			System.out.println(sheetName);
			
			//这个证明要修改统计时间
			if (NUMBER_END!=0||NUMBER_START!=1) {
				String tmp1 = "sssss";
				String tmp2 = "ttttt";
				sql = sql.replace("0 MONTH", tmp1+" MONTH");
				sql = sql.replace("1 MONTH", tmp2+" MONTH");
				sql = sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				sql = sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
				
				detail_sql = detail_sql.replace("0 MONTH", tmp1+" MONTH");
				detail_sql = detail_sql.replace("1 MONTH", tmp2+" MONTH");
				detail_sql = detail_sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				detail_sql = detail_sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
			}
			
			if (!sheetName.endsWith("-统计")) {
				
				/*if (NUMBER_START-NUMBER_END!=1||NUMBER_START>1) {
					//假如统计的时间段超过1个月，或者距离当前统计时间段超过1个月
					continue;
				}*/
				
				//先删除之前遗留的数据，保留之前的首行
				ExcelHelper.clear_data(file, sheetName, 1);
				
				int length = Integer.parseInt(lll[2]);
//				用来定义存放数据的变量
				List<String[]> data= new ArrayList<String[]>();
				
				realdata = sq.sql_Query(sql);
				try {
					while (realdata.next()) {
						String line[]= new String[length];
						for (int i = 0; i <length; i++) {
//							System.out.print(rs.getString(i+1)+"\t");
							String tmp;
							try {
								tmp = realdata.getString(i+1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
//								e.printStackTrace();
								tmp = "";
							}
							if (tmp == null) {
//								System.out.println("输出口查出来是空的");
								line[i] = "";
							}else {
								line[i] = tmp;
							}
						}
						data.add(line);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (realdata != null) {
						try {
							realdata.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				//假如是智能软件部和系统软件部还需要再查一次库
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
					detail_sql = detail_sql.replace("?", appType);
					
					if (xiangmu.equals("系统软件部")) {
						detail_sql = detail_sql.replace("Android", "IOS");
					}
					
					realdata = sq.sql_Query(detail_sql);
					
					try {
						while (realdata.next()) {
							String line[]= new String[length];
							for (int i = 0; i <length; i++) {
//								System.out.print(rs.getString(i+1)+"\t");
								String tmp;
								try {
									tmp = realdata.getString(i+1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
//									e.printStackTrace();
									tmp = "";
								}
								if (tmp == null) {
//									System.out.println("输出口查出来是空的");
									line[i] = "";
								}else {
									line[i] = tmp;
								}
							}
							data.add(line);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (realdata != null) {
							try {
								realdata.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				
				if (WRITE_TO_EXCEL) {
					ExcelHelper.importData(file, sheetName, data, 1, 0,xiangmu);
				}else {
					print_result(data);
				}
				
			}
		}
	
	}
	
	public static void write_statistical_data(String xiangmu,String file) {
		List<String[]> jichudata = ExcelHelper.readExcel(file, qa, 1);
		XlsOpreation xo = new XlsOpreation();
		SqlHelper sq = new SqlHelper(url, account, password);
		List<String[]> ls = xo.getXls_ArrayList(file1, 0);
//		List<String[]> ls = ExcelHelper.readExcel(file1, "qa_type", cloumnnum)
		ResultSet realdata = null;
		
		
		//获取各个项目组的项目配置
		List<String[]> app_types = xo.getXls_ArrayList(file2, 0);
		Map<String, String> apptypes = new HashMap<String, String>();
		//组装机型类型
		for (String [] app:app_types) {
			String type=app[0];
			String ppp = "";
			for (int i = 1; i < app.length; i++) {
				if (app[i].equals("")) {
					continue;
				}
				if(i==1) {
					ppp = "'" + app[i] + "',";
				}else if (i == app.length-1) {
					ppp = ppp + "'" +  app[i] +"'";
				}else {
					ppp = ppp + "'" +  app[i] +"',";
				}
			}
//			System.out.println(type + "\t" + ppp);
			if (ppp.contains("&")) {
				ppp = ppp.replace("&", "&amp;");
			}
			
			if (ppp.endsWith(",")) {
				ppp = ppp.substring(0,ppp.length()-1);
			}
			
			System.out.println(type + "\t" + ppp);
			apptypes.put(type, ppp);
		}
		
		//这边做一个很简单的影视关系
		Map<String, String> old_new = new HashMap<String, String>();
		int jiange=5;
		for (int i = 0; i < 4; i++) {
			String ttt[] = app_types.get(i);
			for (int j = 1; j < ttt.length; j++) {
				//取当前行的下面5行对应单元格的内容，此内容为映射的内容
				old_new.put(ttt[j], app_types.get(i+jiange)[j]);
			}
		}
		
		for(String [] lll:ls) {
			
			//这边定义一些还要二次处理的数据
			List<String> second_opreate = new ArrayList<String>();
			
			String detail_sql = lll[3];
			
			String sql= lll[1];
			//替换掉sql中的？
			sql = sql.replace("?", apptypes.get(xiangmu));
			
			//替换sql中的(xx)，原因是要剔除新品
			sql = sql.replace("(xx)", "("+Remove_Branch+")");
			
			String sheetName = lll[0];
			
			//这边特殊处理下一些sql语句已经不查询了
			if (sheetName.contains("delete")||sheetName.contains("每月遗留需求")) {
				continue;
			}
			
			System.out.println(sheetName);
			//这个证明要修改统计时间
			if (NUMBER_END!=0||NUMBER_START!=1) {
				String tmp1 = "sssss";
				String tmp2 = "ttttt";
				sql = sql.replace("0 MONTH", tmp1+" MONTH");
				sql = sql.replace("1 MONTH", tmp2+" MONTH");
				sql = sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				sql = sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
				
				detail_sql = detail_sql.replace("0 MONTH", tmp1+" MONTH");
				detail_sql = detail_sql.replace("1 MONTH", tmp2+" MONTH");
				detail_sql = detail_sql.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
				detail_sql = detail_sql.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
			}
			
			if (sheetName.endsWith("-统计")) {
				//这边判断再统计季度数据与总数据的时候只需要统计个别数据
				if (NUMBER_START-NUMBER_END!=1 && !(sheetName.contains("版本通过率")||sheetName.contains("交付版本质量分")||sheetName.contains("问题需求比")||sheetName.contains("总遗留bug数")||sheetName.contains("总遗留需求数"))) {
					continue;
				}
				
				String total = "";
				//从数据库获取对应的app的数据
				realdata = sq.sql_Query(sql);
				
				//用一个map来存放对应的数据
				Map<String, String>  map = new HashMap<String, String>();
				try {
//					realdata.next();
					while(realdata.next()) {
//						System.out.println(realdata.getString(1)+"\t"+realdata.getString(2));
						String temp1 = realdata.getString(1);
						String temp2 = realdata.getString(2);
						if (temp1.contains(zhuanyi)) {
							temp1=temp1.replace(zhuanyi, "&");
						}
//						System.out.println(old_new.get(temp1)+"\t"+temp2+"\t");
						//产品质量分药算平均值
						if (sheetName.equals("交付版本质量分-统计")) {
							int countnum=realdata.getInt(3);
							if (temp2.contains(",")) {
								temp2=temp2.replace(",", "");
							}
							float fff=Float.parseFloat(temp2);
							String floatformat = String.format("%.2f", fff/countnum);
							map.put(old_new.get(temp1), floatformat);
						}else {
							map.put(old_new.get(temp1), temp2);
						}
						if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
							second_opreate.add(realdata.getString(3));
							second_opreate.add(realdata.getString(4));
						}else if (sheetName.equals("交付版本质量分-统计")) {
							second_opreate.add(realdata.getString(3));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					if (realdata!=null) {
						try {
							realdata.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//这边假如是只能软件部或者系统软件部还需要再查一次数据
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")) {
					
					String sql1 = lll[3];
					sql1 = sql1.replace("?", appType);
					
					if (NUMBER_END!=0||NUMBER_START!=1) {
						String tmp1 = "sssss";
						String tmp2 = "ttttt";
						sql1 = sql1.replace("0 MONTH", tmp1+" MONTH");
						sql1 = sql1.replace("1 MONTH", tmp2+" MONTH");
						sql1 = sql1.replace(tmp1+" MONTH", NUMBER_END+" MONTH");
						sql1 = sql1.replace(tmp2+" MONTH", NUMBER_START+" MONTH");
					}
					
					realdata = sq.sql_Query(sql1);
					try {
						while(realdata.next()) {
//							System.out.println(realdata.getString(1)+"\t"+realdata.getString(2));
							String tempstr = realdata.getString(2);
							if (sheetName.equals("交付版本质量分-统计")) {
								int countnum=realdata.getInt(3);
								if (tempstr.contains(",")) {
									tempstr=tempstr.replace(",", "");
								}
								float fff=Float.parseFloat(tempstr);
								tempstr = String.format("%.2f", fff/countnum);
							}
							String ios_android=realdata.getString(1);
							if (ios_android==null) {
								continue;
							}
							if (xiangmu.equals("智能软件部")&&ios_android.equals("Android")) {								
								map.put("APP", tempstr);
								if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
									second_opreate.add(realdata.getString(3));
									second_opreate.add(realdata.getString(4));
								}else if (sheetName.equals("交付版本质量分-统计")) {
									second_opreate.add(realdata.getString(3));
								}
							}else if (xiangmu.equals("系统软件部")&&ios_android.equals("IOS")) {
								map.put("APP", tempstr);
								if (sheetName.equals("版本通过率-统计")||sheetName.equals("总遗留bug数-统计")) {
									second_opreate.add(realdata.getString(3));
									second_opreate.add(realdata.getString(4));
								}else if (sheetName.equals("交付版本质量分-统计")) {
									second_opreate.add(realdata.getString(3));
								}
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						if (realdata!=null) {
							try {
								realdata.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
				
				//这边开始构建数据,现在数据库查出来的数据是不包括总数，需要自己计算				
				float tmpfloat=0;
				int tmpint=0;
				boolean t=false;
				int q_1=0;
				int q_2=0;
						
				for(String vvv : map.values()) {
					if (sheetName.equals("问题需求比-统计")) {
						String qqq[]=vvv.split("/");
						q_1 += Integer.parseInt(qqq[0]);
						q_2 += Integer.parseInt(qqq[1]);
						continue;
					}else if (sheetName.equals("版本通过率-统计")) {
						continue;
					}else {
						if(vvv.contains(".")) {
							t=true;
							if (vvv.contains(",")) {
								vvv = vvv.replace(",", "");
							}
							tmpfloat += Float.parseFloat(vvv);
						}else {
							tmpint += Integer.parseInt(vvv);
						}
					}
 				}
				
				String bug_fenlei="";
				
				//这边对不能直接相加来算综合的进行处理：例如版本通过率，质量分等
				if (sheetName.equals("版本通过率-统计")) {
					float all =0;
					float pass=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						if (i % 2 ==0) {
							all += Float.parseFloat(second_opreate.get(i));
//							all += Integer.parseInt(second_opreate.get(i));
						}else {
//							pass += Integer.parseInt(second_opreate.get(i));
							pass += Float.parseFloat(second_opreate.get(i));
						}
					}
					if (all == 0) {
						all =1;
					}
					float rate = pass/all;
					DecimalFormat df = new DecimalFormat("0.00%");
					total = df.format(rate);
				}else if (sheetName.equals("交付版本质量分-统计")) {
					int c=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						c += Integer.parseInt(second_opreate.get(i));
					}
					tmpfloat = tmpfloat/c; 
					String floatformat = String.format("%.2f", tmpfloat);
					tmpfloat = Float.parseFloat(floatformat);
				}else if (sheetName.equals("总遗留bug数-统计")) {
					int resolved =0;
					int unresolved=0;
					for (int i = 0; i < second_opreate.size(); i++) {
						if (i % 2 ==0) {
							resolved += Integer.parseInt(second_opreate.get(i));
						}else {
							unresolved += Integer.parseInt(second_opreate.get(i));
						}
					}
					
					bug_fenlei = "("+resolved+"/"+unresolved+")";
					
				}
				
				//假如是float类型的，转换成string类型
				if (t) {
					total = tmpfloat+"";
				}else {
					if (!sheetName.equals("版本通过率-统计")) {
						total=tmpint+"";
					}
				}
				
				//问题需求比的形式是这样的10/15
				if (sheetName.equals("问题需求比-统计")) {
//					total = q_1+"/"+q_2;
//					total =(float)(Math.round(q_1/q_2*100))/100+"";
					total = String.format("%.2f", (float)q_1/q_2);
				}
				
				//特殊拼接所有遗留bug
				if (sheetName.equals("总遗留bug数-统计")) {
					total +=bug_fenlei;
				}
				
				sheetName = sheetName.replace("-统计", "");
				// 获取当前统计信息的开始位置
				int [] p = get_coordinate(jichudata, sheetName);
				System.out.println(p[0]+"\t"+p[1]);
				
				// 组装数据
				List<String[]> newdata = new ArrayList<String[]>();
				String tmp[] = {total};
				newdata.add(tmp);
				// 这边进行组装数据的长度
				int allsize=0;
				if (xiangmu.equals("智能软件部")||xiangmu.equals("系统软件部")||xiangmu.equals("嵌入式软件部")) {
					allsize=branch_1;
				}else if (xiangmu.equals("福州软件部")) {
					allsize=branch_2;
				}
//			    System.out.println(p[0]+"\t"+map.keySet().size()+"\t"+(p[0]+map.keySet().size()+1));
				System.out.println(sheetName);
				for(int i = p[0]+1; i<p[0]+allsize+1;i++) {
//					System.out.println();
//					System.out.println(i+"\t"+(i+));
					//这边有个简单的影视
					String curry = jichudata.get(i)[p[1]];
					String sqlvalue="0";
					if (map.get(curry)!=null) {
						sqlvalue = map.get(curry);
					}
					//问题需求比转换成小数显示
					if (sheetName.equals("问题需求比")&&!sqlvalue.equals("0")&&sqlvalue.contains("/")) {
						int indexxxxxx = sqlvalue.indexOf("/");						
						int qian = Integer.parseInt(sqlvalue.substring(0, indexxxxxx));
						int hou = Integer.parseInt(sqlvalue.substring(indexxxxxx+1));	
						if (hou==0) {
							hou=1;
						}
						sqlvalue=String.format("%.2f", (float)qian/hou);					
					}
					String tmpshuzu[] = {sqlvalue};
					newdata.add(tmpshuzu);
				}		
				
				if (WRITE_TO_EXCEL) {
					ExcelHelper.updatacell(file, qa, newdata, p[0], p[1]+OFFSET);
				}else {
					print_result(newdata);
				}
				
			}
		}
	}
	
	public static int[] get_coordinate(List<String[]> ll, String name) {
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
	
	public static int get_row(List<String[]> ll, String type, int rownum, int cloumnnum, int maxrownum) {
		
		for (int i = rownum; i < rownum+maxrownum; i++) {
			String cell = ll.get(i)[cloumnnum];
			if (cell.equals(type)) {
				return i;
			}
			
		}
		
		return 0;
		
	}
	
	/**
	 * 复制文件功能
	 * @param source
	 * @param copy
	 * @return
	 * @throws Exception
	 */
    public static boolean copyFile(String source, String copy) throws Exception {
        source = source.replace("\\", "/");
        copy = copy.replace("\\", "/");

        File source_file = new File(source);
        File copy_file = new File(copy);

        // BufferedStream缓冲字节流

        if (!source_file.exists()) {
            throw new IOException("文件复制失败：源文件（" + source_file + "） 不存在");
        }
        if (copy_file.isDirectory()) {
            throw new IOException("文件复制失败：复制路径（" + copy_file + "） 错误");
        }
        File parent = copy_file.getParentFile();
        // 创建复制路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 创建复制文件
        if (!copy_file.exists()) {
            copy_file.createNewFile();
        }else {
			copy_file.delete();
			copy_file.createNewFile();
		}

        FileInputStream fis = new FileInputStream(source_file);
        FileOutputStream fos = new FileOutputStream(copy_file);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] KB = new byte[1024];
        int index;
        while ((index = bis.read(KB)) != -1) {
            bos.write(KB, 0, index);
        }

        bos.close();
        bis.close();
        fos.close();
        fis.close();

        if (!copy_file.exists()) {
            return false;
        } else if (source_file.length() != copy_file.length()) {
            return false;
        } else {
            return true;
        }

    }
    
    public static void print_result(List<String[]>  ls) {
    	for(String str[]:ls) {
    		for(String ttt:str) {
    			System.out.print(ttt+"\t");
    		}
    		System.out.println();
    	}
    	
    }
   
    /**
     * 获取当前统计的月份，格式为202009
     * @return
     */
    public static String get_tongjidate() {
    	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		String year = sdf1.format(new Date());
		SimpleDateFormat sdf2=new SimpleDateFormat("MM");
		String month =sdf2.format(new Date());
		int m=Integer.parseInt(month);
		if(m==1) {
			m = 12;
		}else {
			m = m-1;
		}
//		if (NUMBER_END<0) {
//			m=m-NUMBER_END;
//		}
		m = m-NUMBER_END;
		if (m <= 0) {
			m = m+12;
		}
		
		String time="";
		if (m<10) {
			time = year+"0"+m;
		}else {
			time = year+m;
		}
//		System.out.println(time);
		return time;
    }
    
    public static int[] set_number() {
    	String date = get_tongjidate();
    	int m = Integer.parseInt(date.substring(4));
		
		SimpleDateFormat sdf2=new SimpleDateFormat("MM");
		String month =sdf2.format(new Date());
		int c_m=Integer.parseInt(month);
		int starts[] = new int[3];
		if (m == c_m) {//证明统计月份与当前月份是同一月份
			NUMBER_END = -1;
			starts[0] = 0;
		}else{
			if (c_m - m == 1) {
				NUMBER_END = 0;
				starts[0] = 1;
			}
		}
		int mol = m % 3;
		
		if (mol == 0) {
			starts[1] =3+NUMBER_END;
		}else if (mol == 1) {
			starts[1] =1+NUMBER_END;
		}else if (mol == 2) {
			starts[1] =2+NUMBER_END;
		}
		
		starts[2] = 0;
		return starts;
    }
    
    /**
     * 设置NUMBER_END变量，很多都依据这个变量来处理，所以这个方法需要放到最前面
     */
    public static void set_num_end() {
    	File tmp_files = new File("qa/lastmonth");
		int m = 0;
    	for(File ff:tmp_files.listFiles()) {
    		if (ff.getName().contains(".xlsx")) {
				String name = ff.getName();
				int sta = name.indexOf("部");
				String ret = name.substring(sta+5,sta+7);
				if (!ret.equals("")) {
					m =Integer.parseInt(ret);
					break;
				}
			}
    	}
    	SimpleDateFormat sdf2=new SimpleDateFormat("MM");
		String month =sdf2.format(new Date());
		int c_m=Integer.parseInt(month);
		
		int xuyao = m + 1;
		
		if (c_m == m) {
			
		}
		
		NUMBER_END = Math.abs(c_m-xuyao) -1 ;
		
    }
    
    /**
     * 获取统计季度数据的时候除了当前月份的其他月份
     * @return
     */
    public static List<Integer> get_current_quarter() {
    	List<Integer> month = new ArrayList<Integer>();
    	String date = get_tongjidate();
    	int m = Integer.parseInt(date.substring(4));
    	int mol = m % 3;
    	
    	if (mol == 0) {
    		month.add(m-2);
			month.add(m-1);
		}else if (mol == 1) {
//			month.add(arg0);
		}else if (mol == 2) {
			month.add(m-1);
		}
    	return month;
    }
    
    /**
     * 获取从开始统计到当前一个月所有统计的月份
     * @return
     */
    public static Map<String, List<Integer>> get_all_month() {
    	Map<String, List<Integer>> all_month = new HashMap<String, List<Integer>>();
    	String date = get_tongjidate();
    	int m = Integer.parseInt(date.substring(4));
    	for(int i = 0;i < start_month.length; i++) {
    		List<Integer> tmp = new ArrayList<Integer>();
    		for (int j = start_month[i]; j < m; j++) {
				tmp.add(j);
			}
    		if (i==0) {
				all_month.put("福州软件部", tmp);
			}else if (i==1) {
				all_month.put("嵌入式软件部", tmp);
			}else if (i==2) {
				all_month.put("系统软件部", tmp);
			}else if (i==3) {
				all_month.put("智能软件部", tmp);
			}
    	}
    	return all_month;
    }

}
