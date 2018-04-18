package com.cosmos.works.simplesmsreceiver;

import android.os.Bundle;

import com.cosmos.works.simplesmsreceiver.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;


public class BroadcastNewSms extends Activity {
	private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
	        "com.cosmos.works.simplesmsreceiver", "com.cosmos.works.simplesmsreceiver");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.androidexample_broadcast_newsms);
	}
	
	

	}
