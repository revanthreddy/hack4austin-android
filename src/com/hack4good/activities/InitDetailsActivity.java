package com.hack4good.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.hack4good.king.R;

public class InitDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_details);
        try {
            // open the file for reading
            InputStream instream = openFileInput("config.json");
            StringBuilder sb = null;
            // if file the available for reading
            if (instream != null) {
              // prepare the file for reading
              InputStreamReader inputreader = new InputStreamReader(instream);
              BufferedReader buffreader = new BufferedReader(inputreader);
                         
              String line;
              sb = new StringBuilder();
              // read every line of the file into the line-variable, on line at the time
              while (( line = buffreader.readLine()) != null) {
                // do something with the settings from the file
            	  sb.append(line);
              }
              JSONObject readFile = new JSONObject(sb.toString());
//              Toast.makeText(getApplicationContext(), readFile.get("teacherName")+"", Toast.LENGTH_LONG).show();
              EditText setValue = (EditText) findViewById(R.id.activityinitdetails_edittext_TeacherUserNameValue);
              setValue.setText(readFile.get("teacherName")+"");
              setValue = (EditText) findViewById(R.id.MultiAutoCompleteTextView1);
              setValue.setText(readFile.get("studentName")+"");
              setValue = (EditText) findViewById(R.id.activityinitdetails_edittext_StudentCurrentAddress);
              setValue.setText(readFile.get("studentAddress")+"");
            }
            
            // close the file again       
            instream.close();
          } catch (java.io.FileNotFoundException e) {
            // do something if the myfilename.txt does not exits
          } catch (IOException e) {
			// TODO Auto-generated catch block
        	  
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        findViewById(R.id.activityinitdetails_button_SetDetails).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					  // open myfilename.txt for writing
					  OutputStreamWriter out = new OutputStreamWriter(openFileOutput("config.json",0));
					  JSONObject jsonObj = new JSONObject();
					  EditText et = (EditText) findViewById(R.id.activityinitdetails_edittext_TeacherUserNameValue);
					  jsonObj.put("teacherName",et.getText().toString());
					  et = (EditText) findViewById(R.id.MultiAutoCompleteTextView1);
					  jsonObj.put("studentName",et.getText().toString());
					  et = (EditText) findViewById(R.id.activityinitdetails_edittext_StudentCurrentAddress);
					  jsonObj.put("studentAddress",et.getText().toString());
					  // write the contents on mySettings to the file
					  out.write(jsonObj.toString());
					  // close the file
					  out.close();
					} catch (Exception e) {
						Log.d("lambdi",e.getMessage());
					  //do something if an IOException occurs.
					}
				Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
				startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.init_details, menu);
        return true;
    }
    
}
