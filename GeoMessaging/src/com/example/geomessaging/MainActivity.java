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
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{
	private Button button_user;
	private Button button_senden;
	private Button button_aktualisieren;
	private EditText eingabe;
	private TextView text;
	private Spinner range;
	Location location;
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
	GPSTracker gps = new GPSTracker(MainActivity.this);
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
		// auf User Seite kommen bzw logout anzeigen wenn eingeloggt
		if (User.getMail() != null) {
			button_user.setText("Logout");
		}

		button_user.setOnClickListener(new OnClickListener() {
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
				if (reichweite.equals("alles")) {
					reichweiteZahl = 50000;
				} else {
					reichweiteZahl = Integer.parseInt(reichweite);
				}
				
				double positionLat = gps.getLatitude();
				double positionLong =  gps.getLongitude();
				String bla = String.valueOf(positionLat);
				Log.i("Nachricht aufruf mit range", reichweite);
				Log.i("LAT", bla);
				
				text.setText(Nachrichten.getNachrichten(reichweiteZahl,
						positionLong, positionLat));
			}

		});

		// Nachricht senden, falls nicht eingelogt, GoTo einloggseite
		button_senden.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (User.getMail() != null) {
					eingabe = (EditText) findViewById(R.id.editText1);
					GregorianCalendar cal = new GregorianCalendar();
					Log.i("Gregorian Calender", cal.toString());
					try {
						double positionLat = gps.getLatitude();
						double positionLong =  gps.getLongitude();
						String positionLatString = String.valueOf(positionLat);
						String positionLongString = String.valueOf(positionLong);
						JSONObject json = new JSONObject();
						json.put("name", User.getMail());
						json.put("date", dateFormat.format(cal.getTime()));
						json.put("msg", eingabe.getText().toString());
						json.put("lat", positionLat);
						json.put("long", positionLong);
						Log.i("Senden Jsonobjekt", json.toString());
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(
								WebServerKommunikation.SERVER + "getJson.php");
						// Request parameters and other properties.
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("json", json
								.toString()));
						try {
							httpPost.setEntity(new UrlEncodedFormEntity(params,
									"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// writing error to Log
							e.printStackTrace();
						}
						// Making HTTP Request
						try {
							HttpResponse response = httpClient
									.execute(httpPost);
							HttpEntity respEntity = response.getEntity();
							if (respEntity != null) {
								// EntityUtils to get the reponse content
								String content = EntityUtils
										.toString(respEntity);
								Log.i("RESPONSE", content);
							}
							eingabe.setText("");
							// aktualisieren klicken um nachrichten zu holen
							button_aktualisieren.performClick();
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
		button_aktualisieren.performClick();
	}

	public void onLocationChanged(Location location) { this.location = location; Log.i("Location", "new Location: "+location); }

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
