package com.shipping.tribune;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Fragment {

	View rootview;
	ImageView image;
	TextView topbar,desc,title;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview=inflater.inflate(R.layout.activity_detail, container,false);
		image=(ImageView) rootview.findViewById(R.id.mainImage);
		topbar=(TextView) rootview.findViewById(R.id.topbar);
		title=(TextView) rootview.findViewById(R.id.title);
		desc=(TextView) rootview.findViewById(R.id.desc);
		return rootview;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		try {
			SharedPreferences pref=getActivity().getSharedPreferences("detail", 0);
			
			Picasso.with(getActivity()).load(pref.getString("image", ""))
			.placeholder(R.drawable.shipping_home).error(R.drawable.shipping_home)
			.into(image);
			
			topbar.setText(pref.getString("cat", ""));
			title.setText(pref.getString("title", ""));
			desc.setText(Html.fromHtml(pref.getString("desc", "")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
