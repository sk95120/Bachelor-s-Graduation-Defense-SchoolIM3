package com.example.shikesi4287.teacherstudentim.fragment;

import android.app.DownloadManager;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.R;
import java.io.File;

public class ContactFragment4 extends Fragment{
    private Button but ;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private ImageView mImageView6;
    private ImageView mImageView7;
    private ImageView mImageView8;
    private DownLoadApk.DownloadFinishReceiver mReceiver;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate (R.layout.content_fragement4,container,false); //一定要false
        //but = (Button ) view.findViewById(R.id.buttonUpdat);
        mImageView1 = view.findViewById(R.id.mImageView1);
        mImageView2 = view.findViewById(R.id.mImageView2);
        mImageView3 = view.findViewById(R.id.mImageView3);
        mImageView4 = view.findViewById(R.id.mImageView4);
        mImageView5 = view.findViewById(R.id.mImageView5);
        mImageView6 = view.findViewById(R.id.mImageView6);
        mImageView7 = view.findViewById(R.id.mImageView7);
        mImageView8 = view.findViewById(R.id.mImageView8);
        mImageView1.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.qyal));
        mImageView2.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.qrcode));
        mImageView3.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.wallet));
        mImageView4.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.like));
        mImageView5.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.photo));
        mImageView6.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.smiling));
        mImageView7.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.gear));
        mImageView8.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.newinfo));

        mImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView8.setImageDrawable(null);
                Toast.makeText(getContext(),"已经是最新版本！",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    //后升级
    public void butUpdat(View view){
        String url = "ftp://140.143.243.213/flow/schoolIM.apk";
        //DownLoadApk(url);
    }
    private class  DownLoadApk extends Service{
        String url ;
        public DownLoadApk(String url){
            this.url = url ;
        }
        @Override
        public void onCreate() {
            super.onCreate();
            mReceiver = new DownloadFinishReceiver();
            registerReceiver(mReceiver, new IntentFilter (DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        @Override
        public IBinder onBind(Intent intent) {
            return new DownBinder();
        }

        class DownBinder extends Binder {
            //初始化DownloadManager并开始下载
            public void startDownload (String downUrl) {
                File apkFile = new File(DownLoadApk.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "小熊.apk");
                if (apkFile.exists()) {
                    apkFile.delete();
                }

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"师生互助.apk");
                request.setDestinationUri(Uri.fromFile(file));
                DownloadManager mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                mDownloadManager.enqueue(request);
            }
        }
        //下载完成的广播
        private class DownloadFinishReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);//Android获取一个用于打开APK文件的intent
                //版本判断
                if(Build.VERSION.SDK_INT>=24) {
                    Uri apkUri = FileProvider.getUriForFile(DownLoadApk.this, "com.example.shikesi4287.install",
                                    new File (getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"师生互助.apk"));//主机+共享文件
                    intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //应用临时授权该Uri所代表的文件
                }else{
                    intent1.setDataAndType(Uri.fromFile(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"师生互助.apk")),
                            "application/vnd.android.package-archive");
                }
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(mReceiver);
        }

    }
}
