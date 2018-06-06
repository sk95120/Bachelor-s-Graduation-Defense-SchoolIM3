package com.example.shikesi4287.teacherstudentim.fragment;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.shikesi4287.teacherstudentim.R;
import com.example.shikesi4287.teacherstudentim.information.Person;


public class FragmentActivityMain extends Activity implements RadioGroup.OnCheckedChangeListener {

    public ContactFragment1 contactFragment1;
    public ContactFragment2 contactFragment2;
    public ContactFragment3 contactFragment3;
    public ContactFragment4 contactFragment4;
    public FragmentManager fragmentManager;
    public Person person;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        requestWindowFeature (Window.FEATURE_NO_TITLE);//设置title在windows的显示
        setContentView (R.layout.activity_fragment_main);
        fragmentManager = getFragmentManager ();
        Intent it = this.getIntent();
       // person.setPersomName(it.get);
        init_UI();
    }

    private void init_UI() {
        RadioGroup radioGroup = findViewById (R.id.rg_control);

        //设置监听事件
        radioGroup.setOnCheckedChangeListener (this);

        FragmentTransaction transaction = fragmentManager.beginTransaction ();//系统类库方法

        //默认显示第一个
        contactFragment1 = new ContactFragment1 ();
        transaction.add (R.id.fl_contect,contactFragment1);
        transaction.commit ();
    }

    private void FragmentChangeHideShow(FragmentTransaction transaction){
        if(contactFragment1 != null){
            transaction.hide (contactFragment1);
        }
        if(contactFragment2 != null){
            transaction.hide (contactFragment2);
        }
        if(contactFragment3 != null){
            transaction.hide (contactFragment3);
        }
        if(contactFragment4 != null){
            transaction.hide (contactFragment4);
        }
    }
    public void setTabShow(int id){
        //新的Fragment事务
        FragmentTransaction transaction =   fragmentManager.beginTransaction ();
        //隐藏Fragment
        FragmentChangeHideShow (transaction);

        switch (id){
            case 0:
                if(contactFragment1 == null){
                    contactFragment1 = new ContactFragment1 ();
                    transaction.add (R.id.fl_contect,contactFragment1);
                }else{
                    transaction.show (contactFragment1);
                }
                break;
            case 1:
                if(contactFragment2 == null){
                    contactFragment2 = new ContactFragment2 ();
                    transaction.add (R.id.fl_contect,contactFragment2);
                }else{
                    transaction.show (contactFragment2);
                }
                break;
            case 2:
                if(contactFragment3 == null){
                    contactFragment3 = new ContactFragment3 ();
                    transaction.add (R.id.fl_contect,contactFragment3);
                }else{
                    transaction.show (contactFragment3);
                }

                break;
            case 3:
                if(contactFragment4 == null){
                    contactFragment4 = new ContactFragment4 ();
                    transaction.add (R.id.fl_contect,contactFragment4);
                }else{
                    transaction.show (contactFragment4);
                }
                break;
        }
        transaction.commit ();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch(checkedId){
            case R.id.rg1:
                setTabShow(0);
                break;
            case R.id.rg2:
                setTabShow(1);
                break;
            case R.id.rg3:
                setTabShow(2);
                break;
            case R.id.rg4:
                setTabShow(3);
                break;
        }
    }

}
