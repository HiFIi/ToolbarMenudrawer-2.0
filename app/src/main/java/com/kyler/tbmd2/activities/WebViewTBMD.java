package com.kyler.tbmd2.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

/**
 * Created by Kyler on 1/24/2015.
 */
public class WebViewTBMD extends ToolbarMenudrawer {
    private static final String google = "https://google.com";
    private static WebView wv;
    private static ProgressBar myProgress;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_WEBVIEW;
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        wv = (WebView) findViewById(R.id.webView);
        myProgress = (ProgressBar) findViewById(R.id.progressBar);
        myProgress.setMax(100);

        /* Use this to make the webview link convert from Mobile to Desktop. ;)
        wv.getSettings().setUserAgentString("Mozilla/5.0 " +
                "(Windows NT 6.2; " +
                "WOW64) AppleWebKit/537.31 " +
                "(KHTML, like Gecko) Chrome/20 " +
                "Safari/537.31"); */

        wv.loadUrl(google);

        wv.clearCache(true);

        WebSettings webSettings = wv.getSettings();

        wv.setWebViewClient(new MyWebViewClient());

        wv.setWebChromeClient(new WebChromeClient() {

            // this will be called on page loading progress

            @Override

            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);


                myProgress.setProgress(newProgress);
                //loadingTitle.setProgress(newProgress);
                // hide the progress bar if the loading is complete

                if (newProgress == 100) {
                    myProgress.setVisibility(View.GONE);

                } else {
                    myProgress.setVisibility(View.VISIBLE);

                }

            }

        });

        wv.getSettings().setPluginState(WebSettings.PluginState.ON);

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;

            default:

        }
        ;

        return super.onOptionsItemSelected(item);
    }

    private class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }
}
