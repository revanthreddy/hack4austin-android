package com.hack4good.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hack4good.king.R;

public class InitDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_details);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.init_details, menu);
        return true;
    }
    
}
