package com.shipping.tribune;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkDetector {
	boolean flag;

	public static boolean IsThereNetkork(Context context) {
		boolean flag = false, mobile = false, wifi = false;

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activenetwork = manager.getActiveNetworkInfo();
		if (activenetwork != null) {
			mobile = activenetwork.getType() == ConnectivityManager.TYPE_MOBILE;
			wifi = activenetwork.getType() == ConnectivityManager.TYPE_WIFI;
			if (activenetwork.isConnected()
					|| activenetwork.isConnectedOrConnecting()) {
				if (wifi == true || mobile == true) {
					flag = true;
				} else if (wifi == false || mobile == false) {
					flag = false;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
}