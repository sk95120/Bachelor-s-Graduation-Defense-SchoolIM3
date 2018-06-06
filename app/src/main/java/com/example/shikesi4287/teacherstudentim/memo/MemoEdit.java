package com.example.shikesi4287.teacherstudentim.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shikesi4287.teacherstudentim.R;

import java.util.Date;

public class MemoEdit extends AppCompatActivity {

    TextView txv;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edit);
        //获得传入对象
        Intent it = getIntent();
        String s = it.getStringExtra("备忘");
        txv = (TextView)findViewById(R.id.textView);
        edt = (EditText)findViewById(R.id.editText);
        txv.setText(s.substring(0,2));
        if(s.length()>3){
            edt.setText(s.substring(3));//备忘率传来的除去前3字符，填入EditView
        }
    }
    public void onCancel(View V){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void onSave(View V){
        Intent it2 = new Intent();
        it2.putExtra("备忘",txv.getText() +""+ edt.getText());//txv.getText() + edt.getText()会出错
        it2.putExtra("日期",new Date().toString());
        setResult(RESULT_OK,it2);
        finish();
    }

}
