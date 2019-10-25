package com.example.wero.a1_20154816395.bean;

import java.io.Serializable;
import java.util.List;

public class QueryWordBean implements Serializable{


    /**
     * word_name : hi
     * is_CRI : 1
     * exchange : {"word_pl":"","word_past":"","word_done":"","word_ing":"","word_third":"","word_er":"","word_est":""}
     * symbols : [{"ph_en":"haɪ","ph_am":"haɪ","ph_other":"","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/9e/13/9e13a7b46bb5fb45f0890985f84934a1.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/49/f6/49f68a5c8493ec2c0bf489821c21fc3b.mp3","ph_tts_mp3":"http://res-tts.iciba.com/4/9/f/49f68a5c8493ec2c0bf489821c21fc3b.mp3","parts":[{"part":"int.","means":["（用作问候语）嘿，喂"]},{"part":"abbr.","means":["夏威夷群岛的书面缩写","（=high intensity）高强度"]}]}]
     * items : [""]
     */

    private String word_name;
    private int is_CRI;
    private Object exchange;
    private List<SymbolsBean> symbols;
    private List<Object> items;

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public int getIs_CRI() {
        return is_CRI;
    }

    public void setIs_CRI(int is_CRI) {
        this.is_CRI = is_CRI;
    }

    public Object getExchange() {
        return exchange;
    }

    public void setExchange(Object exchange) {
        this.exchange = exchange;
    }

    public List<SymbolsBean> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolsBean> symbols) {
        this.symbols = symbols;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public static class ExchangeBean {
        /**
         * word_pl :
         * word_past :
         * word_done :
         * word_ing :
         * word_third :
         * word_er :
         * word_est :
         */

        private String word_pl;
        private String word_past;
        private String word_done;
        private String word_ing;
        private String word_third;
        private String word_er;
        private String word_est;

        public String getWord_pl() {
            return word_pl;
        }

        public void setWord_pl(String word_pl) {
            this.word_pl = word_pl;
        }

        public String getWord_past() {
            return word_past;
        }

        public void setWord_past(String word_past) {
            this.word_past = word_past;
        }

        public String getWord_done() {
            return word_done;
        }

        public void setWord_done(String word_done) {
            this.word_done = word_done;
        }

        public String getWord_ing() {
            return word_ing;
        }

        public void setWord_ing(String word_ing) {
            this.word_ing = word_ing;
        }

        public String getWord_third() {
            return word_third;
        }

        public void setWord_third(String word_third) {
            this.word_third = word_third;
        }

        public String getWord_er() {
            return word_er;
        }

        public void setWord_er(String word_er) {
            this.word_er = word_er;
        }

        public String getWord_est() {
            return word_est;
        }

        public void setWord_est(String word_est) {
            this.word_est = word_est;
        }
    }

    public static class SymbolsBean {
        /**
         * ph_en : haɪ
         * ph_am : haɪ
         * ph_other :
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/9e/13/9e13a7b46bb5fb45f0890985f84934a1.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/49/f6/49f68a5c8493ec2c0bf489821c21fc3b.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/4/9/f/49f68a5c8493ec2c0bf489821c21fc3b.mp3
         * parts : [{"part":"int.","means":["（用作问候语）嘿，喂"]},{"part":"abbr.","means":["夏威夷群岛的书面缩写","（=high intensity）高强度"]}]
         */

        private String ph_en;
        private String ph_am;
        private String ph_other;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<PartsBean> parts;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_other() {
            return ph_other;
        }

        public void setPh_other(String ph_other) {
            this.ph_other = ph_other;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<PartsBean> getParts() {
            return parts;
        }

        public void setParts(List<PartsBean> parts) {
            this.parts = parts;
        }

        public static class PartsBean {
            /**
             * part : int.
             * means : ["（用作问候语）嘿，喂"]
             */

            private String part;
            private List<String> means;

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public List<String> getMeans() {
                return means;
            }

            public void setMeans(List<String> means) {
                this.means = means;
            }
        }
    }
}
