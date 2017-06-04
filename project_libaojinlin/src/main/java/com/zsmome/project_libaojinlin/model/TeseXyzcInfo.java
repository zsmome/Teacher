package com.zsmome.project_libaojinlin.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class TeseXyzcInfo {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1127
         * fid : 20170421
         * appid : 1476692250
         * appname : 秘宝猎人
         * typename : 角色扮演
         * appsize : 482 MB
         * adimg : /allimgs/img_iapp/201704/_1492766636129.jpg
         * appkfs : 网易
         * iconurl : /allimgs/img_iapp/201610/_1476692145261.png
         * addtime : 2017-04-21
         * descs : 《秘宝猎人》是网易自研的逗趣射击闯关手游，游戏以简洁明快的流行漫画风为载体，呈现遗迹文明与科技幻想共存的近未来世界观，融合射击、动作、RPG的创新性玩法，在世界各个神秘的遗迹中展开精彩刺激又诙谐逗趣的寻宝冒险之旅！遗迹潜入、列车追逐、智躲机关、弹雨枪战，火爆炫酷的射击特效，灵活精准的闪身走位，武器道具的切换组合，不同猎人的组队配合，带给你爽快刺激又极具策略的冒险战斗体验。
         * critique : 《秘宝猎人》是网易首款RPG冒险手游，游戏以简洁明快的逗趣日漫风为载体，以全球文明构建史诗级世界观，融合射击、动作、解谜、RPG的创新性玩法，在世界各个神秘的遗迹中展开精彩刺激的寻宝冒险之旅。潜入盗宝、飙车追逐、智躲机关、弹雨枪战，火爆炫酷的射击特效，灵活精准的闪身走位，武器道具的切换组合，带给你爽快刺激的游戏体验!
         * iszq : 0
         * typeid : 0
         * istop : 1
         */

        private int id;
        private int fid;
        private String appid;
        private String appname;
        private String typename;
        private String appsize;
        private String adimg;
        private String appkfs;
        private String iconurl;
        private String addtime;
        private String descs;
        private String critique;
        private int iszq;
        private int typeid;
        private int istop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
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

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppsize() {
            return appsize;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }

        public String getAppkfs() {
            return appkfs;
        }

        public void setAppkfs(String appkfs) {
            this.appkfs = appkfs;
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

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getCritique() {
            return critique;
        }

        public void setCritique(String critique) {
            this.critique = critique;
        }

        public int getIszq() {
            return iszq;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }
    }
}
