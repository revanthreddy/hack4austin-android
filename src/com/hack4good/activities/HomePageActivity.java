package com.hack4good.activities;

import com.hack4good.king.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HomePageActivity extends Activity{
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_home_page);
	        
	        findViewById(R.id.newLog).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getApplicationContext(), SessionBasicInfoActivity.class);
					startActivity(intent);
				}
			});
	        
	        findViewById(R.id.logs).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getApplicationContext(), SessionLogsActivity.class);
					startActivity(intent);
				}
			});
	        
	    }
}
