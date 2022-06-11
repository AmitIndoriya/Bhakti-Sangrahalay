package com.bhakti_sangrahalay.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bhakti_sangrahalay.R;


public class BaseActivity extends AppCompatActivity {
    public static Typeface regularTypeface;
    public static Typeface boldTypeface;
    public static Typeface semiBoldTypeface;
    public static Typeface mediumTypeface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFont();
    }

    public void initFont() {
        regularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Laila-Regular.ttf");
        boldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Laila-Bold.ttf");
        semiBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Laila-SemiBold.ttf");
        mediumTypeface = Typeface.createFromAsset(getAssets(), "fonts/Laila-Medium.ttf");
    }

    public void setTitle(String title) {
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(title);
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTypeface(boldTypeface);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
