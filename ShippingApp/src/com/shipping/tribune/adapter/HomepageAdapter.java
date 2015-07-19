package com.shipping.tribune.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shipping.tribune.R;

public class HomepageAdapter extends BaseAdapter{
	Context context;
	ArrayList<Integer>cagtegory;
	ArrayList<String>name;

	public HomepageAdapter(Context context, ArrayList<Integer> cagtegory,ArrayList<String>name) {
		this.context = context;
		this.cagtegory = cagtegory;
		this.name=name;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cagtegory.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return cagtegory.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return cagtegory.indexOf(cagtegory.get(arg0));
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(convertview==null){
			LayoutInflater inflator=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertview=inflator.inflate(R.layout.single_row, null);
		}
		
		ImageView image=(ImageView) convertview.findViewById(R.id.imageView1);
		TextView title=(TextView) convertview.findViewById(R.id.title);
		
		image.setImageResource(cagtegory.get(position));
		title.setText(name.get(position));
		
		return convertview;
	}

}
