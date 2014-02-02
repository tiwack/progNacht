package com.example.geomessaging;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Nachrichten {
	private static String jsonString = "{nachrichten:[{'id': 4,'name': 'Hugo','lat': 47.66741,'long': 9.168935,'date':'10.09.2014 - 10:10','msg': 'Alles toll'},{'id': 3,'name': 'Hugo','lat': 47.66741,'long': 9.168935,'date':'10.09.2014 - 10:10','msg': 'schoene neue Welt'},{'id': 2,'name': 'Hugo','lat': 47.66741,'long': 9.168935,'date':'10.09.2014 - 10:10','msg': 'Per Anhalter durch die Galaxis'},{'id': 1,'name': 'Hugo','lat': 47.66741,'long': 9.168935,'date':'10.09.2014 - 10:10','msg': '1984'}]}";

	public static String getNachrichten(){

		String ausgabe = "";
		JSONObject dummy = new JSONObject();
		LinkedList<Nachricht> nachrichten = new LinkedList();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			Log.i("object eingelesen", jsonObject.toString());
			JSONArray jsonArray = jsonObject.getJSONArray("nachrichten");
			Log.i("array eingelesen", jsonArray.toString());
			for(int i = 0; i < 10; i++){
				Log.i("ausgabe", ausgabe);
				dummy = jsonArray.getJSONObject(i);
				nachrichten.add(new Nachricht(dummy.getInt("id"),dummy.getString("name"), dummy.getString("date"),dummy.getDouble("long"),dummy.getDouble("lat"),dummy.getString("msg")));
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i("catchblock", "json");
			e.printStackTrace();
		}
		for(Nachricht n : nachrichten){
			//sortieren nach Entfernung
			double lati = n.getLati();
			double longi = n.getLongi();
			
			
			
			//alle relevanten Nachrichten ranhaengen
			ausgabe += n.toString();

		}
		
		return ausgabe;
		
}

}



