package com.hack4good.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.esri.android.geotrigger.GeotriggerApiClient;
import com.esri.android.geotrigger.GeotriggerApiListener;
import com.esri.android.geotrigger.GeotriggerBroadcastReceiver;
import com.esri.android.geotrigger.GeotriggerService;
import com.esri.android.geotrigger.TriggerBuilder;
import com.hack4good.geotrigger.GeotriggerHelper;
import com.hack4good.king.R;

public class HomePageActivity extends Activity implements
GeotriggerBroadcastReceiver.LocationUpdateListener,
GeotriggerBroadcastReceiver.ReadyListener {
	
	private static final String TAG = "GeotriggerActivity";
	// Create a new application at https://developers.arcgis.com/en/applications
    private static final String AGO_CLIENT_ID = "Y4nSJdZXHq66ah7W";
    // The project number from https://cloud.google.com/console
    private static final String GCM_SENDER_ID = "1046060106948";
    // A list of initial tags to apply to the device.
    // Triggers created on the server for this application, with at least one of these same tags,
    // will be active for the device.
    private static final String[] TAGS = new String[] {"some_tag", "another_tag", "android"};
    // The GeotriggerBroadcastReceiver receives intents from the
    // GeotriggerService, calling any listeners implemented in your class.
    private GeotriggerBroadcastReceiver mGeotriggerBroadcastReceiver;

    private boolean mShouldCreateTrigger;

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
	        findViewById(R.id.settings).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getApplicationContext(), InitDetailsActivity.class);
					startActivity(intent);
				}
			});
	        
	        mGeotriggerBroadcastReceiver = new GeotriggerBroadcastReceiver();

	        mShouldCreateTrigger = false;
	    }

//	@Override
//	public void onReady() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onLocationUpdate(Location arg0, boolean arg1) {
//		// TODO Auto-generated method stub
//		
//	}

	 @Override
	    public void onStart() {
	        super.onStart();

	        GeotriggerHelper.startGeotriggerService(this, AGO_CLIENT_ID, GCM_SENDER_ID, TAGS,
	                GeotriggerService.TRACKING_PROFILE_ADAPTIVE);
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();
	        // Unregister the receiver. Activity will no longer respond to
	        // GeotriggerService intents. Tracking and push notification handling
	        // will continue in the background.
	        unregisterReceiver(mGeotriggerBroadcastReceiver);
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        // Register the receiver. The default intent filter listens for all
	        // intents that the receiver can handle. If you need to handle events
	        // while the app is in the background, you must register the receiver
	        // in the manifest.
	        registerReceiver(mGeotriggerBroadcastReceiver,
	                GeotriggerBroadcastReceiver.getDefaultIntentFilter());
	    }
	    
	@Override
	public void onReady() {
		// Called when the device has registered with ArcGIS Online and is ready
        // to make requests to the Geotrigger Service API.
        Toast.makeText(this, "GeotriggerService ready!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "GeotriggerService ready!");
	}

	@Override
	public void onLocationUpdate(Location loc, boolean arg1) {
		// Called with the GeotriggerService obtains a new location update from
        // Android's native location services. The isOnDemand parameter lets you
        // determine if this location update was a result of calling
        // GeotriggerService.requestOnDemandUpdate()
        Toast.makeText(this, "Location Update Received!",
                Toast.LENGTH_SHORT).show();
        Log.d(TAG, String.format("Location update received: (%f, %f)",
                loc.getLatitude(), loc.getLongitude()));
        Log.d(TAG, "Location update received : "+loc.getAccuracy());
        
        // Create the trigger if we haven't done so already.
        if (mShouldCreateTrigger) {
            // Set create trigger flag here so that we don't create multiple
            // triggers if we get a few initial updates in rapid succession.
            mShouldCreateTrigger = false;

            // The TriggerBuilder helps build JSON parameters for use with the
            // 'trigger/create' API route.
            JSONObject params = new TriggerBuilder()
                    .setTags(TAGS[0]) // make sure to add at least one of the tags we have on the device to this trigger
                    .setGeo(loc, 100)
                    .setDirection(TriggerBuilder.DIRECTION_LEAVE)
                    .setNotificationText("You left the trigger!")
                    .build();

            // Send the request to the Geotrigger API.
            GeotriggerApiClient.runRequest(this, "trigger/create", params,
                    new GeotriggerApiListener() {
                @Override
                public void onSuccess(JSONObject data) {
//                    Toast.makeText(GeotriggerActivity.this, "Trigger created!",
//                            Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Trigger Created");
                }

                @Override
                public void onFailure(Throwable e) {
                    Log.d(TAG, "Error creating trigger!", e);
                    // It didn't work, so we need to try again
                    mShouldCreateTrigger = true;
                }
            });
        }
		
	}
	 
	 
	 
}
