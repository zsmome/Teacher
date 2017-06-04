package com.zsmome.project_libaojinlin.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */

public class SearchWeekly {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1493363230
         * iconurl : /allimgs/img_iapp/201609/_1474190951389.png
         * giftname : 劳动节礼包
         * number : 0
         * exchanges : 42
         * type : 1
         * gname : 放开那三国2
         * integral : 0
         * isintegral : 0
         * addtime : 2017-04-28
         * ptype : 1,2,
         * operators : 巴别时代
         * flag : 1
         */

        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private int exchanges;
        private int type;
        private String gname;
        private int integral;
        private int isintegral;
        private String addtime;
        private String ptype;
        private String operators;
        private int flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getExchanges() {
            return exchanges;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
