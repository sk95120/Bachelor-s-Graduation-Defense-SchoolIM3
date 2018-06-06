package com.example.shikesi4287.teacherstudentim.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.R;
import com.example.shikesi4287.teacherstudentim.memo.MemoEdit;

import static android.app.Activity.RESULT_OK;

public class ContactFragment3 extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String[] aMemo = {"1.单击编辑备忘录","2.长按清楚备忘录","3.","4.","5.","6.","7.","8.","9."};
    ListView lv;
    ArrayAdapter<String> aa;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate (R.layout.memo_list,container,false); //一定要false
        lv = (ListView)view.findViewById(R.id.listView);
        aa  = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1,
                aMemo);
        //缺少关键一句
        lv.setAdapter(aa);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(this.getContext(),MemoEdit.class);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            aMemo[requestCode] = data.getStringExtra("备忘");
            aa.notifyDataSetChanged();
            Toast.makeText(this.getContext(),
                    "备忘记录于\n"+data.getStringExtra("日期")+"\n 修改",
                    Toast.LENGTH_LONG)
                    .show();//这里没有写show（）
        }
    }
}
