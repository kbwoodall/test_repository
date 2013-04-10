package com.sm.rjguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOne extends Activity {
	private static final int REQUEST_CODE = 10;
	String imagePath;
	ImageView image;
	TextView v1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_one);
		// setContentView(new ZoomableImageView(this));

		Intent i = getIntent();
		imagePath = i.getStringExtra("imagePath");
		String r2 = i.getStringExtra("Value2");
		Bitmap bitmap = (Bitmap) i.getParcelableExtra("BitmapImage");
		
		Bitmap newmap = scaleBitmap(bitmap, 150, 150); 

		v1 = (TextView) findViewById(R.id.textView1);
		v1.setText(imagePath);

		image = (ImageView) findViewById(R.id.imageView1);
		image.setImageBitmap(bitmap);
		//image.setImageBitmap(newmap);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			if (data.hasExtra("returnKey1")) {
				// Toast.makeText(this,
				// data.getExtras().getString("returnKey1"),
				// Toast.LENGTH_SHORT).show();
			}
		}
	}

	public static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth,
			float newHeight) {
		if (bitmapToScale == null)
			return null;
		// get the original width and height
		int width = bitmapToScale.getWidth();
		int height = bitmapToScale.getHeight();
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();

		// resize the bit map
		matrix.postScale(newWidth / width, newHeight / height);

		// recreate the new Bitmap and set it back
		return Bitmap.createBitmap(bitmapToScale, 0, 0,
				bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix,
				true);
	}

}

// new GetImage().execute(imagePath);
// scaleGesture = (TextView)findViewById(R.id.ScaleGesture);
// scaleGestureDetector = new ScaleGestureDetector(this,
// new simpleOnScaleGestureListener());

// import android.view.MotionEvent;
// import android.view.ScaleGestureDetector;
// import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
// ScaleGestureDetector scaleGestureDetector;
/*
 * @Override public boolean onTouchEvent(MotionEvent event) { // TODO
 * Auto-generated method stub scaleGestureDetector.onTouchEvent(event); return
 * true; }
 * 
 * public class simpleOnScaleGestureListener extends
 * SimpleOnScaleGestureListener {
 * 
 * @Override public boolean onScale(ScaleGestureDetector detector) { // TODO
 * Auto-generated method stub
 * //v1.setText(String.valueOf(detector.getScaleFactor())); return true; }
 * 
 * @Override public boolean onScaleBegin(ScaleGestureDetector detector) { //
 * TODO Auto-generated method stub //v1.setVisibility(View.VISIBLE); return
 * true; }
 * 
 * @Override public void onScaleEnd(ScaleGestureDetector detector) { // TODO
 * Auto-generated method stub //v1.setVisibility(View.INVISIBLE); } }
 */

/*
 * public class GetImage extends AsyncTask<String, Void, Bitmap> {
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
 * protected void onPostExecute(Bitmap result) { if (result != null)
 * image.setImageBitmap(result); }
 * 
 * @Override protected Bitmap doInBackground(String... arg0) {
 * 
 * String imgName = ""; Bitmap bImg = null;
 * 
 * for (String i : arg0) { imgName = i; } Log.d("DOWNLOADFILE ", imgName); URL
 * myFileUrl = null; try { myFileUrl = new URL(imgName);
 * Log.d("DOWNLOADURL OK ", imgName); } catch (MalformedURLException e) { //
 * e.printStackTrace(); Log.d("DOWNLOADURL NOT FOUND ", imgName); return null; }
 * try { HttpURLConnection conn = (HttpURLConnection) myFileUrl
 * .openConnection(); conn.setDoInput(true); conn.connect(); InputStream is =
 * conn.getInputStream(); bImg = BitmapFactory.decodeStream(is);
 * Log.d("DOWNLOADURL FOUND IMAGE ", imgName); return bImg;
 * 
 * } catch (IOException e) { e.printStackTrace(); return null; } } } }
 * 
 * 
 * Toast.makeText(ActivityOne.this, imagePath, // Toast.LENGTH_SHORT).show();
 * Bitmap bm = getBitmapFromURL(imagePath);
 * 
 * public void onClick(View view) { // Intent i = new Intent(this,
 * ActivityTwo.class); // i.putExtra("Value1",
 * "This value one for ActivityTwo "); // i.putExtra("Value2",
 * "This value two ActivityTwo"); // Set the request code to any code you like,
 * you can identify the // callback via this code // startActivityForResult(i,
 * REQUEST_CODE); }
 * 
 * public Bitmap getBitmapFromURL(String src) { try { URL url = new URL(src);
 * HttpURLConnection connection = (HttpURLConnection) url .openConnection();
 * connection.setDoInput(true); connection.connect(); InputStream input =
 * connection.getInputStream(); Bitmap myBitmap =
 * BitmapFactory.decodeStream(input); return myBitmap; } catch (IOException e) {
 * e.printStackTrace(); return null; } }
 */

