package com.shipping.tribune;

import com.parse.Parse;
import com.parse.PushService;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.
	Parse.initialize(this, "F5TSPBOWV3uMEN43oqSirjCjiSvxDIrfU3k3raVD", "zkz2AXECYfmqKUfePxXbahYKaq0RCXwCkfjpYO6a"); 

	// Specify an Activity to handle all pushes by default.
	PushService.setDefaultPushCallback(this, MainActivity.class);
  }
}