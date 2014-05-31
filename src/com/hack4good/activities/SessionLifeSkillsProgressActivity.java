package com.hack4good.activities;

import com.hack4good.king.R;
import com.hack4good.king.R.layout;
import com.hack4good.king.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SessionLifeSkillsProgressActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_session_life_skills_progress);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.session_life_skills_progress, menu);
		return true;
	}

}
