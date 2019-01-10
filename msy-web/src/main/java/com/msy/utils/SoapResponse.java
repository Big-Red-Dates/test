/**   
* @Package com.starit 
* @Description: TODO 
* @author chesir 
* @date 2016-2-1 上午11:25:40 
* @version V1.0   
*/
package com.msy.utils;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author yanwu
 *
 */
public class SoapResponse extends DefaultHandler {
	@SuppressWarnings("rawtypes")
	public Map resmap = null;

	private StringBuffer buf;

	// 开始解析文档，即开始解析XML根元素时调用该方法
	@SuppressWarnings("rawtypes")
	@Override
	public void startDocument() throws SAXException {
		resmap = new HashMap();
		buf = new StringBuffer();
	}


	@Override
	public void characters( char[] chars, int start, int length )throws SAXException{ 
		if (null != buf){
			buf.append(chars,start,length);
		}
     }

	// 每个元素结束的时候都会调用该方法
	@SuppressWarnings("unchecked")
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		String value = buf.toString().trim();
		if (null!= resmap && null!=buf){
			resmap.put(qName, value); 
			buf.delete(0,buf.length());
		}
	}
	
	 

	// 结束解析文档，即解析根元素结束标签时调用该方法
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
