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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

import com.shipping.tribune.adapter.LocalNewsAdapter;
import com.shipping.tribune.beans.NewsItem;

public class LocalNews extends Fragment implements OnItemClickListener{

	View rootview;
	private static String url = "http://www.shippingtribune.com/api/localnews.php";
	String response;
	ArrayList<NewsItem> items;
	ListView newsList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.activity_local_news, container,
				false);
		newsList = (ListView) rootview.findViewById(R.id.listview);
		newsList.setOnItemClickListener(this);
		return rootview;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		try {
			if( NetworkDetector.IsThereNetkork(getActivity())){
				new Webservice().execute();
			}
			else {
				Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private class Webservice extends AsyncTask<Void, Void, Void> {
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

				items = new ArrayList<NewsItem>();

				HttpGet httpGet = new HttpGet(url);

				httpResponse = httpclient.execute(httpGet);
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				Log.d("Response==>", response);

				JSONArray mainArray = new JSONArray(response);

				JSONArray jsonarray = mainArray.getJSONArray(0).getJSONArray(0);

				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					NewsItem item = new NewsItem();
					item.setID(obj.getString("ID"));
					item.setPost_title(obj.getString("post_title"));
					item.setImage(obj.getString("image"));
					item.setPost_date(obj.getString("post_date"));
					item.setDescription(obj.getString("description"));

					items.add(item);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			try {
				if (pdialog.isShowing()) {
					pdialog.dismiss();
				}

				newsList.setAdapter(new LocalNewsAdapter(getActivity(), items));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		SharedPreferences pref = getActivity()
				.getSharedPreferences("detail", 0);
		Editor editot = pref.edit();
		NewsItem row = items.get(position);

		String image = row.getImage();
		String title = row.getPost_title();
		String desc = row.getDescription();
		editot.putString("image", image);
		editot.putString("title", title);
		editot.putString("desc", desc);
		editot.putString("cat", "Shipping Building");
		editot.commit();

		getActivity().getSupportFragmentManager().beginTransaction()
				.addToBackStack("")
				.replace(R.id.content_frame, new DetailActivity()).commit();

	}
}