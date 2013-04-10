package com.sm.anapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.view.Menu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	ArrayAdapter adapter;
	ArrayList<String> emptyArray = new ArrayList<String>();
	ArrayList<String> addressArray = new ArrayList<String>();
	ArrayList<String> orderArray = new ArrayList<String>();
	ListView listView;
	TextView tv;
	List<String> ls;
	String[] values;
	String orderSelected;

	Spinner spinner1, spinner2;

	private CommentsDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button searchButton = (Button) findViewById(R.id.add);
		tv = (TextView) findViewById(R.id.textView1);

		// tv.setText("  Address count");

		try {
			new LoadOrderExecutionFromJson().execute("blank");
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Wifi not available",
					Toast.LENGTH_LONG).show();
		}

		// addItemsOnSpinner1();
		// addItemsOnSpinner2();

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Perform action on click
				// Toast.makeText(getApplicationContext(),
				// "Search is in process", Toast.LENGTH_LONG).show();

				orderSelected = String.valueOf(spinner1.getSelectedItem());
				emptyArray.clear();
				emptyArray.add(0, "Retrieving data");
				tv.setText(" ");
				setListAdapter(adapter);

				System.out.println("ORDER SELECTED IS : " + orderSelected);

				new LoadTablesFromJson().execute(orderSelected);
			}
		});

		final Button typeButton = (Button) findViewById(R.id.delete);

		typeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Perform action on click
				/*
				 * Toast.makeText(getApplicationContext(),
				 * "Search Type is in process", Toast.LENGTH_LONG).show();
				 */
				orderSelected = String.valueOf(spinner1.getSelectedItem());

				Toast.makeText(
						getApplicationContext(),
						"Order Execution Number selected: "
								+ String.valueOf(spinner1.getSelectedItem()),
						Toast.LENGTH_SHORT).show();
			}
		});

		datasource = new CommentsDataSource(this);
		datasource.open();

		List<Comment> values = datasource.getAllComments();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView

		// new LoadTablesFromJson().execute("12");
		// emptyArray.add(0, "Retrieving data");

		// ArrayAdapter adapter = new ArrayAdapter(this,
		// android.R.layout.simple_list_item_1, values);
		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
				emptyArray);
		setListAdapter(adapter);

		/*
		 * Setup screen transfer for GPS
		 */

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String gps = addressArray.get(position);

				String var = ((TextView) view).getText().toString() + " " + gps;

				// When clicked, show a toast with the TextView text

				// Toast.makeText(getApplicationContext(), var,
				// Toast.LENGTH_SHORT).show();

				// Toast.makeText(getApplicationContext(),var,
				// Toast.LENGTH_SHORT).show();

				// Intent i = new Intent(MainActivity.this, GpsActivity.class);
				// Intent i = new Intent(MainActivity.this,
				// OpenStreetMapDemo.class);

				// i.putExtra("gps", gps);
				// i.putExtra("address", ((TextView)
				// view).getText().toString());

				new LoadCoordinatesFromJson().execute(gps);

				// String sLongitude = "36.061648";
				// String sLatitude = "-115.055639";

				// i.putExtra("longitude", sLongitude);
				// i.putExtra("latitude", sLatitude);

				// startActivity(i);

			}
		});

		// new LoadTablesFromJson().execute("12");
		// new LoadTablesFromJson().execute(orderSelected);
	}

	/*
	 * private class MyAdapter extends ArrayAdapter<List> { private
	 * List<AddressEntity> listOfPersons;
	 * 
	 * 
	 * public MyAdapter(Context context, List listOfPersons) { super(context,
	 * R.layout.comprow, listOfPersons); this.listOfPersons = listOfPersons;
	 * listView = (ListView) findViewById(R.id.mylist); }
	 * 
	 * @Override public View getView(int position, View convertView, ViewGroup
	 * parent) {
	 * 
	 * Object l = listOfPersons.get(position); PersonArray pa = (PersonArray) l;
	 * List plist = pa.getList();
	 * 
	 * // Log.d("PERSON ARRAY SIZE ", Integer.toString(plist.size()));
	 * 
	 * LayoutInflater inflater = (LayoutInflater)
	 * getSystemService(Context.LAYOUT_INFLATER_SERVICE); View row =
	 * inflater.inflate(R.layout.comprow, parent, false);
	 * 
	 * tv1 = (TextView) row.findViewById(R.id.rowTextView1); } return row; }
	 * 
	 * }
	 */

	public void addListenerOnButton(Button ib, final String str,
			final Bitmap image) {

		ib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Toast.makeText(activity_main,
				// "Button " + str + " was clicked! ",
				// Toast.LENGTH_SHORT).show();
				// Intent i = new Intent(RJGuide.this, ActivityOne.class);
				// i.putExtra("imagePath", str);
				// i.putExtra("Value2", "Message for Activity Two");
				// i.putExtra("BitmapImage", image);
				// startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	/**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	Intent i;
 
        switch (item.getItemId())
        {
        case R.id.menu_bookmark:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            //Toast.makeText(MainActivity.this, "dnd is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);		
            i.putExtra("comment", "dnd is Selected");
			startActivity(i);  
            return true;        
            
        case R.id.menu_save:
            //Toast.makeText(MainActivity.this, "gated is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);	
            i.putExtra("comment", "gated is Selected");
			startActivity(i);  
            return true;
 
        case R.id.menu_search:
            //Toast.makeText(MainActivity.this, "sub is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);	
            i.putExtra("comment", "sub is Selected");
			startActivity(i);  
            return true;
 
        case R.id.menu_share:
            //Toast.makeText(MainActivity.this, "vacant is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);	
            i.putExtra("comment", "vacant is Selected");
			startActivity(i);  
            return true;
 
        case R.id.menu_delete:
            //Toast.makeText(MainActivity.this, "apt is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);	
            i.putExtra("comment", "apt is Selected");
			startActivity(i);  
            return true;
 
        case R.id.menu_preferences:
            //Toast.makeText(MainActivity.this, "zip is Selected", Toast.LENGTH_SHORT).show();
            i = new Intent(getApplicationContext(), MenuOption.class);	
            i.putExtra("comment", "zip is Selected");
			startActivity(i);  
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
	
	
	// android.graphics.Color.RED;
	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		// ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>)
		// getListAdapter();
		// ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		Comment comment = null;

		switch (view.getId()) {
		case R.id.add:
			String[] comments = new String[] { "Test1", "Test2", "Test3",
					"Test4" };
			int nextInt = new Random().nextInt(4);
			// Save the new comment to the database
			comment = datasource.createComment(comments[nextInt]);
			// adapter.add(comment);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				// comment = (Comment) getListAdapter().getItem(0);
				// datasource.deleteComment(comment);
				// adapter.remove(comment);
			}
			break;
		}
		// adapter.notifyDataSetChanged();
	}

	public void addItemsOnSpinner1() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		// List<String> list = new ArrayList<String>();
		// list.add("5");
		// list.add("9");
		// list.add("12");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, orderArray);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);

	}

	/*
	 * public void addItemsOnSpinner2() {
	 * 
	 * spinner2 = (Spinner) findViewById(R.id.spinner2); List<String> list = new
	 * ArrayList<String>(); list.add("7"); list.add("8"); list.add("9");
	 * ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	 * android.R.layout.simple_spinner_item, list);
	 * dataAdapter.setDropDownViewResource
	 * (android.R.layout.simple_spinner_dropdown_item);
	 * spinner2.setAdapter(dataAdapter);
	 * 
	 * }
	 */

	public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	private class LoadTablesFromJson extends AsyncTask<String, Void, List> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onPostExecute(List result) {
			emptyArray.clear();
			addressArray.clear();
			System.out.println("RETURN size: " + result.size());

			// adapter = new ArrayAdapter(this, result);
			// listView.setAdapter(myAdapter);

			if (result.isEmpty()) {
				emptyArray.add(0, "No results found");
			} else {
				for (Object a : result) {
					AddressEntity ae = (AddressEntity) a;

					emptyArray.add(ae.getStreet());
					addressArray.add(ae.getIdAddress());

				}
			}
			String tot = Integer.toString(result.size());
			// int row = result.size() + 1;

			// emptyArray.add("Total rows " + tot);
			setListAdapter(adapter);

			// if (!result.isEmpty())
			tv.setText("Number of addresses " + result.size());

		}

		@Override
		protected List<AddressEntity> doInBackground(String... args) {
			List<AddressEntity> addressList = new ArrayList<AddressEntity>();

			String imgName = "";
			for (String i : args) {
				imgName = i;
			}
			System.out.println("Parameter LOAD SELECTED is : " + imgName);
			imgName = "http://projects.reviewjournal.com/data/index7.php?name="
					+ imgName;
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(imgName);
			JSONArray addresses = null;

			try {
				addresses = json.getJSONArray("top");
				for (int i = 0; i < addresses.length(); i++) {
					JSONObject c = addresses.getJSONObject(i);
					int id = c.getInt("id");

					String distributor = c.getString("distributor");
					String product = c.getString("product");
					String route = c.getString("route");
					String street = c.getString("street");
					String idAddress = c.getString("idAddress");

					AddressEntity ae = new AddressEntity(id, distributor,
							product, route, street, idAddress);
					addressList.add(ae);
					// aString = { iStr, a.getProduct(), a.getRoute(),
					// a.getStreet() };
					// System.out.println(ae.toString() + "/n");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return addressList;
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	private class LoadOrderExecutionFromJson extends
			AsyncTask<String, Void, List> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onPostExecute(List result) {
			orderArray.clear();
			System.out.println("RETURN size: " + result.size());

			// adapter = new ArrayAdapter(this, result);
			// listView.setAdapter(myAdapter);

			if (result.isEmpty()) {
				orderArray.add(0, "No results found");
			} else {
				for (Object a : result) {
					String item = (String) a;
					orderArray.add(item);
				}
			}
			// setListAdapter(adapter);
			System.out.println("ORDERS " + orderArray.toString());

			addItemsOnSpinner1();
		}

		@Override
		protected List<String> doInBackground(String... args) {

			List<String> orderList = new ArrayList<String>();
			String imgName = "http://projects.reviewjournal.com/data/index8.php";

			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(imgName);
			JSONArray orders = null;

			try {
				orders = json.getJSONArray("top");
				for (int i = 0; i < orders.length(); i++) {
					JSONObject c = orders.getJSONObject(i);
					String id = Integer.toString(c.getInt("id"));
					orderList.add(id);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				// Toast.makeText(activity, "Wifi Connecter is not installed.",
				// Toast.LENGTH_LONG).show();

			}
			return orderList;
		}
	}

	// -----------------------------------------------------------------------------------------------------------
	private class LoadCoordinatesFromJson extends AsyncTask<String, Void, List> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onPostExecute(List result) {

			System.out.println("RETURN size: " + result.size());
			String rLongitude = (String) result.get(0);
			String rLatitude = (String) result.get(1);

			// Toast.makeText(getApplicationContext(),"New Longitude: " + " " +
			// rLongitude + " " + "Latitude: " + " " + rLatitude,
			// Toast.LENGTH_LONG).show();

			Intent i = new Intent(MainActivity.this, OpenStreetMapDemo.class);
			// Intent i = new Intent(MainActivity.this,
			// CircleOverlayDemo.class);
			String sLongitude = rLongitude;
			String sLatitude = rLatitude;

			i.putExtra("longitude", sLongitude);
			i.putExtra("latitude", sLatitude);

			startActivity(i);

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
					String pos = "LONGITUDE IS " + longitude + " LATITUDE IS "
							+ latitude;

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
