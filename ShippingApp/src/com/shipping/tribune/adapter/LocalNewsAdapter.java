package com.shipping.tribune.adapter;

import java.util.ArrayList;

import com.shipping.tribune.R;
import com.shipping.tribune.beans.NewsItem;
import com.shipping.tribune.beans.ShippingItem;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LocalNewsAdapter extends BaseAdapter{
	Context context;
	ArrayList<NewsItem> items;

	public LocalNewsAdapter(Context context, ArrayList<NewsItem> items) {
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
			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertview = inflator.inflate(R.layout.list_row, null);
		}

		ImageView image = (ImageView) convertview.findViewById(R.id.newsPic);
		TextView title = (TextView) convertview.findViewById(R.id.news);
		TextView date = (TextView) convertview.findViewById(R.id.date);

		NewsItem row_pos = items.get(position);

		Picasso.with(context).load(row_pos.getImage())
				.placeholder(R.drawable.shipping_home).error(R.drawable.shipping_home)
				.into(image);

		title.setText(row_pos.getPost_title());
		date.setText(row_pos.getPost_date());

		return convertview;
	}

}
