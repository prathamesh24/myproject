package com.shipping.tribune;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.shipping.tribune.adapter.VideoAdapter;
import com.shipping.tribune.beans.YoutubeItems;

public class VideoActivity extends Fragment {

	ArrayList<YoutubeItems> items;
	View rootview;
	private static String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyByq-zWwLS6BZ6JOt1bLVZOZNSszO6PWhQ&channelId=UCIxVyW2S4jrfY27mcgSRqQg&part=snippet,id&order=date&maxResults=20";
	String response;
	ListView ylist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.activity_video, container, false);
		ylist = (ListView) rootview.findViewById(R.id.youtubeList);
		
		ylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				if( NetworkDetector.IsThereNetkork(getActivity())){
					YoutubeItems row=items.get(position);
					startActivity(new Intent(getActivity(),YoutubePlayer.class).putExtra("youtubeID", row.getId()));
				}
				else {
					Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		return rootview;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		if( NetworkDetector.IsThereNetkork(getActivity())){
			new GetYoutubeVideo().execute();
		}
		else {
			Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
		}

		
	}

	private class GetYoutubeVideo extends AsyncTask<Void, Void, Void> {
		ProgressDialog pdialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pdialog = new ProgressDialog(getActivity());
			pdialog.setMessage("Loading...");
			pdialog.setTitle("Please Wait");
			pdialog.setCancelable(false);
			pdialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;

				HttpGet httpGet = new HttpGet(url);

				httpResponse = httpclient.execute(httpGet);
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				Log.d("Response==>", response);

				items = new ArrayList<YoutubeItems>();

				JSONObject mainObject = new JSONObject(response);

				JSONArray mainarray = mainObject.getJSONArray("items");

				for (int i = 0; i < mainarray.length(); i++) {
					JSONObject singleObject = mainarray.getJSONObject(i);
					YoutubeItems youtube = new YoutubeItems();
					String yid = singleObject.getJSONObject("id").getString(
							"videoId");
					youtube.setId(yid);
					youtube.setTitle(singleObject.getJSONObject("snippet")
							.getString("title"));
					youtube.setImage("https://i.ytimg.com/vi/" + yid
							+ "/hqdefault.jpg");
					items.add(youtube);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			try {
				if(pdialog.isShowing()){
					pdialog.dismiss();
				}
				ylist.setAdapter(new VideoAdapter(getActivity(), items));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
