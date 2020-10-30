package com.tools;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

public class XmlJsonUtil {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp ="<root>\r\n" + 
				"<head>\r\n" + 
				"<OrgNum>0000101</OrgNum>\r\n" + 
				"<TermiId></TermiId>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<cust_id>QY201600006082</cust_id>\r\n" + 
				"<cust_no>9542108405</cust_no>\r\n" + 
				"<loan_mortgages></loan_mortgages>\r\n" + 
				"</body>\r\n" + 
				"</root>";
		System.out.println(XmlJsonUtil.XmlToJson(tmp));
		
		
	}*/
	
	/**
     * @Description: xml convert to json
     * @author wangyan_z
     * @date 2019年7月10日 上午10:50:32
     */
    public static String XmlToJson(String xmlString){
 
        StringReader input = new StringReader(xmlString);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }
 
    /**
     * @Description: json convert to xml
     * @author wangyan_z
     * @date 2019年7月10日 上午10:52:32
     */
    public static String JsonToXml(String jsonString){
        StringReader input = new StringReader(jsonString);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // remove <?xml version="1.0" encoding="UTF-8"?>
        if (output.toString().length() >= 38) {
            return output.toString().substring(39);
        }
        return output.toString();
    }
    /**
     * @Description: 去掉xml中的换行和空格
     * @author wangyan_z
     * @date 2019年7月11日 下午4:05:40
     */
    public static String JsonToXmlReplaceBlank(String jsonString) {
        String str = XmlJsonUtil.JsonToXml(jsonString);
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
