package bealder.tourmalet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        new MixinMenuActivity().addMenuListeners(this);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        final View loader = findViewById(R.id.loader);

        webView.loadUrl("file:///android_asset/weather.html");

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                loader.setVisibility(View.GONE);
            }
        });
    }
}
