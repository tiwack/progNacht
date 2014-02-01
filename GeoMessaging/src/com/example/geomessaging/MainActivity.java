package com.example.geomessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.LinkedList;




public class MainActivity extends Activity {
	private Button button_user;
	private Button button_senden;
	private Button button_aktualisieren;
	private EditText eingabe;
	private TextView text;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//buttons felder etc holen
		button_senden = (Button)findViewById(R.id.button2);
		button_aktualisieren= (Button)findViewById(R.id.button1);
		eingabe = (EditText)findViewById(R.id.editText1);
		text = (TextView)findViewById(R.id.textView1);
		button_user = (Button)findViewById(R.id.button3);
		Log.i("Button einlesen", "done");
		
		//auf User Seite kommen
		button_user.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent geheZurUserSeite = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(geheZurUserSeite);
				
			}
		});
		
	
		button_aktualisieren.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				text.setText(Nachrichten.getNachrichten());
				
			}
			
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
