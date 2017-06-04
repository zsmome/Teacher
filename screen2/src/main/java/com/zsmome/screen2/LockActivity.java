package com.zsmome.screen2;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zsmome.screen2.adapters.CommonAdapter;
import com.zsmome.screen2.adapters.ViewHolder;
import com.zsmome.screen2.models.UserTable;
import com.zsmome.screen2.models.WordsTable;
import com.zsmome.screen2.models.YouDao;
import com.zsmome.screen2.sqlite.UserDAOImpl;
import com.zsmome.screen2.sqlite.WordsDAOImpl;
import com.zsmome.screen2.utils.InternalStorage;
import com.zsmome.screen2.utils.NotifactionUtil;
import com.zsmome.screen2.utils.YouDaoParse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GUOQI on 16/10/23.
 */

public class LockActivity extends AppCompatActivity {
    /*====声明控件====*/
    private GridView mWordGv;
    private TextView mHintTv;
    private TextView mInputTv;
    //需要自己定义标志
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    //本地单词
    private WordsTable mWord;
    //提示的个数,有改变的地方必须重新赋值
    private int mHintWordCount = 0;
    //提示剩余的次数
    private static int mHintRemainCount = 10;
    //存储本地文件
    InternalStorage mIStorage = new InternalStorage(this);
    //用户名
    private String mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //显示在锁屏的上层界面
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_lock);
        super.onCreate(savedInstanceState);
        initViews();
    }

    /**
     * 找到组件
     */
    private void initViews() {
        mHintTv = (TextView) findViewById(R.id.hint_tv);
        mInputTv = (TextView) findViewById(R.id.input_tv);
        mWordGv = (GridView) findViewById(R.id.word_button_gv);
        mHintRemainCount = getHintRemainCount();
        initHintTv();
    }

    /**
     * 提示文字
     */
    private void initHintTv() {
        //获取单词
        mWord = new WordsDAOImpl(this).getWords();
        mHintTv.setText(mWord.getTrans());
        initGridView();
    }

    /**
     * 初始化字母按钮表
     *
     *
     *
     * 没网，使用本地文件
     * 有网，使用网络文件
     */
    private void initGridView() {
        List<String> mData = new ArrayList<>();
        for(int i = 0; i< mWord.getWord().length(); i++) {
            String word = mWord.getWord().charAt(i) + "";
            if(mData.contains(word)) {
                continue;
            }
            mData.add(word);
        }
        Collections.sort(mData);
        CommonAdapter<String> commAda = new CommonAdapter<String>(this, mData, R.layout.button_word) {
            @Override
            public void setViews(ViewHolder viewHolder, final String data) {
                /*设置文字*/
                viewHolder.setButtonText(R.id.bt, data);
                /*设置监听*/
                viewHolder.getView(R.id.bt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = mInputTv.getText().toString();
                        mInputTv.setText(str+data);
                        mHintWordCount = mInputTv.length();
                    }
                });
            }
        };
        mWordGv.setAdapter(commAda);
    }

    /**
     * 结束Activity
     */
    @Override
    protected void onDestroy() {
        //杀死本页进程,启动服务
        super.onDestroy();
    }
    //使back键，音量加减键失效
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return disableKeycode(keyCode, event);
    }

    private boolean disableKeycode(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onUserLeaveHint() {
        exitAction();
        super.onUserLeaveHint();
    }
    private void exitAction() {
        try {
//            Intent intent = new Intent(this, LockActivity.class);
//            startActivity(intent);
            //推送一条消息===========================================
            if(!TextUtils.isEmpty(mName)) {
                new UserDAOImpl(this).updateLoseWordsCount(mName);
                int count  = new UserDAOImpl(this).getUser(mName).getLoseWordsCount();
                Log.i("LockActivity", "exitAction: "+mName+count);
                new NotifactionUtil(this).sendNotifactoin(mName, "无意中错过了"+count+"个单词了！", "", "英语锁屏");
            }
//            android.os.Process.killProcess(android.os.Process.myPid());
            this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除按钮
     * @param view
     */
    public void delButton(View view) {
        String str = mInputTv.getText().toString();
        if(str.length() == 0) {
            return;
        }
        mInputTv.setText(str.substring(0, str.length()-1));
        mHintWordCount = str.length();
    }

    /**
     * 确认按钮
     * @param view
     */
    public void okButton(View view) {
        String input = mInputTv.getText().toString();
        if(mWord.getWord().equals(input)) {
            Toast.makeText(this, "解锁成功，奖赏5个提示币！", Toast.LENGTH_SHORT).show();
            //成功一次奖励5个提醒
            mHintRemainCount += 5;
            new UserDAOImpl(this).updateHintRemainCount(mName, mHintRemainCount);
            finish();
        } else {
            mInputTv.setText("");
            mHintWordCount = 0;
            Toast.makeText(this, "再接再励哟~", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 点击一次提示一次
     * @param view
     */
    public void hintButton(View view) {
        //如果提示的个数超过单词本身
        if(mHintWordCount == mWord.getWord().length()) {
            Toast.makeText(this, "已全部提示完，请点击解锁！", Toast.LENGTH_SHORT).show();
            return;
        }
        //提示次数为0
        if(mHintWordCount == 0) {
            Toast.makeText(this, "提醒次数为0!", Toast.LENGTH_SHORT).show();
            return;
        }
        mHintRemainCount--;
        new UserDAOImpl(this).updateHintRemainCount(mName, mHintRemainCount);
        String input = mInputTv.getText().toString();
        //两个字符串，一致的索引号
        int count = 0;
        for(int i=input.length()-1; i>0; i--) {
            if(mWord.getWord().substring(0, i).equals(input.substring(0,i))) {
                count = i;
                break;
            }
        }
        //当前输入的字符串，和要匹配的一致，就提示一个
        if(count == input.length()-1 || count == 0) {
            mHintWordCount++;
            mInputTv.setText(input + mWord.getWord().charAt(mHintWordCount - 1));
        } else {
            //提示当前没有错的字符串
            if(count == 0) {
                mInputTv.setText("");
            }
            mInputTv.setText(input.substring(0, count));
        }
    }

    /**
     * 翻译
     * @param view
     */
    public void fanyiButton(View view) {
        new YouDaoParse().parseJSON("one", new YouDaoParse.OnJSONParseListener() {
            @Override
            public void OnJSONParseComplete(String json) {
                YouDao youDao = new Gson().fromJson(json, YouDao.class);
                //解析接口，是否正常
                if(json.isEmpty() || youDao.getErrorCode() != 0) {
                    Toast.makeText(LockActivity.this, "啧啧,请检查网络！", Toast.LENGTH_SHORT).show();
                    Log.i("lockactivity", "啧啧,请检查网络！");
                    return;
                } else {
                    Intent intent = new Intent(LockActivity.this, FanYiActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 获取提示数量
     * @return
     */
    public int getHintRemainCount() {
        mName = mIStorage.getData("user_info", "username");
        //已经登录
        if(!TextUtils.isEmpty(mName)) {
            int count = new UserDAOImpl(this).getUser(mName).getHintRemainCount();
            return count;
        }else {
            return 0;
        }
    }
}
