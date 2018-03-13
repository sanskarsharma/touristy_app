package com.technovate18.sanskar.touristy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.technovate18.sanskar.touristy.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle("About CG Tourism");

        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
