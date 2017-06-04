package com.zsmome.project_libaojinlin.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class TeseBdxqsInfo {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1502
         * cid : 145
         * appid : 1436341545
         * appname : 饥荒：口袋版
         * appicon : /allimgs/img_iapp/201507/_1436341376822.png
         * typename : 冒险解密
         * appdesc :
         * appvtype : 1,2
         */

        private int id;
        private int cid;
        private String appid;
        private String appname;
        private String appicon;
        private String typename;
        private String appdesc;
        private String appvtype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppicon() {
            return appicon;
        }

        public void setAppicon(String appicon) {
            this.appicon = appicon;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppdesc() {
            return appdesc;
        }

        public void setAppdesc(String appdesc) {
            this.appdesc = appdesc;
        }

        public String getAppvtype() {
            return appvtype;
        }

        public void setAppvtype(String appvtype) {
            this.appvtype = appvtype;
        }
    }
}
