package com.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	
	private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    
    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileType 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }
    
    /**
     * 
     * @param fileName
     * @param sheetname
     * @param cloumnnum
     * @return
     */
    public static List<String[]> readExcel(String fileName, String sheetname, int cloumnnum) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                System.out.println("文件不存在");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<String[]> resultDataList = parseExcel(workbook,sheetname,cloumnnum);

            return resultDataList;
        } catch (Exception e) {
            System.out.println("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                System.out.println("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }
    
    /**
     * 读取excel的数据
     * @param workbook
     * @param sheetname 用于读取excel的sheet的名字
     * @param columnnum 用于读取的列数，假如为0的话就表示读取全部，其他的话按实际对应的列
     * @return
     */
    private static List<String[]> parseExcel(Workbook workbook, String sheetname, int columnnum) {
        List<String[]> resultDataList = new ArrayList<String[]>();
         // 解析sheet
//         for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
//             Sheet sheet = workbook.getSheetAt(sheetNum);
//
//             // 校验sheet是否合法
//             if (sheet == null) {
//                 continue;
//             }
        Sheet sheet = workbook.getSheet(sheetname);

             // 获取第一行数据
             int firstRowNum = sheet.getFirstRowNum();
             Row firstRow = sheet.getRow(firstRowNum);
             if (null == firstRow) {
                 System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
             }

             // 解析每一行的数据，构造数据对象
             int rowStart = firstRowNum + 1;
//             int rowEnd = sheet.getPhysicalNumberOfRows();
             int rowEnd = sheet.getLastRowNum();
             
             for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
                 Row row = sheet.getRow(rowNum);

                 if (null == row) {
                     continue;
                 }
                 
//                 int rowlength = row.getPhysicalNumberOfCells();
                 int rowlength = row.getLastCellNum();
                 
                 String [] line = new String[rowlength];
                 for (int i = 0; i < rowlength; i++) {
                	 
					String tmp = convertCellValueToString(row.getCell(i));
//					System.out.print(tmp+"\t");
					line[i] = tmp;
				 }
//                 System.out.println();
                 
                 resultDataList.add(line);
             }

         return resultDataList;
     }

/**
 * 将单元格内容转换为字符串
 * @param cell
 * @return
 */
private static String convertCellValueToString(Cell cell) {
    if(cell==null){
        return null;
    }
    String returnValue = null;
    switch (cell.getCellType()) {
        case NUMERIC:   //数字
            Double doubleValue = cell.getNumericCellValue();

            // 格式化科学计数法，取一位整数
            DecimalFormat df = new DecimalFormat("0");
            returnValue = df.format(doubleValue);
            break;
        case STRING:    //字符串
            returnValue = cell.getStringCellValue();
            break;
        case BOOLEAN:   //布尔
            Boolean booleanValue = cell.getBooleanCellValue();
            returnValue = booleanValue.toString();
            break;
        case BLANK:     // 空值
            break;
        case FORMULA:   // 公式
            returnValue = cell.getCellFormula();
            break;
        case ERROR:     // 故障
            break;
        default:
            break;
    }
    return returnValue;
}

/**
 * 把数据导入到sheet表，可以指定从特定行特定列开始
 * @param fileName
 * @param sheetname
 * @param ls
 * @param rowstart
 * @param cloumnstart
 */
public static void importData(String fileName,String sheetname, List<String[]> ls, int rowstart, int cloumnstart,String xiangmu){
	
	Workbook workbook = null;
	FileInputStream inputStream =null;
	Sheet sheet = null;
	// 以文件的形式输出工作簿对象
    FileOutputStream fileOut = null;
//    POIFSFileSystem poi = null;
//    XSSFFactory poi1 = null;
	try {
        // 获取Excel后缀名
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 获取Excel文件
        File excelFile = new File(fileName);
        if (!excelFile.exists()) {
//        	假如工作簿不存在则创建一个，假如存在的话就获取对应的工作普
        	excelFile.createNewFile();
        	workbook = new SXSSFWorkbook();
        	sheet =workbook.createSheet(sheetname);
        }else {
        	inputStream = new FileInputStream(excelFile);
        	
//        	if (fileType.equalsIgnoreCase(XLS)) {
//        		poi = new POIFSFileSystem(inputStream);
//				workbook = new HSSFWorkbook(poi);
//			}else if (fileType.equalsIgnoreCase(XLSX)) {
//				poi1 = new 
//				workbook = new XSSFWorkbook(poi);
//			}
        	workbook = new XSSFWorkbook(inputStream);
        	sheet = workbook.getSheet(sheetname);
        	
//			workbook = getWorkbook(inputStream, fileType);
//			sheet = workbook.getSheet(sheetname);
		}
//        sheet.setDefaultColumnWidth(4000);
//        sheet.setDefaultRowHeight((short) 300);
    	
        for(int i=0;i < ls.size(); i++) {
        	String line[] = ls.get(i);
        	Row row = sheet.createRow(rowstart++);
        	Cell cell = null;
        	for (int j = 0; j < line.length; j++) {
        		String tmp= line[j];
        		if(tmp.contains("&amp;")) {
        			tmp = tmp.replace("&amp;", "&");
        		}
        		
        		if (xiangmu.equals("福州软件部")&&sheetname.equals("Y产品问题")&&j==3) {
        			tmp="福州软件部";
				}else if (xiangmu.equals("系统软件部")&&sheetname.equals("Y产品问题")&&j==3) {
					tmp="系统软件部";
				}else if (xiangmu.equals("智能软件部")&&sheetname.equals("Y产品问题")&&j==3) {
					tmp="智能软件部";
				}
        		
        		cell = row.createCell(j+cloumnstart);
        		cell.setCellValue(tmp);
        		if (j==1) {
        			//设置超链接
					get_link(workbook, cell, sheetname, tmp);
				}
        	}
        	
        }
        
        fileOut = new FileOutputStream(excelFile);
        workbook.write(fileOut);
        fileOut.flush();
        } catch (Exception e) {
            System.out.println("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != inputStream) {
					inputStream.close();
				}
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
	}

}

