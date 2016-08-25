package com.example.nwcemp01.medipract;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


   /* Toolbar    mToolbars = (Toolbar) findViewById(R.id.tb_header_view);
       TextView mToolTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mToolTitle.setText("Booking");
        TextView title = (TextView) findViewById(R.id.titleid);
        title.setText("Specialization");
*/

        Button btn_booknow = (Button) findViewById(R.id.bt_booknow);
        Button btn_booklater = (Button) findViewById(R.id.bt_booklater);
        btn_booklater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentbooklater = new Intent(Booking.this, CalendarFragment.class);
                startActivity(intentbooklater);

            }

        });
        btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_booknow = new Intent(Booking.this, Category.class);
                startActivity(intent_booknow);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
}

}
