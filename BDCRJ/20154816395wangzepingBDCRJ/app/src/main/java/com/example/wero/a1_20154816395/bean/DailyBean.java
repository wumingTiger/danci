package com.example.wero.a1_20154816395.bean;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-20
 */

public class DailyBean {
    String sid;
    String tts;
    String content;
    String note;
    String love;
    String translation;
    String picture;
    String picture2;
    String caption;
    String dateline;
    String s_pv;

    public Object[] getTags() {
        return tags;
    }

    public void setTags(Object[] tags) {
        this.tags = tags;
    }

    public void setFenxiang_img(Object fenxiang_img) {
        this.fenxiang_img = fenxiang_img;
    }

    Object tags[];
    Object fenxiang_img;
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getS_pv() {
        return s_pv;
    }

    public void setS_pv(String s_pv) {
        this.s_pv = s_pv;
    }

}