/**
 * 指定更新特定cell的值
 * @param fileName
 * @param sheetname
 * @param value
 * @param rowindex
 * @param cloumnindex
 */
public static void updatacell(String fileName,String sheetname, String value, int rowindex, int cloumnindex){
	
	Workbook workbook = null;
	FileInputStream inputStream =null;
	Sheet sheet = null;
	// 以文件的形式输出工作簿对象
    FileOutputStream fileOut = null;
    CellStyle cs =null;
//    POIFSFileSystem poi = null;
//    XSSFFactory poi1 = null;
	try {
        // 获取Excel后缀名
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 获取Excel文件
        File excelFile = new File(fileName);
        if (!excelFile.exists()) {
//        	假如工作簿不存在则创建一个，假如存在的话就获取对应的工作普
        	excelFile.createNewFile();
        	workbook = new SXSSFWorkbook();
        	sheet =workbook.createSheet(sheetname);
        }else {
        	inputStream = new FileInputStream(excelFile);
        	
//        	if (fileType.equalsIgnoreCase(XLS)) {
//        		poi = new POIFSFileSystem(inputStream);
//				workbook = new HSSFWorkbook(poi);
//			}else if (fileType.equalsIgnoreCase(XLSX)) {
//				poi1 = new 
//				workbook = new XSSFWorkbook(poi);
//			}
        	workbook = new XSSFWorkbook(inputStream);
        	sheet = workbook.getSheet(sheetname);
        	cs = workbook.createCellStyle();
        	//对齐方式设置
        	cs.setAlignment(HorizontalAlignment.CENTER);
            //边框颜色和宽度设置
        	cs.setBorderBottom(BorderStyle.THIN);
        	cs.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        	cs.setBorderLeft(BorderStyle.THIN);
        	cs.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        	cs.setBorderRight(BorderStyle.THIN);
        	cs.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        	cs.setBorderTop(BorderStyle.THIN);
        	cs.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
            //设置背景颜色
//        	cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        	cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//			workbook = getWorkbook(inputStream, fileType);
//			sheet = workbook.getSheet(sheetname);
		}
        
        //获取行，然后对单元格赋值
        Row row = sheet.getRow(rowindex+1);
        Cell cell = row.createCell(cloumnindex);
        cell.setCellValue(value);
        cell.setCellStyle(cs);
        
        // 把文件写入到excel
        fileOut = new FileOutputStream(excelFile);
        workbook.write(fileOut);
        fileOut.flush();
        } catch (Exception e) {
            System.out.println("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != inputStream) {
					inputStream.close();
				}
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
	}

}

/**
 * 更新对应xlsx的单元格的内容
 * @param fileName
 * @param sheetname
 * @param ls
 * @param rowindex
 * @param cloumnindex
 */
