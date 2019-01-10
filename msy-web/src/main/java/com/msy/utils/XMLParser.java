package com.msy.utils;

import java.util.Map;

/**
 */
public class XMLParser {

    @SuppressWarnings("unchecked")
	public static Map<String, String> getMapFromXML(String xmlString){

        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = Util.getStringStream(xmlString);
        Document document = builder.parse(is);

        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getNodeValue());
            }
            i++;
        }
        return map;*/
    	
    	return XmlUtil.ParseXML(xmlString);

    }
    
    public static void main(String[] args){
    	String abc="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxf8e130a1cc83c3b6]]></appid><mch_id><![CDATA[1266073301]]></mch_id><nonce_str><![CDATA[iBKGqqbwTipIPqdu]]></nonce_str><sign><![CDATA[608C16D2C5EB66F2F812E7ED35677F8F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2016061721041243d7cf612a0022999032]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></xml>";
    	abc = abc +"\n";
    	System.out.println(getMapFromXML(abc));
    }

}
