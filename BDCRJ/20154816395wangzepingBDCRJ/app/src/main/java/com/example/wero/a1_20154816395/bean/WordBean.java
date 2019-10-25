package com.example.wero.a1_20154816395.bean;

import java.io.Serializable;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public class WordBean implements Serializable{
    private Long id = 0l;
    private String en;
    private String ch;
    private boolean star;

    public boolean getIsStar() {
        return star;
    }

    public void setIsStar(boolean isStar) {
        this.star = isStar;
    }

    public WordBean(){}
    public WordBean(String en, String ch, boolean star) {
        this.en = en;
        this.ch = ch;
        this.star = star;
    }

    public WordBean(String en, String ch) {
        this.en = en;
        this.ch = ch;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}
