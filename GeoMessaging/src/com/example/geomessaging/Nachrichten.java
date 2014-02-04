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

	public static String getNachrichten() {

		String ausgabe = "";
		JSONObject dummy = new JSONObject();
		LinkedList<Nachricht> nachrichten = new LinkedList();
		try {
			Log.i("tryblock, json einlesen", "da");
			JSONObject jsonObject = WebServerKommunikation.readJsonFromUrl();
			Log.i("object eingelesen", jsonObject.toString());
			JSONArray jsonArray = jsonObject.getJSONArray("nachrichten");
			Log.i("array eingelesen", jsonArray.toString());
			for (int i = 0; i < 10; i++) {
				Log.i("ausgabe", ausgabe);
				dummy = jsonArray.getJSONObject(i);
				// packt die Werte aus Json in ein Listeonjekt
				nachrichten.add(new Nachricht(dummy
						.getString("name"), dummy.getString("date"), dummy
						.getDouble("long"), dummy.getDouble("lat"), dummy
						.getString("msg")));
			}

		} catch (IOException e) {
			Log.i("catchblock", "IOEXEPTION");
			e.printStackTrace();
		} catch (JSONException e) {
			// bei weniger als 10 Nachrichten laeuft er ueber den Catchblock
			// raus.
			Log.i("catchblock", "json");
			e.printStackTrace();
		}
		Iterator iterator;
		iterator = nachrichten.descendingIterator();
		while (iterator.hasNext()) {
			Nachricht n = (Nachricht)iterator.next();
			// sortieren nach Entfernung
			double lati = n.getLati();
			double longi = n.getLongi();

			// alle relevanten Nachrichten ranhaengen
			ausgabe += n.toString();

		}

		return ausgabe;

	}

}