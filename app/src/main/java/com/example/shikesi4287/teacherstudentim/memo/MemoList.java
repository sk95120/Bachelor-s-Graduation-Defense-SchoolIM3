package com.example.shikesi4287.teacherstudentim.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.R;

public class MemoList extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String[] aMemo = {"1.单击编辑备忘录","2.长按清楚备忘录","3.","4.","5.","6."};
    ListView lv;
    ArrayAdapter<String> aa;

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list);

        lv = (ListView)findViewById(R.id.listView);
        aa  = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                aMemo);
        //缺少关键一句
        lv.setAdapter(aa);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(this,MemoEdit.class);
         //将数据加到it传给先一个Activity
        it.putExtra("备忘",aMemo[position]); //这次只附加内容
        startActivityForResult(it,position); //启动Intent，把position当作标识符
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        aMemo[position] = (position+1)+".";
        aa.notifyDataSetChanged();//通知ListView更新数据
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            aMemo[requestCode] = data.getStringExtra("备忘");
            aa.notifyDataSetChanged();
            Toast.makeText(this,
                    "备忘记录于\n"+data.getStringExtra("日期")+"\n 修改",
                    Toast.LENGTH_LONG)
            .show();//这里没有写show（）
        }
    }
}
