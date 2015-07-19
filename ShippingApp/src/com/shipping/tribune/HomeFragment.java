package com.shipping.tribune;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment implements OnClickListener {

	View rootview;
	ImageView shipnews, portnews, localnews, energynews, offshorenews,
			videonews, shipbuilding, shiprecycle, aboutus;
	Integer sliderimgs[];
	TextView marquee;
	int piccount = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.activity_home_fragment, container,
				false);
		
		marquee = (TextView) rootview.findViewById(R.id.marquee);
		
		marquee.setSelected(true); 

		ViewFlipper flipper = (ViewFlipper) rootview.findViewById(R.id.topImage);
		flipper.startFlipping();
		
		sliderimgs=new Integer[]{R.drawable.pentagon,R.drawable.wir,R.drawable.squire,R.drawable.accord};


		//slider = (ImageView) rootview.findViewById(R.id.topImage);
		shipnews = (ImageView) rootview.findViewById(R.id.shipnews);
		portnews = (ImageView) rootview.findViewById(R.id.portnews);
		localnews = (ImageView) rootview.findViewById(R.id.localnews);
		energynews = (ImageView) rootview.findViewById(R.id.energynews);
		offshorenews = (ImageView) rootview.findViewById(R.id.offshorenews);
		videonews = (ImageView) rootview.findViewById(R.id.videonews);
		shipbuilding = (ImageView) rootview.findViewById(R.id.shipbuilding);
		shiprecycle = (ImageView) rootview.findViewById(R.id.shiprecycle);
		aboutus = (ImageView) rootview.findViewById(R.id.aboutus);

		shipnews.setOnClickListener(this);
		portnews.setOnClickListener(this);
		localnews.setOnClickListener(this);
		energynews.setOnClickListener(this);
		offshorenews.setOnClickListener(this);
		videonews.setOnClickListener(this);
		shipbuilding.setOnClickListener(this);
		shiprecycle.setOnClickListener(this);
		aboutus.setOnClickListener(this);
		flipper.setOnClickListener(this);
		
		//sliderview();

		return rootview;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.shipnews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new News()).addToBackStack("")
					.commit();
			break;
		case R.id.portnews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new PortNews())
					.addToBackStack("").commit();
			break;
		case R.id.localnews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new LocalNews())
					.addToBackStack("").commit();
			break;

		case R.id.energynews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new EnergyNews())
					.addToBackStack("").commit();
			break;
		case R.id.offshorenews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new Offshorenews())
					.addToBackStack("").commit();
			break;

		case R.id.videonews:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new VideoActivity())
					.addToBackStack("").commit();
			break;
		case R.id.shipbuilding:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new ShippBuilding())
					.addToBackStack("").commit();
			break;
		case R.id.shiprecycle:
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new ShipRecycling())
					.addToBackStack("").commit();
			break;
		case R.id.aboutus:
			getActivity().getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, new AboutUs())
			.addToBackStack("").commit();
			break;

		case R.id.topImage:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.shippingtribune.com"));
			getActivity().startActivity(browserIntent);
			break;
		}
	}
	
	/*private void sliderview() {

		final Handler handler = new Handler();
		final Runnable r = new Runnable() {
			public void run() {
				try {
					if (piccount >= sliderimgs.length) {
						piccount = 0;
					} else {
						slider.setImageResource(sliderimgs[piccount++]);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.d("ala re ", "error piccount cha");
				}
			}
		};
		
		Thread backgroundslider = new Thread() {
			public void run() {
				while (true) {
					try {

						// if(piccount>img.length){
						// piccount=0;
						// }
						// else{
						// sliders.setImageBitmap(img[0]);
						// Thread will sleep for 5 seconds
						// sliders.loadUrl(sliderimgs[piccount++]);
						handler.post(r);
						sleep(5* 1000);
					} catch (Exception e) {
						Log.d("ala re ", "error slider");
						e.printStackTrace();

					}
				}
			}
		};
		
		backgroundslider.start();
	}*/
}
