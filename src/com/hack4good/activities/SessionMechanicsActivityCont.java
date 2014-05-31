package com.hack4good.activities;

import com.hack4good.king.R;
import com.hack4good.king.R.layout;
import com.hack4good.king.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class SessionMechanicsActivityCont extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session_mechanics_activity_cont);
		findViewById(R.id.activitymechanicscont_button_Next).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), SessionLifeSkillsProgressActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.session_mechanics_activity_cont, menu);
		return true;
	}

}
