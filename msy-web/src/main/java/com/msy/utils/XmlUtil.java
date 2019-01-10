/**   
* @Title: XmlUtil.java 
* @Package 
* @Description: TODO 
* @author chesir   
* @date 2016-2-1 上午11:42:22 
* @version V1.0   
*/
package com.msy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



/**
 *
 */
public class XmlUtil {
	private static final Logger logger = Logger.getLogger(XmlUtil.class);
	/**
     * Serialise the supplied W3C DOM subtree.
     * <p/>
     * The output is unformatted.
     *
     * @param nodeList The DOM subtree as a NodeList.
     * @return The subtree in serailised form.
     * @throws DOMException Unable to serialise the DOM.
     */
    public static String serialize(NodeList nodeList) throws DOMException {
        return serialize(nodeList, false);
    }

    /**
     * Serialise the supplied W3C DOM subtree.
     *
     * @param node The DOM node to be serialized.
     * @param format Format the output.
     * @return The subtree in serailised form.
     * @throws DOMException Unable to serialise the DOM.
     */
    public static String serialize(final Node node, boolean format) throws DOMException {
        StringWriter writer = new StringWriter();
        serialize(node, format, writer);
        return writer.toString();
    }

    /**
     * Serialise the supplied W3C DOM subtree.
     *
     * @param node The DOM node to be serialized.
     * @param format Format the output.
     * @param writer The target writer for serialization.
     * @throws DOMException Unable to serialise the DOM.
     */
    public static void serialize(final Node node, boolean format, Writer writer) throws DOMException {
        if(node.getNodeType() == Node.DOCUMENT_NODE) {
            serialize(node.getChildNodes(), format, writer);
        } else {
            serialize(new NodeList() {
                public Node item(int index) {
                    return node;
                }

                public int getLength() {
                    return 1;
                }
            }, format, writer);
        }
    }

    /**
     * Serialise the supplied W3C DOM subtree.
     *
     * @param nodeList The DOM subtree as a NodeList.
     * @param format Format the output.
     * @return The subtree in serailised form.
     * @throws DOMException Unable to serialise the DOM.
     */
    public static String serialize(NodeList nodeList, boolean format) throws DOMException {
        StringWriter writer = new StringWriter();
        serialize(nodeList, format, writer);
        return writer.toString();
    }

    /**
     * Serialise the supplied W3C DOM subtree.
     *
     * @param nodeList The DOM subtree as a NodeList.
     * @param format Format the output.
     * @param writer The target writer for serialization.
     * @throws DOMException Unable to serialise the DOM.
     */
    public static void serialize(NodeList nodeList, boolean format, Writer writer) throws DOMException {

        if (nodeList == null) {
            throw new IllegalArgumentException(
                    "null 'subtree' NodeIterator arg in method call.");
        }

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer;

            if(format) {
                try {
                    factory.setAttribute("indent-number", new Integer(4));
                } catch(Exception e) {
                    // Ignore... Xalan may throw on this!!
                    // We handle Xalan indentation below (yeuckkk) ...
                }
            }
            transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            if(format) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
            }

            int listLength = nodeList.getLength();

            // Iterate through the Node List.
            for (int i = 0; i < listLength; i++) {
                Node node = nodeList.item(i);

                if (XmlUtil.isTextNode(node)) {
                    writer.write(node.getNodeValue());
                } else if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
                    writer.write(((Attr) node).getValue());
                } else if (node.getNodeType() == Node.ELEMENT_NODE) {
                    transformer.transform(new DOMSource(node), new StreamResult(writer));
                }
            }
        } catch (Exception e) {
            DOMException domExcep = new DOMException(
                    DOMException.INVALID_ACCESS_ERR,
                    "Unable to serailise DOM subtree.");
            domExcep.initCause(e);
            throw domExcep;
        }
    }

    /**
     * Is the supplied W3C DOM Node a text node.
     *
     * @param node The node to be tested.
     * @return True if the node is a text node, otherwise false.
     */
    public static boolean isTextNode(Node node) {
        short nodeType;

        if (node == null) {
            return false;
        }
        nodeType = node.getNodeType();

        return nodeType == Node.CDATA_SECTION_NODE
                || nodeType == Node.TEXT_NODE;
    }
    
    @SuppressWarnings("rawtypes")
	public static Map ParseXML(String xmlStr){
		Map ret = new HashMap();
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			SoapResponse myhandler = new SoapResponse();

			parser.parse(new InputSource(new StringReader(xmlStr)), myhandler);
			
			ret = myhandler.resmap;
			logger.debug(String.format("ParseXML return: %s", ret.toString()));
		} catch (Exception e) {
			logger.debug(String.format("ParseXML Excption %s:", e.getMessage()));
			e.printStackTrace();
		} 
		return ret;

	}

    /**
     * 转XMLmap
     * @author  
     * @param xmlBytes
     * @param charset
     * @return
     * @throws Exception
     */
    public static Map<String, String> toMap(byte[] xmlBytes,String charset) throws Exception{
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Document doc = reader.read(source);
        Map<String, String> params = toMap(doc.getRootElement());
        return params;
    }
    
    /** <一句话功能简述>
     * <功能详细描述>request转字符串
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String parseRequst(HttpServletRequest request){
        String body = "";
        try {
            ServletInputStream inputStream = request.getInputStream(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while(true){
                String info = br.readLine();
                if(info == null){
                    break;
                }
                if(body == null || "".equals(body)){
                    body = info;
                }else{
                    body += info;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }            
        return body;
    }
    /**
     * 转MAP
     * @author  
     * @param element
     * @return
     */
    public static Map<String, String> toMap(Element element){
        Map<String, String> rest = new HashMap<String, String>();
        List<Element> els = element.elements();
        for(Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
    public static String toXml(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        buf.append("<xml>");
        for(String key : keys){
            buf.append("<").append(key).append(">");
            buf.append("<![CDATA[").append(params.get(key)).append("]]>");
            buf.append("</").append(key).append(">\n");
        }
        buf.append("</xml>");
        return buf.toString();
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String abc="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxf8e130a1cc83c3b6]]></appid><mch_id><![CDATA[1266073301]]></mch_id><nonce_str><![CDATA[iBKGqqbwTipIPqdu]]></nonce_str><sign><![CDATA[608C16D2C5EB66F2F812E7ED35677F8F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2016061721041243d7cf612a0022999032]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></xml>";
    	abc = abc +"\n";
    	ParseXML(abc);
	}

}
