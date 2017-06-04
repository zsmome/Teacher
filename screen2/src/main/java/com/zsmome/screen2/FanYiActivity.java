package com.zsmome.screen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.screen2.R;
import com.zsmome.screen2.models.YouDao;
import com.zsmome.screen2.utils.YouDaoParse;

import java.util.List;

public class FanYiActivity extends AppCompatActivity {
    //声明控件
    private EditText mInputEt;
    private TextView mTranTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //显示在锁屏的上层界面
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_yi);
        bindViews();
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mInputEt = (EditText) findViewById(R.id.fanyi_input_et);
        mTranTv = (TextView) findViewById(R.id.fanyi_tran_tv);
    }

    public void fanyiButton(View view) {
        //为空，退出
        if(TextUtils.isEmpty(mInputEt.getText().toString())) {
            return ;
        }
        new YouDaoParse().parseJSON(mInputEt.getText().toString(), new YouDaoParse.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                YouDao youDao = new Gson().fromJson(json, YouDao.class);
                //解析接口，是否正常
                if(json.isEmpty() || youDao.getErrorCode() != 0) {
                    Toast.makeText(FanYiActivity.this, "啧啧,请检查网络！", Toast.LENGTH_SHORT).show();
                    Log.i("lockactivity", "啧啧,请检查网络！");
                    return;
                } else if(youDao.getErrorCode() == 0) {
                    StringBuilder sb = new StringBuilder();
                    //翻译内容
                    if(youDao.getTranslation() != null) {
                        sb.append("译：");
                        for(String s : youDao.getTranslation())
                        sb.append(s).append("\t");
                        sb.append("\n\n");
                        //
                    }
                    //basic
                    if(youDao.getBasic() != null) {
                        YouDao.BasicBean basicBean = youDao.getBasic();
                        //音标
                        sb.append("音:").append("\t");
                        //英式
                        if(basicBean.getUkphonetic() != null) {
                            sb.append("英:"+basicBean.getUkphonetic()).append("\t");
                        }
                        //美式
                        if(basicBean.getUkphonetic() != null) {
                            sb.append("美:"+basicBean.getUsphonetic()).append("\t");
                        }
                        //其他
                        //如果有了音标
                        boolean hasPhoneti = (basicBean.getUkphonetic() != null || basicBean != null) ?  true : false;
                        if(basicBean.getPhonetic() != null && !hasPhoneti) {
                            sb.append(basicBean.getPhonetic()).append("\t");
                        }
                        sb.append("\n\n");
                        //解释
                        if(basicBean.getExplains() != null) {
                            for(String s : basicBean.getExplains())
                            sb.append(s).append("\n\n");
                        }
                        sb.append("\n");
                    }
                    //web
                    if(youDao.getWeb() != null) {
                         List<YouDao.WebBean> webBeanList = youDao.getWeb();
                        sb.append("网络:").append("\n");
                        for(YouDao.WebBean webBean : webBeanList) {
                            sb.append(webBean.getKey()).append("\t");
                            for(String s : webBean.getValue()) {
                                sb.append(s).append("\t");
                            }
                            sb.append("\n");
                        }
                    }
                    mTranTv.setText(sb.toString());
                }
            }
        });
    }
}
