package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.net.NetworkInfo;

public class GeneralUtils{
	
	private static GeneralUtils INSTANCE = null;

	protected GeneralUtils() {}
	
	public static GeneralUtils getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new GeneralUtils();
		}
		return INSTANCE;
	}
	
	
	public boolean isTablet(Context context) 
	{
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
	
	public String getPhoneNumber(Context context)
	{
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);		
		return telephonyManager.getLine1Number();
	}
	
	public final boolean isInternetOn(Context pContext) 
	{
		ConnectivityManager connec =  (ConnectivityManager)pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
				connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) 
		{
			return true;
		} 
		else if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) 
		{
			return false;
		}
		
		return false;
	}
}
