package com.zsmome.screen2.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class YouDao {

    /**
     * translation : ["一个"]
     * basic : {"us-phonetic":"wʌn","phonetic":"wʌn","uk-phonetic":"wʌn","explains":["num. 一；一个","n. 一","adj. 一的；唯一的","pron. 一个人；任何人","n. (One)人名；(老)温"]}
     * query : one
     * errorCode : 0
     * web : [{"value":["ONE (BONNIE PINK专辑)","One (岚)","一人"],"key":"One"},{"value":["有一天","一天","真爱挑日子"],"key":"One Day"},{"value":["HTC One","HTC One (手机)","HTC One"],"key":"HTC One"}]
     */

    private BasicBean basic;
    private String query;
    private int errorCode;
    private List<String> translation;
    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        /**
         * us-phonetic : wʌn
         * phonetic : wʌn
         * uk-phonetic : wʌn
         * explains : ["num. 一；一个","n. 一","adj. 一的；唯一的","pron. 一个人；任何人","n. (One)人名；(老)温"]
         */

        @SerializedName("us-phonetic")
        private String usphonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {
        /**
         * value : ["ONE (BONNIE PINK专辑)","One (岚)","一人"]
         * key : One
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
