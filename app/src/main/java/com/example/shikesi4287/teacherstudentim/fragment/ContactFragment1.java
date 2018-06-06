package com.example.shikesi4287.teacherstudentim.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shikesi4287.teacherstudentim.R;
import com.example.shikesi4287.teacherstudentim.dump.socket.MySocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactFragment1 extends Fragment{
    private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "140.143.243.213";
    private static final int PORT = 9999;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private static final int COMPLETED = 0;
    public Handler handler ;
    @SuppressLint("HandlerLeak")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate (R.layout.activity_main,container,false); //一定要false

        mEditText =  view.findViewById(R.id.txv1MES);
        mTextView =  view.findViewById(R.id.txv3MES);
        mExecutorService = Executors.newCachedThreadPool();
        //滑动bar
        Button send = view.findViewById(R.id.sendBut);
        Button connect = view.findViewById(R.id.connectBut);
        Button disconnect = view.findViewById(R.id.disconnetBut);
        handler = new Handler() ;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMsg = mEditText.getText().toString();
                mEditText.setText ("");
                mExecutorService.execute(new
                        sendService(sendMsg));
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutorService.execute(new
                        connectService());
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutorService.execute(new
                        sendService("0"));
            }
        });

        mTextView.setMovementMethod (ScrollingMovementMethod.getInstance ());
        return view;
    }

    public void connectBut(View view) {
        mExecutorService.execute(new  connectService ());
    }

    public void sendBut(View view) {
        String sendMsg = mEditText.getText().toString();
        mEditText.setText ("");
        mExecutorService.execute(new sendService (sendMsg));
    }

    public void disconnectBut(View view) {
        mExecutorService.execute(new sendService ("0"));
    }

    public void toFragment(View v){
//        Intent it = new Intent (this,FragmentActivityMain.class);
//        startActivityForResult (it,203);
    }
    public class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }

    public class connectService implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(HOST, PORT);
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter (new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("connectService:" + e.getMessage()));
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {
                if ((receiveMsg = in.readLine()) != null) {
                    Log.d(TAG, "receiveMsg:" + receiveMsg);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(mTextView.getText()+ "\n\n" + receiveMsg );
                        }
                    });
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}