public static void updatacell(String fileName,String sheetname, List<String[]> ls, int rowindex, int cloumnindex){
	Workbook workbook = null;
	FileInputStream inputStream =null;
	Sheet sheet = null;
	// 以文件的形式输出工作簿对象
    FileOutputStream fileOut = null;
//    POIFSFileSystem poi = null;
//    XSSFFactory poi1 = null;
    CellStyle cs =null;
	try {
        // 获取Excel后缀名
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 获取Excel文件
        File excelFile = new File(fileName);
        if (!excelFile.exists()) {
//        	假如工作簿不存在则创建一个，假如存在的话就获取对应的工作普
        	excelFile.createNewFile();
        	workbook = new SXSSFWorkbook();
        	sheet =workbook.createSheet(sheetname);
        }else {
        	inputStream = new FileInputStream(excelFile);
        	workbook = new XSSFWorkbook(inputStream);
        	sheet = workbook.getSheet(sheetname);
        	cs = workbook.createCellStyle();
        	//对齐方式设置
        	cs.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
        	cs .setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中
            //边框颜色和宽度设置
        	cs.setBorderBottom(BorderStyle.THIN);
        	cs.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        	cs.setBorderLeft(BorderStyle.THIN);
        	cs.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        	cs.setBorderRight(BorderStyle.THIN);
        	cs.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        	cs.setBorderTop(BorderStyle.THIN);
        	cs.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
//			workbook = getWorkbook(inputStream, fileType);
//			sheet = workbook.getSheet(sheetname);
		}
//        sheet.setDefaultColumnWidth(4000);
//        sheet.setDefaultRowHeight((short) 300);
    	
        for(int i=0;i < ls.size(); i++) {
        	String line[] = ls.get(i);
//        	Row row = sheet.createRow(rowindex++);
        	Row row = sheet.getRow(i+rowindex+1);
        	Cell cell = null;
        	for (int j = 0; j < line.length; j++) {
        		cell = row.createCell(j+cloumnindex);
        		String data = line[j];
        		
        		Boolean isNum = false;//data是否为数值型
                Boolean isInteger=false;//data是否为整数
                Boolean isPercent=false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger=data.toString().matches("^[-\\+]?[\\d]*$");
                    //判断data是否为百分数（是否包含“%”）
                    isPercent=data.toString().contains("%");
                }
                
              //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum && !isPercent) {
                	
                	XSSFDataFormat df = (XSSFDataFormat) workbook.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        cs.setDataFormat(df.getFormat("#,#0"));//数据格式只显示整数
                    }else{
                    	cs.setDataFormat(df.getFormat("#,##0.00"));//保留两位小数点
                    }                   
                    // 设置单元格格式
                    cell.setCellStyle(cs);
                    // 设置单元格内容为double类型
                    cell.setCellValue(Double.parseDouble(data.toString()));
                } else {
                    cell.setCellStyle(cs);
                    // 设置单元格内容为字符型
                    cell.setCellValue(data.toString());
                }
//        		
//        		cell.setCellValue(data);
//        		cell.setCellStyle(cs);
        	}
        	
        }
        
        
        fileOut = new FileOutputStream(excelFile);
        workbook.write(fileOut);
        fileOut.flush();
        } catch (Exception e) {
            System.out.println("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != inputStream) {
					inputStream.close();
				}
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
	}

}


/**
 * 设置workbook对应的cell的超链接
 * @param workbook
 * @param cell
 * @param type
 * @param bugid
 */
public static void get_link(Workbook workbook, Cell cell, String type, String bugid) {
	CreationHelper creationHelper = workbook.getCreationHelper();
	Hyperlink link = creationHelper.createHyperlink(HyperlinkType.URL);
	
	//设置超链接的字体
	CellStyle hlink_style = workbook.createCellStyle();  
	Font hlink_font = workbook.createFont();  
    hlink_font.setUnderline(Font.U_SINGLE);  
    hlink_font.setColor(IndexedColors.BLUE.getIndex());  
    hlink_style.setFont(hlink_font);  
	
    //设置超链接的链接地址
	String url ="";
	if(type.equals("Y产品问题")||type.equals("Y每月预期交付需求")||type.equals("Y当月预期交付需求已交付需求")||type.equals("Y每月遗留需求")||type.equals("Y总遗留需求")) {
		url = "http://192.168.10.27:81/zentao/story-view-"+bugid+".html";
	}else if (type.equals("Y版本提测")) {
		url = "http://192.168.10.27:81/zentao/testtask-view-"+bugid+".html";
	}else {
		url = "http://192.168.10.27:81/zentao/bug-view-"+bugid+".html";
	}
	
	link.setAddress(url);
	cell.setHyperlink(link);
	cell.setCellStyle(hlink_style);
}

/**
 * 删除对应xlsx中的数据，从执行的start_row开始，从0开始
 * @param fileName
 * @param sheetname
 * @param start_row
 */
public static void clear_data(String fileName,String sheetname,int start_row) {
	Workbook workbook = null;
	FileInputStream inputStream =null;
	Sheet sheet = null;
	// 以文件的形式输出工作簿对象
    FileOutputStream fileOut = null;
	try {
        // 获取Excel后缀名
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 获取Excel文件
        File excelFile = new File(fileName);
        if (!excelFile.exists()) {
//        	假如工作簿不存在则创建一个，假如存在的话就获取对应的工作普
        	return;
        }else {
        	inputStream = new FileInputStream(excelFile);
        	workbook = new XSSFWorkbook(inputStream);
        	sheet = workbook.getSheet(sheetname);
		}
        
        int rowNum = sheet.getLastRowNum();
        for (int i = start_row; i <= rowNum; i++) {
//        	System.out.println(i);
        	Row row = sheet.getRow(i);
        	// 删除行的内容
        	if (row != null) {
        		sheet.removeRow(row);
			}
		}
        
        
        fileOut = new FileOutputStream(excelFile);
        workbook.write(fileOut);
        fileOut.flush();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != inputStream) {
					inputStream.close();
				}
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
	}
}

}
