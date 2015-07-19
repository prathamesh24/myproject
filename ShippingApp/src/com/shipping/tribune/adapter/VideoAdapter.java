package com.shipping.tribune.adapter;

import java.util.ArrayList;

import com.shipping.tribune.R;
import com.shipping.tribune.beans.YoutubeItems;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends BaseAdapter {

	Context context;
	ArrayList<YoutubeItems> items;

	public VideoAdapter(Context context, ArrayList<YoutubeItems> items) {
		super();
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return items.indexOf(items.get(arg0));
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertview == null) {
			LayoutInflater minflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertview = minflater.inflate(R.layout.videolayout, null);
		}
		ImageView video = (ImageView) convertview
				.findViewById(R.id.youtubevideo);
		TextView title = (TextView) convertview.findViewById(R.id.title);

		YoutubeItems row_pos = items.get(position);

		Picasso.with(context).load(row_pos.getImage())
				.placeholder(R.drawable.shipping_home).error(R.drawable.shipping_home)
				.into(video);

		title.setText(row_pos.getTitle());

		return convertview;
	}

}
