package com.example.commontasker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class videos extends taskdetails {

    private Button button;
    private WebView mWebview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);
        mWebview = (WebView) findViewById(R.id.webView00);
        mWebview.setWebViewClient(new MyBrowser());
        button = (Button) findViewById(R.id.allagh_sa);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.activity_main);
        }
        else{

            Toast toast = Toast.makeText(videos.this, "Δεν έχετε σύνδεση στο Ίντερνετ , παρακαλούμε αμα θέλετε να συνεχίσετε ενεργοποιήστε τα δεδομένα του κινητού σας τηλεφώνού είτε συνδεθείτε σε ενα Wifi.", Toast.LENGTH_LONG);
            toast.show();
        }


        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        addListenerOnButtonChrome();

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void addListenerOnButtonChrome() {

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                mWebview.getSettings().setJavaScriptEnabled(true);
                mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                mWebview.setWebChromeClient(new WebChromeClient() {

                });

                mWebview.loadUrl("http://www.youtube.com");
            }

        });

    }
}







