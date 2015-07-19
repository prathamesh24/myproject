package com.shipping.tribune;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutUs extends Fragment {

	String desc;
	TextView texttv;
	View rootview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.activity_about_us, container,
				false);
		texttv = (TextView) rootview.findViewById(R.id.texttv);
		desc = "The Shipping Tribune provides important shipping news and updates to Maritime fraternity spread across the globe. One of the main objectives of the Shipping Tribune is to provide essential platform for Shipping and Maritimes networking.The Shipping Tribune receives over 3,600,000 clicks every month. Our website is visited mostly by ship owners, operators, managers, ship chandlers and senior shipping executives with enormous decision making roles. Our visitors are from all across the globe from Shipping and Maritime Industry. Latest news from the industry is updated on regular basis and our Daily Newsletter is emailed to shipping and Maritime community all over the world.";
		texttv.setText(Html.fromHtml(desc));
		return rootview;
	}

}
