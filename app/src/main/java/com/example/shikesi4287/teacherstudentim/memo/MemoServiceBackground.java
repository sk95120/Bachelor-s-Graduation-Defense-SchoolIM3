package com.example.shikesi4287.teacherstudentim.memo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.memo.MemoList;

public class MemoServiceBackground extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"shike!",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}