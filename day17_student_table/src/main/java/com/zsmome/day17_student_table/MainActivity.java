package com.zsmome.day17_student_table;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zsmome.day17_student_table.adapters.CommonAdapter;
import com.zsmome.day17_student_table.adapters.ViewHolder;
import com.zsmome.day17_student_table.model.Student;
import com.zsmome.day17_student_table.sqlite.MySqliteOpenHelper;
import com.zsmome.day17_student_table.sqlite.StudentDAOImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*声明控件*/
    private EditText mNameEt;
    private EditText mAgeEt;
    private RadioGroup mSexRg;
    /*地址*/
    private EditText mAddEt;
    /*列表*/
    private ListView mStudentLv;
    /*数据库帮助*/
    private MySqliteOpenHelper mHelper;
    /*学生信息操作*/
    private StudentDAOImpl mStudentOP = new StudentDAOImpl(this);
    /*学生集合*/
    private List<Student> mStudentList;
    /*学生适配器*/
    private CommonAdapter<Student> mStudentAda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*初始数据库*/
        mHelper = MySqliteOpenHelper.getInstance(this);
        mHelper.getWritableDatabase();

        bindViews();
        initListView();
    }

    /**
     * 初始化学生列表
     */
    private void initListView() {
        mStudentList = mStudentOP.getStudentList();
        mStudentAda = new CommonAdapter<Student>(this, mStudentList, R.layout.list_view_student) {
            @Override
            public void setViews(ViewHolder viewHolder, Student data) {
                viewHolder.setTextViewText(R.id.name_tv, data.getName());
                viewHolder.setTextViewText(R.id.age_tv, data.getAge());
                viewHolder.setTextViewText(R.id.sex_tv, data.getSex());
                viewHolder.setTextViewText(R.id.add_tv, data.getAddress());
            }
        };
        mStudentLv.setAdapter(mStudentAda);
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        mNameEt = (EditText) findViewById(R.id.name_et);
        mAgeEt = (EditText) findViewById(R.id.age_et);
        mSexRg = (RadioGroup) findViewById(R.id.sex_rg);
        mAddEt = (EditText) findViewById(R.id.add_et);
        mStudentLv = (ListView) findViewById(R.id.student_lv);
    }

    /**
     * 添加学生信息
     * @param view
     */
    public void addStudentInfo(View view) {
        String name = mNameEt.getText().toString();
        String age = mAgeEt.getText().toString();
        /*获取性别*/
        RadioButton radio = (RadioButton)  (findViewById(mSexRg.getCheckedRadioButtonId()));
        String sex = radio.getText().toString();
        String address = mAddEt.getText().toString();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "不能为空!", Toast.LENGTH_SHORT).show();
            return ;
        }
        /*学生信息*/
        Student stu = new Student(name, age, sex, address);
        mStudentOP.insert(stu);
        mStudentList = mStudentOP.getStudentList();
        mStudentAda.refresh(mStudentList);
    }
}
