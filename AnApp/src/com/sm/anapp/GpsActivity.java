package com.sm.anapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class GpsActivity extends Activity {
	
	String gps, address;
	
	ArrayList<String> emptyArray = new ArrayList<String>();
	ArrayList<String> addressArray = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		Intent i = getIntent();
		gps = i.getStringExtra("gps");
		address = i.getStringExtra("address");	
		String var = "Coming from main screen " + address + " " + gps;
		
		Toast.makeText(getApplicationContext(),var, Toast.LENGTH_LONG).show();	
		
		new LoadTablesFromJson().execute(gps);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gps, menu);
		return true;
	}
	
	private class LoadTablesFromJson extends AsyncTask<String, Void, List> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onPostExecute(List result) {
		
			System.out.println("RETURN size: " + result.size());			
			String rLongitude = (String) result.get(0);
			String rLatitude = (String) result.get(1);		
			
			Toast.makeText(getApplicationContext(),"Longitude: " + " " + rLongitude + " " + "Latitude: " + " " + rLatitude, Toast.LENGTH_LONG).show();					
		}
		@Override
		protected List<String> doInBackground(String... args) {
			List<String> addressList = new ArrayList<String>();

			String idAddress = "";
			for (String i : args) {
				idAddress = i;
			}
			System.out.println("Parameter ADDRESS ID is : " + idAddress);
			idAddress = "http://projects.reviewjournal.com/data/index9.php?name="
					+ idAddress;
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(idAddress);
			JSONArray addresses = null;

			try {
				addresses = json.getJSONArray("top");
				for (int i = 0; i < addresses.length(); i++) {
					JSONObject c = addresses.getJSONObject(i);
				
					String longitude = c.getString("longitude");
					String latitude = c.getString("latitude");	
					String pos = "LONGITUDE IS "  + longitude + " LATITUDE IS " + latitude;
					
					addressList.add(longitude);
					addressList.add(latitude);	
					System.out.println(pos);
						
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return addressList;
		}
	}
	// -----------------------------------------------------------------------------------------------------------
}
