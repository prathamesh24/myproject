package com.shipping.tribune;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.RefreshCallback;
import com.parse.SaveCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PushNotificationActivity extends Activity {

	private RadioButton genderFemaleButton;
	private RadioButton genderMaleButton;
	private EditText ageEditText;
	private RadioGroup genderRadioGroup;

	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push_notification);

		// Track app opens.
		ParseAnalytics.trackAppOpened(getIntent());
		
		// Set up our UI member properties.
		this.genderFemaleButton = (RadioButton) findViewById(R.id.gender_female_button);
		this.genderMaleButton = (RadioButton) findViewById(R.id.gender_male_button);
		this.ageEditText = (EditText) findViewById(R.id.age_edit_text);
		this.genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
	}

	@Override
	public void onStart() {
		super.onStart();
		
		// Display the current values for this user, such as their age and gender.
		displayUserProfile();
		refreshUserProfile();
	}

	// Save the user's profile to their installation.
	public void saveUserProfile(View view) {		
		String ageTextString = ageEditText.getText().toString();

		if (ageTextString.length() > 0) {
			ParseInstallation.getCurrentInstallation().put("age", Integer.valueOf(ageTextString));
		}

		if (genderRadioGroup.getCheckedRadioButtonId() == genderFemaleButton.getId()) {
			ParseInstallation.getCurrentInstallation().put("gender", GENDER_FEMALE);
		} else if (genderRadioGroup.getCheckedRadioButtonId() == genderMaleButton.getId()) {
			ParseInstallation.getCurrentInstallation().put("gender", GENDER_MALE);
		} else {
			ParseInstallation.getCurrentInstallation().remove("gender");
		}

		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(ageEditText.getWindowToken(), 0);
		
		ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_success, Toast.LENGTH_SHORT);
					
					SharedPreferences pref=getSharedPreferences("ship", 0);
					Editor editor=pref.edit();
					editor.putString("saved", "yes");
					editor.commit();
					
					toast.show();
					finish();
				} else {
					e.printStackTrace();

					Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_failed, Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	}
	
	// Refresh the UI with the data obtained from the current ParseInstallation object.
	private void displayUserProfile() {
		String gender = ParseInstallation.getCurrentInstallation().getString("gender");
		int age = ParseInstallation.getCurrentInstallation().getInt("age");

		if (gender != null) {
			genderMaleButton.setChecked(gender.equalsIgnoreCase(GENDER_MALE));
			genderFemaleButton.setChecked(gender.equalsIgnoreCase(GENDER_FEMALE));
		} else {
			genderMaleButton.setChecked(false);
			genderFemaleButton.setChecked(false);
		}
		
		if (age > 0) {
			ageEditText.setText(Integer.valueOf(age).toString());
		}

	}
	
	// Get the latest values from the ParseInstallation object.
	private void refreshUserProfile() {		
		ParseInstallation.getCurrentInstallation().refreshInBackground(new RefreshCallback() {
			
			@Override
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					displayUserProfile();
				}
			}
		});
	}

}

