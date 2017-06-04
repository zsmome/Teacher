package com.zsmome.day09_homework_weekly_baseadapter.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Weekly {

    /**
     * ad : [{"id":296,"title":"神之剑独家礼包","flag":1,"iconurl":"/allimgs/img_iad/_1490609550641.jpg","addtime":"2017-03-18","giftid":"1490608692","appName":"神之剑","appLogo":"/allimgs/img_iapp/201703/_1490608046773.jpg","appId":1490608105},{"id":298,"title":"火线精英周年庆媒体独家礼包","flag":1,"iconurl":"/allimgs/img_iad/_1490782907188.jpg","addtime":"2017-03-18","giftid":"1490779777","appName":"火线精英","appLogo":"/allimgs/img_iapp/201508/_1439173429159.jpg","appId":1439173476},{"id":291,"title":"兵界之王礼包","flag":1,"iconurl":"/allimgs/img_iad/_1488879238209.jpg","addtime":"2017-03-17","giftid":"1488870236","appName":"兵界之王","appLogo":"/allimgs/img_iapp/201701/_1484291244928.jpg","appId":1477993775},{"id":294,"title":"恋舞OL青绿粉狸礼包","flag":1,"iconurl":"/allimgs/img_iad/_1490170625224.jpg","addtime":"2017-03-16","giftid":"1490169476","appName":"恋舞OL","appLogo":"/userfiles/applogo/_1422412216871.jpg","appId":1411637357},{"id":299,"title":"光明大陆备战礼包","flag":1,"iconurl":"/allimgs/img_iad/_1490948730002.jpg","addtime":"2017-03-16","giftid":"1490947227","appName":"光明大陆","appLogo":"/allimgs/img_iapp/201703/_1490928880061.png","appId":1468658115}]
     * pageno : 114
     * list : [{"id":"1492047700","iconurl":"/allimgs/img_iapp/201612/_1481694889227.png","giftname":"公测礼包","number":6,"exchanges":4,"type":1,"gname":"热血江湖手游","integral":0,"isintegral":0,"addtime":"2017-04-13","ptype":"1,2,","operators":"","flag":1},{"id":"1492047954","iconurl":"/allimgs/img_iapp/201611/_1477984307622.png","giftname":"礼包","number":22,"exchanges":0,"type":1,"gname":"幻姬骑士团","integral":0,"isintegral":0,"addtime":"2017-04-13","ptype":"1,2,","operators":"","flag":1},{"id":"1492063859","iconurl":"/allimgs/img_iapp/201701/_1484037221764.jpg","giftname":"踏青季冒险礼包","number":15,"exchanges":0,"type":1,"gname":"鬼剑豪","integral":0,"isintegral":0,"addtime":"2017-04-13","ptype":"1,","operators":"","flag":1},{"id":"1492074132","iconurl":"/allimgs/img_iapp/201609/_1472720425858.png","giftname":"新手礼包","number":17,"exchanges":0,"type":1,"gname":"达尔文进化岛","integral":0,"isintegral":0,"addtime":"2017-04-13","ptype":"1,2,","operators":"","flag":1},{"id":"1492077548","iconurl":"/allimgs/img_iapp/201704/_1492070099792.png","giftname":"IOS迎新礼包","number":9,"exchanges":0,"type":1,"gname":"我的使命","integral":0,"isintegral":0,"addtime":"2017-04-13","ptype":"2,","operators":"","flag":1},{"id":"1491978111","iconurl":"/allimgs/img_iapp/201703/_1488965621578.png","giftname":"礼包","number":7,"exchanges":2,"type":1,"gname":"风之旅团 ","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,","operators":"","flag":1},{"id":"1491978497","iconurl":"/allimgs/img_iapp/201703/_1488355571281.png","giftname":"踏春礼包","number":14,"exchanges":1,"type":1,"gname":"扫荡三国","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491978789","iconurl":"/allimgs/img_iapp/201611/_1479106481476.png","giftname":"欢乐礼包","number":21,"exchanges":1,"type":1,"gname":"魔法门挂机","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491979104","iconurl":"/allimgs/img_iapp/201704/_1491978423178.png","giftname":"礼包","number":34,"exchanges":1,"type":1,"gname":"速度与激情8","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491990426","iconurl":"/allimgs/img_iapp/201508/_1439189181081.png","giftname":"礼包","number":14,"exchanges":5,"type":1,"gname":"霹雳江湖","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491992895","iconurl":"/allimgs/img_iapp/201606/_1466128524233.png","giftname":"暖心礼包","number":19,"exchanges":2,"type":1,"gname":"枪战英雄","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491993122","iconurl":"/allimgs/img_iapp/201606/_1466128524233.png","giftname":"独家礼包","number":20,"exchanges":0,"type":1,"gname":"枪战英雄","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491993282","iconurl":"/allimgs/img_iapp/201606/_1466128524233.png","giftname":"媒体礼包","number":19,"exchanges":0,"type":1,"gname":"枪战英雄","integral":0,"isintegral":0,"addtime":"2017-04-12","ptype":"1,2,","operators":"","flag":1},{"id":"1491889591","iconurl":"/allimgs/img_iapp/201702/_1488176325411.png","giftname":"高级礼包","number":2,"exchanges":7,"type":1,"gname":"龙之谷手游","integral":0,"isintegral":0,"addtime":"2017-04-11","ptype":"1,2,","operators":"","flag":1},{"id":"1491889763","iconurl":"/allimgs/img_iapp/201601/_1451959017523.png","giftname":"礼包","number":3,"exchanges":7,"type":1,"gname":"横扫千军","integral":0,"isintegral":0,"addtime":"2017-04-11","ptype":"1,2,","operators":"","flag":1}]
     */

    private int pageno;
    private List<AdBean> ad;
    private List<ListBean> list;

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class AdBean {
        /**
         * id : 296
         * title : 神之剑独家礼包
         * flag : 1
         * iconurl : /allimgs/img_iad/_1490609550641.jpg
         * addtime : 2017-03-18
         * giftid : 1490608692
         * appName : 神之剑
         * appLogo : /allimgs/img_iapp/201703/_1490608046773.jpg
         * appId : 1490608105
         */

        private int id;
        private String title;
        private int flag;
        private String iconurl;
        private String addtime;
        private String giftid;
        private String appName;
        private String appLogo;
        private int appId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGiftid() {
            return giftid;
        }

        public void setGiftid(String giftid) {
            this.giftid = giftid;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }
    }

    public static class ListBean {
        /**
         * id : 1492047700
         * iconurl : /allimgs/img_iapp/201612/_1481694889227.png
         * giftname : 公测礼包
         * number : 6
         * exchanges : 4
         * type : 1
         * gname : 热血江湖手游
         * integral : 0
         * isintegral : 0
         * addtime : 2017-04-13
         * ptype : 1,2,
         * operators :
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
