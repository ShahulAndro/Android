package com.example.scanandroiddevice;

import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ScanAndroidDeviceMainActivity extends Activity {
	
	TextView scanAppsData;
	StringBuffer appNameAndPermissions=new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_android_device_main);
		scanAppsData=(TextView)findViewById(R.id.scan_apps_text_view);
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo applicationInfo : packages) {
		   Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);

		   try {
		      PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

		      appNameAndPermissions.append(packageInfo.packageName+"*******:\n");
		      //Get Permissions
		      String[] requestedPermissions = packageInfo.requestedPermissions;

		      if(requestedPermissions != null) {
		         for (int i = 0; i < requestedPermissions.length; i++) {
		            Log.d("test", requestedPermissions[i]);
		            appNameAndPermissions.append(requestedPermissions[i]+"\n");
		         }
		         appNameAndPermissions.append("\n");
		      }
		      
		   } catch (NameNotFoundException e) {
		      e.printStackTrace();
		   }
		}
		scanAppsData.setText(appNameAndPermissions);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_scan_android_device_main,
				menu);
		return true;
	}

}
