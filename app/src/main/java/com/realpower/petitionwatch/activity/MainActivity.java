package com.realpower.petitionwatch.activity;

import android.os.Bundle;

import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.activity.BaseActivity;

import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
