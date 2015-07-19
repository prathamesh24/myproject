package com.shipping.tribune;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends FragmentActivity implements OnClickListener {

	ImageButton drawerBtn,call;
	RelativeLayout drawerlay;
	DrawerLayout dLayout;
	Button Registerwith, home, generalnews, arcticle, career,news,interview;
	ImageView appicon;
	TextView appname,number,email;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_home_screen);

		drawerlay = (RelativeLayout) findViewById(R.id.menuu);
		call = (ImageButton) findViewById(R.id.call);
		email=(TextView) findViewById(R.id.email);
		appname=(TextView) findViewById(R.id.icon);
		number=(TextView) findViewById(R.id.number);
		drawerBtn = (ImageButton) findViewById(R.id.navigationBtn);
		dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		Registerwith = (Button) findViewById(R.id.Registerwith);
		appicon = (ImageView) findViewById(R.id.appicon);
		home = (Button) findViewById(R.id.home);
		news= (Button) findViewById(R.id.news);
		generalnews = (Button) findViewById(R.id.generalnews);
		arcticle = (Button) findViewById(R.id.arctical);
		career = (Button) findViewById(R.id.carrer);
		interview=(Button) findViewById(R.id.interview);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/english_regular.ttf");
		appname.setTypeface(tf);

		call.setOnClickListener(this);
		email.setOnClickListener(this);
		number.setOnClickListener(this);
		Registerwith.setOnClickListener(this);
		drawerBtn.setOnClickListener(this);
		appicon.setOnClickListener(this);
		home.setOnClickListener(this);
		generalnews.setOnClickListener(this);
		arcticle.setOnClickListener(this);
		career.setOnClickListener(this);
		news.setOnClickListener(this);
		interview.setOnClickListener(this);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new HomeFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.navigationBtn:

			if (dLayout.isDrawerOpen(drawerlay))
				dLayout.closeDrawer(drawerlay);
			else {
				dLayout.openDrawer(drawerlay);
			}

			break;
		case R.id.news:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new News()).commit();
			break;
			
		case R.id.Registerwith:
			SharedPreferences pref=getSharedPreferences("ship", 0);
			
			if(pref.getString("saved", "no").equalsIgnoreCase("no"))
				startActivity(new Intent(this, PushNotificationActivity.class));
			else {
				dLayout.closeDrawer(drawerlay);
				Toast.makeText(HomeScreen.this, "Already Registered", Toast.LENGTH_LONG).show();
			}
			break;

		case R.id.appicon:
			drawerBtn.performClick();
			break;
		case R.id.home:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new HomeFragment()).commit();
			break;

		case R.id.arctical:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new Arcticle()).commit();
			break;
		case R.id.generalnews:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new GeneralNews()).commit();
			break;
		case R.id.carrer:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new Career()).commit();
			break;
			
		case R.id.interview:
			dLayout.closeDrawer(drawerlay);
			getSupportFragmentManager().popBackStack(null,
					getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new Interview()).commit();
			break;
			
		case R.id.number:
			contactus("tel:+91 22 31921595");
			break;
		case R.id.call:
			contactus("tel:+91 22 31921595");
			break;
		case R.id.email:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.shippingtribune.com"));
			startActivity(browserIntent);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("Are you sure you want to exit?");
			dialog.setPositiveButton(" Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							finish();
						}
					});
			dialog.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});

			dialog.show();
		} else {
			getSupportFragmentManager().popBackStack();
		}
	}
	
	public void contactus(String phno) {
		AlertDialog.Builder callalert = new AlertDialog.Builder(HomeScreen.this);
		callalert.setTitle("This call will charge as per service provider");
		final String phone = phno;
		callalert.setPositiveButton("Proceed",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Intent.ACTION_DIAL);
						intent.setData(Uri.parse(phone));
						startActivity(intent);
					}
				});
		callalert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

		callalert.show();

	}


}
