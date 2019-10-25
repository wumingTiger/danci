package com.example.wero.a1_20154816395.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于请求的类一定要添加默认构造函数
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-10
 */

public class WordPackBean implements Serializable{
    private Long userid;
    private String topic;
    private Long count;
    private List<WordBean> words;

    public WordPackBean(String topic, long count) {
        this.topic = topic;
        this.count = count;
    }


    public WordPackBean(String topic) {
        this.topic = topic;
    }
    public WordPackBean(){}

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getCount() {
        return count;
    }

    public List<WordBean> getWords() {
        return words;
    }

    public void setWords(List<WordBean> words) {
        this.words = words;
    }
}
