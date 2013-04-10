package com.sm.anapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.mapquest.android.maps.*;

public class OpenStreetMapDemo extends SimpleMap {
    
	protected MapView map; 
    String gps, address;
    String sLongitude, sLatitude;
    double lon, lat;
    boolean searching = false;
    //MapController mc;
	
	ArrayList<String> emptyArray = new ArrayList<String>();
	ArrayList<String> addressArray = new ArrayList<String>(); 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //mc = super.map.getController();     
        
        Intent i = getIntent();
		sLongitude = i.getStringExtra("longitude");
		sLatitude = i.getStringExtra("latitude");	
		String var = "Longitude: " + sLongitude + " Latitude: " + sLatitude;
		
		//Toast.makeText(getApplicationContext(),var, Toast.LENGTH_LONG).show();	   
        
        //super.setupMapView(new GeoPoint(38.0, -104.0), 7);
        //super.setupMapView(new GeoPoint(36.061648, -115.055639), 15);
        
        //new LoadTablesFromJson().execute(gps);
        
        //super.setupMapView(new GeoPoint(36.061648, -115.055639), 15);
        //super.setupMapView(new GeoPoint(lon, lat), 15);
				
		//String sLongitude = "36.061648";
		//String sLatitude = "-115.055639";		
		
		lon = Double.valueOf(sLongitude);
		lat = Double.valueOf(sLatitude);
		
		super.setupMapView(new GeoPoint(lon , lat), 17);	
		map  = super.map;		
		
		//Toast.makeText(getApplicationContext(),var, Toast.LENGTH_LONG).show();	
		
		showCircleOverlayPixels();
        //showCircleOverlayMeters();
          
    }

	@Override
    protected int getLayoutId() {
	    // in our simple_osm_map layout xml file, we have a MapView object
        // with no apiKey parameter present. This will default the map to 
        // use OpenStreetMap data
        return R.layout.simple_osm_map;
    }

	@Override
	public boolean isRouteDisplayed() {
		return false;
	}
	
	
	
	/*
	private class LoadTablesFromJson extends AsyncTask<String, Void, List> {

		@Override
		protected void onPreExecute() {		}

		@Override
		protected void onPostExecute(List result) {
		
			System.out.println("RETURN size: " + result.size());			
			String rLongitude = (String) result.get(0);
			String rLatitude = (String) result.get(1);	
			lon = Double.valueOf(rLongitude);
			lat = Double.valueOf(rLatitude);
			searching = false;
			
			//Toast.makeText(getApplicationContext(),"Longitude: " + " " + rLongitude + " " + "Latitude: " + " " + rLatitude, Toast.LENGTH_LONG).show();		
			
			//SimpleMap sm = new SimpleMap();
			//sm.setupMapView(new GeoPoint(lon, lat), 15);
			
			//sm.setupMapView(new GeoPoint(36.061648, -115.055639), 15);
			
			//super.setupMapView(new GeoPoint(36.061648, -115.055639), 15);
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
	*/
	// -----------------------------------------------------------------------------------------------------------
	/**
	 * Create a CircleOverlay with a fixed radius in screen pixels.  The circle will be the
	 * same size regardless of zoom level.
	 * 
	 */
	private void showCircleOverlayPixels() {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAlpha(50);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
		CircleOverlay circle = new CircleOverlay(new GeoPoint(lon,lat), 50, paint);
		
		circle.setTapListener(new CircleOverlay.OverlayTapListener() {			
			@Override
			public void onTap(GeoPoint p, MapView mapView) {
				Toast.makeText(getApplicationContext(), "Pixel Circle Tapped!", Toast.LENGTH_SHORT).show();				
			}
		});
		this.map.getOverlays().add(circle);
		this.map.invalidate();
	}
	
	/**
	 * Create a CircleOverlay with a fixed radius in meters.  The circle will change size
	 * as you zoom in or out.
	 */
	private void showCircleOverlayMeters() {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        paint.setAlpha(50);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
		CircleOverlay circle = new CircleOverlay(new GeoPoint(38.0,-105.0), 5000., paint);
		circle.setTouchEventListener(new CircleOverlay.OverlayTouchEventListener() {			
			@Override
			public void onTouch(MotionEvent evt, MapView mapView) {
				Toast.makeText(getApplicationContext(), "Meters Circle Touch!", Toast.LENGTH_SHORT).show();				
			}
		});
		this.map.getOverlays().add(circle);
		this.map.invalidate();		
	}


	
}