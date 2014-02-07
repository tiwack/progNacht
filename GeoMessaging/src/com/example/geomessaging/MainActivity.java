package com.example.geomessaging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button_user;
	private Button button_senden;
	private Button button_aktualisieren;
	private EditText eingabe;
	private TextView text;
	private Spinner range;
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// network im mainthread
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// buttons felder etc holen
		button_senden = (Button) findViewById(R.id.button2);
		button_aktualisieren = (Button) findViewById(R.id.button1);
		text = (TextView) findViewById(R.id.textView1);
		button_user = (Button) findViewById(R.id.button3);
		Log.i("Button einlesen", "done");
		text.setMovementMethod(new ScrollingMovementMethod());
		range = (Spinner) findViewById(R.id.spinner1);
		
		
		
		
		text.setText(Nachrichten.getNachrichten());
		// auf User Seite kommen bzw logout anzeigen wenn eingeloggt
		if (User.getMail() != null) {
			button_user.setText("Logout");
		}
		button_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (User.getMail() == null) {
					Intent geheZurUserSeite = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(geheZurUserSeite);
				} else {
					button_user.setText("Benutzer");
					User.deleteMail();
				}

			}
		});

		// neue Nachrichten holen
		button_aktualisieren.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int reichweiteZahl;
				String reichweite = range.getSelectedItem().toString();
				if(reichweite.equals("alles")){
				reichweiteZahl = 40000;
				}else{
				reichweiteZahl = Integer.parseInt(reichweite);
				text.setText(Nachrichten.getNachrichten());
				}
				Log.i("Nachricht aufruf mit range", reichweite);
				text.setText(Nachrichten.getNachrichten());
			}

		});

		// Nachricht senden, falls nicht eingelogt, GoTo einloggseite

		button_senden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (User.getMail() != null) {
					eingabe = (EditText) findViewById(R.id.editText1);
					GregorianCalendar cal = new GregorianCalendar();
					Log.i("Gregorian Calender", cal.toString());
					try {
						
						JSONObject json = new JSONObject();
						json.put("name", User.getMail());
						json.put("date", dateFormat.format(cal.getTime()));
						json.put("msg", eingabe.getText().toString());
						json.put("lat", "234.234");
						json.put("long", "123.34");
						Log.i("Senden Jsonobjekt", json.toString());
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(WebServerKommunikation.SERVER + "getJson.php");
						// Request parameters and other properties.
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("json", json.toString()));
						try {
						    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						} catch (UnsupportedEncodingException e) {
						    // writing error to Log
						    e.printStackTrace();
						}
						/*
						 * Making HTTP Request
						 */
						try {
						    HttpResponse response = httpClient.execute(httpPost);
						    HttpEntity respEntity = response.getEntity();
						   
						    if (respEntity != null) {
						        // EntityUtils to get the reponse content
						        String content =  EntityUtils.toString(respEntity);
						        Log.i("RESPONSE", content);
						    }
						    eingabe.setText("");
						    text.setText(Nachrichten.getNachrichten());
						} catch (ClientProtocolException e) {
						    // writing exception to log
						    e.printStackTrace();
						} catch (IOException e) {
						    // writing exception to log
						    e.printStackTrace();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Intent geheZurUserSeite = new Intent(MainActivity.this,
							LoginActivity.class);
					startActivity(geheZurUserSeite);
				}

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
