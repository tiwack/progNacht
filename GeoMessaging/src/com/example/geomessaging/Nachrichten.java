package com.example.geomessaging;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Nachrichten {
	// String zum testen

	public static String getNachrichten(int range, double positionlong,
			double positionlat) {
		String ausgabe = "";
		JSONObject dummy = new JSONObject();
		LinkedList<Nachricht> nachrichten = new LinkedList<Nachricht>();
		try {
			Log.i("tryblock, json einlesen", "da");
			JSONObject jsonObject = WebServerKommunikation.readJsonFromUrl();
			Log.i("object eingelesen", jsonObject.toString());
			JSONArray jsonArray = jsonObject.getJSONArray("nachrichten");
			Log.i("array eingelesen", jsonArray.toString());
			for (int i = 0;; i++) {
				Log.i("ausgabe", ausgabe);
				dummy = jsonArray.getJSONObject(i);
				// packt die Werte aus Json in ein Listeonjekt
				nachrichten.add(new Nachricht(dummy.getString("name"), dummy
						.getString("date"), dummy.getDouble("long"), dummy
						.getDouble("lat"), dummy.getString("msg")));
			}
		} catch (IOException e) {
			Log.i("catchblock", "IOEXEPTION");
			e.printStackTrace();
		} catch (JSONException e) {
			// wenn alle Nachrichten eingelesen sind laeuft er ueber den
			// Catchblock
			// raus.
			Log.i("catchblock", "json");
			e.printStackTrace();
		}
		Iterator iterator;
		iterator = nachrichten.descendingIterator();
		while (iterator.hasNext()) {
			Nachricht n = (Nachricht) iterator.next();
			// sortieren nach Entfernung
			double lati = n.getLati();
			double longi = n.getLongi();
			if (!kordLiegtinRange(range, lati, positionlat, longi, positionlong)) {
				continue;
			}
			// alle relevanten Nachrichten ranhaengen
			ausgabe += n.toString();

		}
		return ausgabe;
	}

	public static boolean kordLiegtinRange(int range, double lati1,
			double lati2, double longi1, double longi2) {
		double lat;
		lat = (lati1 + lati2) / 2 * 0.01745;
		double dx = 111.3 * java.lang.Math.cos(lat) * (lati1 - lati2);
		double dy = 71.5 * (longi1 - longi2);
		double distanz = java.lang.Math.sqrt((dx * dx) + (dy * dy));
		int ergebnis = (int) distanz;
		if (ergebnis <= range) {
			return true;
		}
		return false;
	}
}
