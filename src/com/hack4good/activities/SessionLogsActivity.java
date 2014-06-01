package com.hack4good.activities;

import com.hack4good.king.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SessionLogsActivity extends Activity{
	String[] countries = new String[] {
	        "India", "USA", "Canada"
	    };
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_session_logs);
	        ListView lv = (ListView) findViewById(R.id.draft_logs);
//	        lv.setAdapter(new ArrayAdapter < String > (this, R.layout.activity_session_logs, countries));
//	        lv.setOnItemClickListener(new OnItemClickListener() {

//            public void onItemClick(AdapterView <? > arg0, View arg1, int arg2,
//	                    long arg3) {
//	                    Log.v("lambdi", (String)((TextView) arg1).getText());
//	                }
//
//	            });
	        
	    }
	 
}
