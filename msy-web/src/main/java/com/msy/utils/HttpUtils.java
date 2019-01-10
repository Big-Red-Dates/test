package com.msy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpStatus;

/**
 */
public class HttpUtils {

    private HttpUtils() {
    }

    /**
     * input to String
     * @param is
     * @param charset
     * @return
     * @throws Exception
     */
    public synchronized static String getStringFromInputStream(InputStream is, String charset) throws Exception {
        if (is == null) {
            return null;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        if (br != null) {
            br.close();
        }

        String str = sb.toString();
        return str;
    }

    public static String postXml(String url, String xml) throws IllegalStateException, IOException, Exception {

        TenpayHttpClient httpClient = new TenpayHttpClient();

        httpClient.callHttpPost(url, xml);

        if (httpClient.getResponseCode() == HttpStatus.SC_OK) {
            return httpClient.getResContent();
        }
        return null;
    }

    public static String get(String url) throws Exception {

        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setReqContent(url);
        httpClient.call();

        if (httpClient.getResponseCode() == HttpStatus.SC_OK) {
            return httpClient.getResContent();
        }
        return null;
    }

    public static String getHost(String url) {
        if (url == null || url.trim().equals("")) {
            return "";
        }
        String host = "";
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+(\\:[0-9]*){0,1}");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            host = matcher.group();
        }
        return host;
    }
}
