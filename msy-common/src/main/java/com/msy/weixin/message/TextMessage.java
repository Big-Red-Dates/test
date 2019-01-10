package com.msy.weixin.message;

public class TextMessage extends BeseMessage{

	// 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
