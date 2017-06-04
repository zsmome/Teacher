package com.zsmome.giftgagelves;

import android.content.res.Resources;

/**
 * Created by Administrator on 2017/4/21.
 */

public class InterfaceAddress {
    //资源
    private Resources res;
    public InterfaceAddress(Resources res) {
        this.res = res;
    }
    /**
     * 基础网址
     */
    public String getUrl_base() {
        return res.getString(R.string.url_base);
    }
    public String getUrl_liBaoLv() {
        return res.getString(R.string.url_libao);
    }
    public String getUrl_libao_info() {
        return res.getString(R.string.url_libao_info);
    }
    public String getUrl_kaifu_kaifu() {
        return res.getString(R.string.url_kaifu_kaifu);
    }
    public String getUrl_kaifu_kaice() {
        return res.getString(R.string.url_kaifu_kaice);
    }
    public String getUrl_kaifu_info() {
        return res.getString(R.string.url_kaifu_info);
    }
    public String getUrl_tese_bdxqs() {
        return res.getString(R.string.url_tese_bdxqs);
    }
    public String getUrl_tese_xyzk() {
        return res.getString(R.string.url_tese_xyzk);
    }
    public String getUrl_bdxqs_info() {
        return res.getString(R.string.url_bdxqs_info);
    }
    public String getUrl_xyzk_info() {
        return res.getString(R.string.url_xyzk_info);
    }
    public String getUrl_search() {
        return res.getString(R.string.url_search);
    }
    public String getUrl_iconhot() {
        return res.getString(R.string.url_iconhot);
    }
}
