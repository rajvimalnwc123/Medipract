package com.example.nwcemp01.medipract;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Splashscreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;


    String mEmail_sharedPref;
    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String EmailId = "emailId";
    public static final String HospitalId = "hospitalid";
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

      /*  wv1 = (WebView) findViewById(R.id.webview);
        wv1.setWebViewClient(new MyBrowser());

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl("http://demo.surabibullion.com/surabiview/market_chart_3.php");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }*/


        // Check whether user already logged in or not
        sharedpreferences = getApplicationContext().getSharedPreferences(
                MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();
        mEmail_sharedPref = sharedpreferences.getString(EmailId, null);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          Intent mainIntent = new Intent(Splashscreen.this, Booking.class);
                                          Splashscreen.this.startActivity(mainIntent);
                                          Splashscreen.this.finish();

                                          if (mEmail_sharedPref == null) {
                                              Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                                              startActivity(intent);
                                              Splashscreen.this.finish();

                                          } else {
                                              Intent intent = new Intent(Splashscreen.this, Booking.class);
                                              startActivity(intent);
                                              Splashscreen.this.finish();

                                          }
                                      }
                                  }

                , SPLASH_DISPLAY_LENGTH);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);/*/
/***
 * Change Here***
 * startActivity(intent);
 * finish();
 * System.exit(0);
 * }
 */
    }
}