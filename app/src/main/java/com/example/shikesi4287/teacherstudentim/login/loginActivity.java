package com.example.shikesi4287.teacherstudentim.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.R;
import com.example.shikesi4287.teacherstudentim.SQLite.DataBaseInfo;
import com.example.shikesi4287.teacherstudentim.SQLite.InfoBean;
import com.example.shikesi4287.teacherstudentim.fragment.FragmentActivityMain;

public class loginActivity extends AppCompatActivity {
    private static final String TAG = "DB";
    EditText stuName;
    EditText stuId;
    Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        //设置姓名，学号的初始化
        stuName = findViewById (R.id.stuNameText);
        stuId = findViewById (R.id.stuIdText);
        button = findViewById (R.id.login_button);
    }
    public void login(View v){
        String name = stuName.getText ().toString ().trim ();
        String id = stuId.getText ().toString ().trim ();

        if(name.equals ("") && !id.equals (""))
        {
            Toast.makeText (this,
                    "请填写姓名。",
                    Toast.LENGTH_LONG)
                    .show ();
        }else if(!name.equals ("") && id.equals (""))
        {
            Toast.makeText (this,
                    "请填写学号。",
                    Toast.LENGTH_LONG)
                    .show ();
        }else if(name.equals ("") && id.equals (""))
        {
            Toast.makeText (this,
                    "请填写个人信息。",
                    Toast.LENGTH_LONG)
                    .show ();
        }else{
            //数据库存数据
            DataBaseInfo infoData = new DataBaseInfo(getBaseContext());
            InfoBean infoBean = new InfoBean();
            infoBean.name = name.toString();
            infoBean.stuid = stuId.toString();
            boolean result = infoData.add(infoBean);
            if(result){
                Log.d(TAG, "数据库生成成功。");
            }else{
                Log.e(TAG, "数据库生成失败。");
            }
            Intent it = new Intent(this, FragmentActivityMain.class);
            it.putExtra ("姓名",name);
            it.putExtra ("学号",id);
            startActivityForResult (it,202);
        }

    }



}
