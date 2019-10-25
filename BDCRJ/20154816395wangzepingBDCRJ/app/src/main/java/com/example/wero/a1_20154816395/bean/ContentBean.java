package com.example.wero.a1_20154816395.bean;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-23
 * gson有个巨坑,不想自己重写,偷个懒
 */
    public class ContentBean {

    /**
     * JSon转换出错,不想调试了,用的这个类
     * code : 1000
     * message : 成功
     * content : [{"userid":1,"topic":"l99999l0","count":6,"words":[{"id":2,"ch":"hi","en":"hi","star":false},{"id":3,"ch":"hii","en":"hi","star":false},{"id":4,"ch":"hiii","en":"hi","star":false},{"id":5,"ch":"iihi","en":"hi","star":false},{"id":6,"ch":"hiiiii","en":"hi","star":false},{"id":7,"ch":"hiiiiiiiiiii","en":"zzi","star":false}]},{"userid":1,"topic":"qqqq","count":4,"words":[{"id":9,"ch":"ww","en":"y","star":false},{"id":10,"ch":"t","en":"r","star":false},{"id":11,"ch":"w","en":"e","star":false},{"id":12,"ch":"z","en":"z","star":false}]}]
     */

    private int code;
    private String message;
    private List<WordPackBean> content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WordPackBean> getContent() {
        return content;
    }

    public void setContent(List<WordPackBean> content) {
        this.content = content;
    }

}