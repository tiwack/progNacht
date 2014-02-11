package com.example.geomessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		Log.i("JeadJsonFromUrlMethod", url);
		InputStream is = new URL(url).openStream();
		Log.i("inputStream", is.toString());
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			Log.i("jsonText", jsonText);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}

	}
}
