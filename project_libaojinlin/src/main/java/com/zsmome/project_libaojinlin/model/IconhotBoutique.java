package com.zsmome.project_libaojinlin.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class IconhotBoutique {

    /**
     * flag : true
     * info : {"push1":[{"id":37,"appid":"1474956019","type":1,"clicks":16327,"flag":1,"platform":0,"name":"口袋妖怪大师版","typename":"休闲益智","logo":"/allimgs/img_iapp/201609/_1474955931115.png","size":"1M","addtime":"2017-03-07 16:59:43.0"},{"id":8,"appid":"1479805962","type":1,"clicks":14764,"flag":1,"platform":0,"name":"大富豪3","typename":"模拟经营","logo":"/allimgs/img_iapp/201701/_1484813476053.jpg","size":"1M","addtime":"2017-03-07 16:59:01.0"},{"id":10,"appid":"1481269394","type":1,"clicks":15523,"flag":1,"platform":0,"name":"传奇世界H5","typename":"角色扮演","logo":"/allimgs/img_iapp/201701/_1484631471177.jpg","size":"5M","addtime":"2017-01-17 13:28:54.0"}],"push2":[{"id":9,"appid":"1443491252","type":0,"clicks":4533,"flag":1,"platform":0,"name":"王者荣耀","typename":"动作格斗","logo":"/allimgs/img_iapp/201509/_1443491274999.png","size":"360M","addtime":"2017-02-22 13:05:57.0"},{"id":11,"appid":"1421465673","type":0,"clicks":4522,"flag":1,"platform":0,"name":"梦幻西游手游","typename":"角色扮演","logo":"/allimgs/img_iapp/201504/_1429091380602.jpg","size":"280M","addtime":"2017-01-20 16:45:49.0"},{"id":27,"appid":"1421918699","type":0,"clicks":100,"flag":1,"platform":0,"name":"熹妃传","typename":"角色扮演","logo":"/allimgs/img_iapp/201612/_1482820854974.png","size":"158 MB","addtime":"2017-01-20 16:45:06.0"},{"id":23,"appid":"1429680870","type":0,"clicks":100,"flag":1,"platform":0,"name":"横扫千军","typename":"角色扮演","logo":"/allimgs/img_iapp/201601/_1451959017523.png","size":"232M","addtime":"2017-01-07 10:34:04.0"},{"id":28,"appid":"1447062077","type":0,"clicks":2468,"flag":1,"platform":0,"name":"剑侠情缘移动版","typename":"角色扮演","logo":"/allimgs/img_iapp/201512/_1450431247635.png","addtime":"2017-01-04 19:47:57.0"},{"id":12,"appid":"1421467036","type":0,"clicks":2532,"flag":1,"platform":0,"name":"热血传奇手机版","typename":"角色扮演","logo":"/allimgs/img_iapp/201603/_1459327042485.png","size":" 516 MB","addtime":"2016-01-09 18:08:27.0"}]}
     */

    private boolean flag;
    private InfoBean info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private List<Push1Bean> push1;
        private List<Push2Bean> push2;

        public List<Push1Bean> getPush1() {
            return push1;
        }

        public void setPush1(List<Push1Bean> push1) {
            this.push1 = push1;
        }

        public List<Push2Bean> getPush2() {
            return push2;
        }

        public void setPush2(List<Push2Bean> push2) {
            this.push2 = push2;
        }

        public static class Push1Bean {
            /**
             * id : 37
             * appid : 1474956019
             * type : 1
             * clicks : 16327
             * flag : 1
             * platform : 0
             * name : 口袋妖怪大师版
             * typename : 休闲益智
             * logo : /allimgs/img_iapp/201609/_1474955931115.png
             * size : 1M
             * addtime : 2017-03-07 16:59:43.0
             */

            private int id;
            private String appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String size;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }

        public static class Push2Bean {
            /**
             * id : 9
             * appid : 1443491252
             * type : 0
             * clicks : 4533
             * flag : 1
             * platform : 0
             * name : 王者荣耀
             * typename : 动作格斗
             * logo : /allimgs/img_iapp/201509/_1443491274999.png
             * size : 360M
             * addtime : 2017-02-22 13:05:57.0
             */

            private int id;
            private String appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String size;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
