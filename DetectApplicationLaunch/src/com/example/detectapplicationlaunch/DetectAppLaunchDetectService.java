package com.example.detectapplicationlaunch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DetectAppLaunchDetectService extends Service {

	
	ArrayList<String>  lockApplicationList=new ArrayList<String>();
	Timer timer  =  new Timer();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		startTimerService();
		return START_STICKY;
	}
	
	private RunningAppProcessInfo getForegroundApp() {
	    RunningAppProcessInfo result = null, info = null;

	    final ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

	    List <RunningAppProcessInfo> l = activityManager.getRunningAppProcesses();
	    Iterator <RunningAppProcessInfo> i = l.iterator();
	    while(i.hasNext()) {
	        info = i.next();
	        if(info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
	                && !isRunningService(info.processName)) {
	            result = info;
	            break;
	        }
	    }
	    return result;
	}
	
	private boolean isRunningService(String processName) {
	    if(processName == null)
	        return false;

	    RunningServiceInfo service;

	    final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

	    List <RunningServiceInfo> l = activityManager.getRunningServices(9999);
	    Iterator <RunningServiceInfo> i = l.iterator();
	    while(i.hasNext()){
	        service = i.next();
	        if(service.process.equals(processName))
	            return true;
	    }
	    return false;
	}

	
	private boolean checkifThisIsActive(RunningAppProcessInfo target){
	    boolean result = false;
	    ActivityManager.RunningTaskInfo info;

	    if(target == null)
	        return false;

	    final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

	    List <ActivityManager.RunningTaskInfo> l = activityManager.getRunningTasks(9999);
	    Iterator <ActivityManager.RunningTaskInfo> i = l.iterator();

	    while(i.hasNext()){
	        info=i.next();
	        if(info.baseActivity.getPackageName().equals(target.processName)) {
	            result = true;
	            break;
	        }
	    }

	    return result;
	}  

	// what is in b that is not in a ?
	public static Collection subtractSets(Collection a, Collection b)
	{
	    Collection result = new ArrayList(b);
	    result.removeAll(a);
	    return result;
	}
	
	public void startTimerService(){
		
		 /*timer.scheduleAtFixedRate(new TimerTask() {

		        public void run() 
		        {
		            final ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		            final List<RunningTaskInfo> services  =  activityManager.getRunningTasks(Integer.MAX_VALUE);
		                 //for (int i = 0; i < services.size(); i++) {
		                     if("com.mimos.moh.poc.searchdoctors".equalsIgnoreCase(services.get(0).baseActivity.getPackageName()))
		                     {
		                          // you may broad cast a new application launch here.  
		                          //stalkList.add(services.get(i).baseActivity.getPackageName());
		                    	 
		                    	 //Toast.makeText(DetectAppLaunchDetectService.this, "com.mimos.moh.poc.searchdoctors running", 500).show();
		                    	 Log.d("ActivityManager", "*********************************True**************************");
		                    	 
		                    	 Intent dialogIntent = new Intent(getBaseContext(), DetectAppLaunchMainActivity.class);
		                    	 dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                    	 getApplication().startActivity(dialogIntent);
		                    	 //stopSelf();
		                    	 
		                     }
		               // }

		        }
		    }, 1000, 1000);  // every 6 seconds
*/	
	
		timer.schedule(new ActivityTimerTask(), 500, 500);
		
	}
	
	class ActivityTimerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			final ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
            final List<RunningTaskInfo> services  =  activityManager.getRunningTasks(Integer.MAX_VALUE);
                 //for (int i = 0; i < services.size(); i++) {
            		//lock music player 
                     if("com.google.android.music".equalsIgnoreCase(services.get(0).baseActivity.getPackageName()))
                     {
                          // you may broad cast a new application launch here.  
                          //stalkList.add(services.get(i).baseActivity.getPackageName());
                    	 
                    	 //Toast.makeText(DetectAppLaunchDetectService.this, "com.mimos.moh.poc.searchdoctors running", 500).show();
                    	 Log.d("ActivityManager", "*********************************True**************************");
                    	 
                    	 Intent dialogIntent = new Intent(getBaseContext(), DetectAppLaunchMainActivity.class);
                    	 dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    	 getApplication().startActivity(dialogIntent);
                    	 //stopSelf();
                    	 
                     }
                    	 
                    	 
			/*ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningTaskInfo> services  =  activityManager.getRunningTasks(Integer.MAX_VALUE);
            
            checkApplicationLockStatus();
            
            for(int i=0;i<lockApplicationList.size();i++){
            	
            	String[] arrayResult=getPackageNameAndLockStatus(lockApplicationList.get(i));
            	
            	if(arrayResult[0].equalsIgnoreCase(services.get(0).baseActivity.getPackageName()) && arrayResult[1].equalsIgnoreCase("lock")){
            		 Log.d("ActivityManager", "*********************************True**************************");
	               	 Intent dialogIntent = new Intent(getBaseContext(), DetectAppLaunchMainActivity.class);
	               	 dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	               	 getApplication().startActivity(dialogIntent);
            	}
            	
            }*/
                 /*//for (int i = 0; i < services.size(); i++) {
                     if("com.mimos.moh.poc.searchdoctors".equalsIgnoreCase(services.get(0).baseActivity.getPackageName())){
                          // you may broad cast a new application launch here.  
                          //stalkList.add(services.get(i).baseActivity.getPackageName());
                    	 
                    	 //Toast.makeText(DetectAppLaunchDetectService.this, "com.mimos.moh.poc.searchdoctors running", 500).show();
                    	 Log.d("ActivityManager", "*********************************True**************************");
                    	 
                    	 Intent dialogIntent = new Intent(getBaseContext(), DetectAppLaunchMainActivity.class);
                    	 dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    	 getApplication().startActivity(dialogIntent);
                    	 //stopSelf();
                    	 
                     }*/
		}
		
	}
	
	public String[] getPackageNameAndLockStatus(String appNameStatus){
		return appNameStatus.split(":");
	}
	
	public void checkApplicationLockStatus(){
		
		Uri allTitles = Uri.parse("content://com.mimos.mobile.app.management.Apps/applications");
		
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(allTitles, null, null, null, null);
		int count=cursor.getCount();
		
		if(cursor!=null && count>0){
			if (cursor.moveToFirst()) {
				do {
					
					lockApplicationList.add(cursor.getString(cursor.getColumnIndex("package_name"))+":"+cursor.getString(cursor.getColumnIndex("app_lock_status")));
					
				} while (cursor.moveToNext());
			}
		}

		cursor.close();
				
	}
	
	public void stopTimerService(){
		timer.cancel();
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		stopTimerService();
		super.onDestroy();
	}
}
