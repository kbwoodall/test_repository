package com.sm.anapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerTest extends Activity {
	/*
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:orientation="vertical"
	    android:layout_width="fill_parent"
	    android:layout_height="fill_parent"
	    >
	    <Spinner android:id="@+id/spinner"
	        android:layout_width="fill_parent"
	        android:layout_height="wrap_content"
	        android:drawSelectorOnTop="true"
	        android:prompt="@string/item_prompt"
	    />
	</LinearLayout>
		
	public static final CharSequence[] DAYS_OPTIONS  = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, DAYS_OPTIONS);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
	*/
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        //Spinner s = (Spinner) findViewById(R.id.spinner);
        //Prepar adapter 
        //HERE YOU CAN ADD ITEMS WHICH COMES FROM SERVER.
        //final MyData items[] = new MyData[3];
        //items[0] = new MyData("key1", "value1");
        //items[1] = new MyData("key2", "value2");
        //items[2] = new MyData("key3", "value3");
        //ArrayAdapter<MyData> adapter = new ArrayAdapter<MyData>(this,
        //        android.R.layout.simple_spinner_item, items);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //s.setAdapter(adapter);
        /*
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                MyData d = items[position];

                //Get selected value of key 
                String value = d.getValue();
                String key = d.getSpinnerText();
            }
          */
            //public void onNothingSelected(AdapterView<?> parent) {
            //}
        //);
    }

    class MyData {
        public MyData(String spinnerText, String value) {
            this.spinnerText = spinnerText;
            this.value = value;
        }

        public String getSpinnerText() {
            return spinnerText;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return spinnerText;
        }

        String spinnerText;
        String value;
    }
}