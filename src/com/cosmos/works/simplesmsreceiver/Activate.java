package com.cosmos.works.simplesmsreceiver;

import android.os.Bundle;

import com.cosmos.works.simplesmsreceiver.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;


public class Activate extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PackageManager p = getPackageManager();
		ComponentName componentName = new ComponentName(this, com.cosmos.works.simplesmsreceiver.BroadcastNewSms.class);
		p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		

	}
}
