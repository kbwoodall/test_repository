package com.sm.rjguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class RJGuide extends Activity {
	ImageView imView;
	Bitmap bmImg;
	String text;
	String title;
	private static final int REQUEST_CODE = 10;
	ImageView image;
	MyAdapter myAdapter;
	ListView listView;
	List<String> ls;
	String[] values;
	ImageButton ib1, ib2, ib3, ib4, ib5, ib6;
	TextView tv1, tv2, tv3, tv4, tv5, tv6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rjguide);
		new LoadTablesFromJson().execute("Joh");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_rjguide, menu);
		return true;
	}

	private class MyAdapter extends ArrayAdapter<List> {
		private List<PersonEntity> listOfPersons;

		public MyAdapter(Context context, List listOfPersons) {
			super(context, R.layout.comprow, listOfPersons);
			this.listOfPersons = listOfPersons;
			listView = (ListView) findViewById(R.id.mylist);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Object l = listOfPersons.get(position);
			PersonArray pa = (PersonArray) l;
			List plist = pa.getList();

			// Log.d("PERSON ARRAY SIZE ", Integer.toString(plist.size()));

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.comprow, parent, false);
			
			tv1 = (TextView) row.findViewById(R.id.rowTextView1);
			tv2 = (TextView) row.findViewById(R.id.rowTextView2);
			tv3 = (TextView) row.findViewById(R.id.rowTextView3);
			tv4 = (TextView) row.findViewById(R.id.rowTextView4);
			tv5 = (TextView) row.findViewById(R.id.rowTextView5);
			tv6 = (TextView) row.findViewById(R.id.rowTextView6);

			ib1 = (ImageButton) row.findViewById(R.id.imageButton1);
			ib2 = (ImageButton) row.findViewById(R.id.imageButton2);
			ib3 = (ImageButton) row.findViewById(R.id.imageButton3);
			ib4 = (ImageButton) row.findViewById(R.id.imageButton4);
			ib5 = (ImageButton) row.findViewById(R.id.imageButton5);
			ib6 = (ImageButton) row.findViewById(R.id.imageButton6);

			PersonEntity p1 = null, p2 = null, p3 = null, p4 = null, p5 = null, p6 = null;
            System.out.println("Running RJGuide");
            
			switch (plist.size()) {
			case 1:
				p1 = (PersonEntity) plist.get(0);
				ib1.setImageBitmap(p1.getImage());
				tv1.setText(p1.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				tv2.setVisibility(View.GONE);
				ib2.setVisibility(View.GONE);
				tv3.setVisibility(View.GONE);
				ib3.setVisibility(View.GONE);
				tv4.setVisibility(View.GONE);
				ib4.setVisibility(View.GONE);
				tv5.setVisibility(View.GONE);
				ib5.setVisibility(View.GONE);
				tv6.setVisibility(View.GONE);
				ib6.setVisibility(View.GONE);
				break;
			case 2:
				p1 = (PersonEntity) plist.get(0);
				p2 = (PersonEntity) plist.get(1);
				ib1.setImageBitmap(p1.getImage());
				ib2.setImageBitmap(p2.getImage());
				tv1.setText(p1.getTitle());
				tv2.setText(p2.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				addListenerOnButton(ib2, p2.getText(), p2.getImage());
				tv3.setVisibility(View.GONE);
				ib3.setVisibility(View.GONE);
				tv4.setVisibility(View.GONE);
				ib4.setVisibility(View.GONE);
				tv5.setVisibility(View.GONE);
				ib5.setVisibility(View.GONE);
				tv6.setVisibility(View.GONE);
				ib6.setVisibility(View.GONE);
				break;
			case 3:
				p1 = (PersonEntity) plist.get(0);
				p2 = (PersonEntity) plist.get(1);
				p3 = (PersonEntity) plist.get(2);
				ib1.setImageBitmap(p1.getImage());
				ib2.setImageBitmap(p2.getImage());
				ib3.setImageBitmap(p3.getImage());
				tv1.setText(p1.getTitle());
				tv2.setText(p2.getTitle());
				tv3.setText(p3.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				addListenerOnButton(ib2, p2.getText(), p2.getImage());
				addListenerOnButton(ib3, p3.getText(), p3.getImage());
				tv4.setVisibility(View.GONE);
				ib4.setVisibility(View.GONE);
				tv5.setVisibility(View.GONE);
				ib5.setVisibility(View.GONE);
				tv6.setVisibility(View.GONE);
				ib6.setVisibility(View.GONE);
				break;
			case 4:
				p1 = (PersonEntity) plist.get(0);
				p2 = (PersonEntity) plist.get(1);
				p3 = (PersonEntity) plist.get(2);
				p4 = (PersonEntity) plist.get(3);
				ib1.setImageBitmap(p1.getImage());
				ib2.setImageBitmap(p2.getImage());
				ib3.setImageBitmap(p3.getImage());
				ib4.setImageBitmap(p4.getImage());
				tv1.setText(p1.getTitle());
				tv2.setText(p2.getTitle());
				tv3.setText(p3.getTitle());
				tv4.setText(p4.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				addListenerOnButton(ib2, p2.getText(), p2.getImage());
				addListenerOnButton(ib3, p3.getText(), p3.getImage());
				addListenerOnButton(ib4, p4.getText(), p4.getImage());
				tv5.setVisibility(View.GONE);
				ib5.setVisibility(View.GONE);
				tv6.setVisibility(View.GONE);
				ib6.setVisibility(View.GONE);
				break;
			case 5:
				p1 = (PersonEntity) plist.get(0);
				p2 = (PersonEntity) plist.get(1);
				p3 = (PersonEntity) plist.get(2);
				p4 = (PersonEntity) plist.get(3);
				p5 = (PersonEntity) plist.get(4);
				ib1.setImageBitmap(p1.getImage());
				ib2.setImageBitmap(p2.getImage());
				ib3.setImageBitmap(p3.getImage());
				ib4.setImageBitmap(p4.getImage());
				ib5.setImageBitmap(p5.getImage());
				tv1.setText(p1.getTitle());
				tv2.setText(p2.getTitle());
				tv3.setText(p3.getTitle());
				tv4.setText(p4.getTitle());
				tv5.setText(p5.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				addListenerOnButton(ib2, p2.getText(), p2.getImage());
				addListenerOnButton(ib3, p3.getText(), p3.getImage());
				addListenerOnButton(ib4, p4.getText(), p4.getImage());
				addListenerOnButton(ib5, p5.getText(), p5.getImage());
				tv6.setVisibility(View.GONE);
				ib6.setVisibility(View.GONE);
				break;
			case 6:
				p1 = (PersonEntity) plist.get(0);
				p2 = (PersonEntity) plist.get(1);
				p3 = (PersonEntity) plist.get(2);
				p4 = (PersonEntity) plist.get(3);
				p5 = (PersonEntity) plist.get(4);
				p6 = (PersonEntity) plist.get(5);
				ib1.setImageBitmap(p1.getImage());
				ib2.setImageBitmap(p2.getImage());
				ib3.setImageBitmap(p3.getImage());
				ib4.setImageBitmap(p4.getImage());
				ib5.setImageBitmap(p5.getImage());
				ib6.setImageBitmap(p6.getImage());
				tv1.setText(p1.getTitle());
				tv2.setText(p2.getTitle());
				tv3.setText(p3.getTitle());
				tv4.setText(p4.getTitle());
				tv5.setText(p5.getTitle());
				tv6.setText(p6.getTitle());
				addListenerOnButton(ib1, p1.getText(), p1.getImage());
				addListenerOnButton(ib2, p2.getText(), p2.getImage());
				addListenerOnButton(ib3, p3.getText(), p3.getImage());
				addListenerOnButton(ib4, p4.getText(), p4.getImage());
				addListenerOnButton(ib5, p5.getText(), p5.getImage());
				addListenerOnButton(ib6, p6.getText(), p6.getImage());
			}

			Typeface font = Typeface
					.createFromAsset(getAssets(), "verdana.ttf");

			tv1.setTypeface(font);
			tv2.setTypeface(font);
			tv3.setTypeface(font);
			tv4.setTypeface(font);
			tv5.setTypeface(font);
			tv6.setTypeface(font);
			return row;
		}
	}

	public void addListenerOnButton(ImageButton ib, final String str,
			final Bitmap image) {

		ib.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Toast.makeText(RJGuide.this,
				// "ImageButton " + str + " was clicked! ",
				// Toast.LENGTH_SHORT).show();
				Intent i = new Intent(RJGuide.this, ActivityOne.class);
				i.putExtra("imagePath", str);
				i.putExtra("Value2", "Message for Activity Two");
				i.putExtra("BitmapImage", image);
				startActivity(i);
			}
		});
	}

	private class LoadTablesFromJson extends AsyncTask<String, Void, List> {
		String imgName = "";
		Bitmap bImg = null;
		String p1 = "http://www.reviewjournal.com/images/celebrations/display/";

		public String readStream(InputStream is) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}

		protected void onPreExecute() {
		}

		protected void onPostExecute(List result) {
			myAdapter = new MyAdapter(RJGuide.this, result);
			listView.setAdapter(myAdapter);
		}

		protected List doInBackground(String... args) {
			String imgName = "";
			for (String i : args) {
				imgName = i;
			}
			imgName = "http://projects.reviewjournal.com/data/index5.php?name="
					+ imgName;
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(imgName);
			JSONArray persons = null;

			List<PersonEntity> peList = new ArrayList<PersonEntity>();

			PersonArray personArray = new PersonArray();
			List finalArray = new ArrayList();

			try {
				persons = json.getJSONArray("top");
				int x = 1;
				for (int i = 0; i < persons.length(); i++) {
					JSONObject c = persons.getJSONObject(i);
					int id = c.getInt("id");
					String text = c.getString("text");
					String title = c.getString("title");

					imgName = p1 + id + ".jpg";
					imgName = "http://projects.reviewjournal.com/data/13182.jpg";		
						
					URL myFileUrl = null;
					boolean found = false;

					System.out.println("Finding image " + imgName);
					
					try {
						myFileUrl = new URL(imgName);
					} catch (MalformedURLException e) {
						Log.d("file url not found ", e.getMessage());
						//e.printStackTrace();
					}
					try {
						HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
						conn.setDoInput(true);
						conn.connect();
						
						InputStream is = conn.getInputStream();
						bImg = BitmapFactory.decodeStream(is);
						
						found = true;
					} catch (IOException e) {
						e.printStackTrace();
						Log.d("file not found ", e.getMessage());
					}

					if (found) {
						PersonEntity pe = new PersonEntity(id, title, text);
						pe.setImage(bImg);
						peList.add(pe);
						personArray.addToList(pe);

						if (x > 5) {
							finalArray.add(personArray);
							personArray = new PersonArray();
							x = 0;
						}
						x++;
					}
				}
				finalArray.add(personArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return finalArray;
		}
	}

	// -------------------------------------------------------------------------------------------------------
}

// PersonEntity p1 = (PersonEntity) plist.get(0);
// PersonEntity p2 = (PersonEntity) plist.get(1);
// PersonEntity p3 = (PersonEntity) plist.get(2);
// PersonEntity p4 = (PersonEntity) plist.get(3);
// PersonEntity p5 = (PersonEntity) plist.get(4);
// PersonEntity p6 = (PersonEntity) plist.get(5);
/*
 * ib1.setImageBitmap(p1.getImage()); ib2.setImageBitmap(p2.getImage());
 * ib3.setImageBitmap(p3.getImage()); ib4.setImageBitmap(p4.getImage());
 * ib5.setImageBitmap(p5.getImage()); ib6.setImageBitmap(p6.getImage());
 * 
 * tv1.setText(p1.getTitle()); tv2.setText(p2.getTitle());
 * tv3.setText(p3.getTitle()); tv4.setText(p4.getTitle());
 * tv5.setText(p5.getTitle()); tv6.setText(p6.getTitle());
 * 
 * addListenerOnButton(ib1, p1.getText(), p1.getImage());
 * addListenerOnButton(ib2, p2.getText(), p2.getImage());
 * addListenerOnButton(ib3, p3.getText(), p3.getImage());
 * addListenerOnButton(ib4, p4.getText(), p4.getImage());
 * addListenerOnButton(ib5, p5.getText(), p5.getImage());
 * addListenerOnButton(ib6, p6.getText(), p6.getImage());
 */
// String p1 =
// "http://www.reviewjournal.com/images/celebrations/display/159343.jpg";
// String p2 =
// "http://www.reviewjournal.com/images/celebrations/display/61595.jpg";
// String p3 =
// "http://www.reviewjournal.com/images/celebrations/display/21485.jpg";

/*
 * // PersonEntity pe = (PersonEntity)listOfPersons.get(0); // bmImg =
 * pe.getImage(); // text = pe.getText(); // title = pe.getTitle(); //
 * Log.d("MYADAPTER LIST OF PERSONS ", listOfPersons.toString()); listView =
 * (ListView) findViewById(R.id.mylist); values = new String[] { "Android",
 * "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
 * "Max OS X", "Linux", "OS/2" };
 * 
 * ls = new ArrayList<String>(); ls.add("Android"); ls.add("iPhone");
 * ls.add("BlackBerry"); ls.add("Linx"); ls.add("WindowsMobile");
 * ls.add("WebOS"); ls.add("Ubuntu"); ls.add("Windows7"); ls.add("Max OS X");
 * ls.add("OS/2");
 */
/*
 * // ib1.setImageBitmap(bmImg); // ib2.setImageBitmap(bmImg); //
 * ib3.setImageBitmap(bmImg); // ib4.setImageBitmap(bmImg); //
 * ib5.setImageBitmap(bmImg); // ib6.setImageBitmap(bmImg);
 * 
 * // ib4.setVisibility(View.GONE); // ib5.setVisibility(View.GONE); //
 * ib6.setVisibility(View.GONE);
 * 
 * // ib1.setImageResource(R.drawable.icon);
 * //ib2.setImageResource(R.drawable.icon);
 * //ib3.setImageResource(R.drawable.icon);
 * //ib4.setImageResource(R.drawable.icon);
 * //ib5.setImageResource(R.drawable.icon);
 * //ib6.setImageResource(R.drawable.icon);
 * 
 * URL myFileUrl = null; try { myFileUrl = new URL(imgName);
 * Log.d("DOWNLOADTABLESOK ", imgName); } catch (MalformedURLException e) {
 * e.printStackTrace(); }
 * 
 * try { StringBuilder builder = new StringBuilder();
 * 
 * HttpURLConnection conn = (HttpURLConnection) myFileUrl .openConnection();
 * conn.setDoInput(true); conn.connect(); InputStream is =
 * conn.getInputStream(); //bImg = BitmapFactory.decodeStream(is);
 * Log.d("DOWNLOADTABLEOK2 ", is.toString()); BufferedReader reader = new
 * BufferedReader(new InputStreamReader(is)); String line = " "; //while ((line
 * = reader.readLine()) != null) { // builder.append(line); //}
 * Log.d("DOWNLOADTABLEOK3 ", line);
 * 
 * return bImg;
 * 
 * } catch (IOException e) { e.printStackTrace(); return null; } }
 */

/*
 * //myAdapter = new MyAdapter(RJGuide.this, ls); //
 * listView.setAdapter(myAdapter); /* listView.setOnItemClickListener(new
 * OnItemClickListener() { public void onItemClick(AdapterView<?> parent, View
 * view, int position, long id) { Toast.makeText(getApplicationContext(),
 * ((TextView) view).getText(), Toast.LENGTH_SHORT).show(); } });
 */

/*
 * 
 * public void downloadFile(String fileUrl) {
 * 
 * Log.d("downloadFile ", "entering downloadFile"); URL myFileUrl = null; try {
 * myFileUrl = new URL(fileUrl); Log.d("downloadFile ", myFileUrl.getFile());
 * Log.d("downloadHost ", myFileUrl.getHost()); Log.d("downloadPath ",
 * myFileUrl.getPath());
 * 
 * } catch (MalformedURLException e) { e.printStackTrace(); } try {
 * HttpURLConnection conn = (HttpURLConnection) myFileUrl .openConnection();
 * 
 * conn.setDoInput(true); conn.connect(); InputStream is =
 * conn.getInputStream(); if (bmImg != null) { bmImg =
 * BitmapFactory.decodeStream(is); ib1.setImageBitmap(bmImg);
 * Log.d("downloadFile ", "loaded image");
 * 
 * } Log.d("downloadFile ", "got image"); } catch (IOException e) {
 * e.printStackTrace(); } }
 */
/*
 * //
 * ----------------------------------------------------------------------------
 * --------------------------- private class LoadListView extends
 * AsyncTask<List, Void, List> {
 * 
 * public String readStream(InputStream is) { BufferedReader reader = new
 * BufferedReader( new InputStreamReader(is)); StringBuilder sb = new
 * StringBuilder();
 * 
 * String line = null; try { while ((line = reader.readLine()) != null) {
 * sb.append(line + "\n"); } } catch (IOException e) { e.printStackTrace(); }
 * finally { try { is.close(); } catch (IOException e) { e.printStackTrace(); }
 * } return sb.toString(); }
 * 
 * protected void onPreExecute() { }
 * 
 * protected void onPostExecute(List result) { // bmImg = result; myAdapter =
 * new MyAdapter(RJGuide.this, result); listView.setAdapter(myAdapter); }
 * 
 * protected List doInBackground(List... args) { String imgName = ""; Bitmap
 * bImg = null; List rList = new ArrayList();
 * 
 * // String p1 = //
 * "http://www.reviewjournal.com/images/celebrations/display/159343.jpg"; String
 * p1 = "http://www.reviewjournal.com/images/celebrations/display/";
 * 
 * List list = args[0]; Log.d("RESULT LIST2 ", list.toString());
 * 
 * PersonEntity person = (PersonEntity) list.get(0); imgName = p1 +
 * person.getId() + ".jpg";
 * 
 * URL myFileUrl = null; try { myFileUrl = new URL(imgName); } catch
 * (MalformedURLException e) { e.printStackTrace(); } Log.d("DOWNLOADFILE ",
 * imgName); try { HttpURLConnection conn = (HttpURLConnection) myFileUrl
 * .openConnection(); conn.setDoInput(true); conn.connect(); InputStream is =
 * conn.getInputStream(); bImg = BitmapFactory.decodeStream(is); return rList;
 * 
 * } catch (IOException e) { e.printStackTrace(); return null; } } }
 * 
 * //
 * ----------------------------------------------------------------------------
 * --------------------------- private class GetImage extends AsyncTask<List,
 * Void, Bitmap> {
 * 
 * public String readStream(InputStream is) { BufferedReader reader = new
 * BufferedReader( new InputStreamReader(is)); StringBuilder sb = new
 * StringBuilder();
 * 
 * String line = null; try { while ((line = reader.readLine()) != null) {
 * sb.append(line + "\n"); } } catch (IOException e) { e.printStackTrace(); }
 * finally { try { is.close(); } catch (IOException e) { e.printStackTrace(); }
 * } return sb.toString(); }
 * 
 * protected void onPreExecute() { }
 * 
 * protected void onPostExecute(Bitmap result) { bmImg = result; myAdapter = new
 * MyAdapter(RJGuide.this, ls); listView.setAdapter(myAdapter); }
 * 
 * protected Bitmap doInBackground(List... args) { String imgName = ""; Bitmap
 * bImg = null; // String p1 = //
 * "http://www.reviewjournal.com/images/celebrations/display/159343.jpg"; String
 * p1 = "http://www.reviewjournal.com/images/celebrations/display/";
 * 
 * List list = args[0]; PersonEntity person = (PersonEntity) list.get(0);
 * imgName = p1 + person.getId() + ".jpg";
 * 
 * URL myFileUrl = null; try { myFileUrl = new URL(imgName); } catch
 * (MalformedURLException e) { e.printStackTrace(); } Log.d("DOWNLOADFILE ",
 * imgName); try { HttpURLConnection conn = (HttpURLConnection) myFileUrl
 * .openConnection(); conn.setDoInput(true); conn.connect(); InputStream is =
 * conn.getInputStream(); bImg = BitmapFactory.decodeStream(is); return bImg;
 * 
 * } catch (IOException e) { e.printStackTrace(); return null; } } }
 */
// -------------------------------------------------------------------------------------------------------