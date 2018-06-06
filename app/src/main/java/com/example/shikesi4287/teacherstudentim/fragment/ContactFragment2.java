package com.example.shikesi4287.teacherstudentim.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.shikesi4287.teacherstudentim.R;

public class ContactFragment2 extends Fragment{
    WebView wv;
    String url = "http://www.neepu.edu.cn/";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate (R.layout.content_fragement2,container,false); //一定要false
        wv = view.findViewById ( R.id.wv);
        init();
        wv.loadUrl (url);
        return view;
    }
    public void init(){
		WebSettings ws = wv.getSettings ();

		ws.setUseWideViewPort(true);
		ws.setLoadWithOverviewMode(true);

		ws.setSupportZoom(true);
		ws.setBuiltInZoomControls(true);// 设置缩放

		ws.setDomStorageEnabled(true);//设置H5

		//实现页内跳转
		wv.setWebViewClient(new WebViewClient (){
			//处理https请求
			@Override
			public void onReceivedSslError(WebView view,SslErrorHandler handler, android.net.http.SslError error) {
				handler.proceed();
			}
			//在当前的webview中跳转到新的url
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}
}
