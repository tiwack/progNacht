package com.example.geomessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class WebServerKommunikation {
	final public static String SERVER = "http://10.0.2.2:8080/";

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl() throws IOException,
			JSONException {
		String url = SERVER + "/nachrichten.json";
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

	public static boolean doGetAnfragen(String email, String password,
			String direktive) {
		String hash = HashAndSalt.createMd5(password);
		Log.i("Mail:", email);
		Log.i("Password:", password);
		Log.i("Hash:", hash);
		String link = SERVER + direktive + "?mail=" + email + "&password="
				+ hash;
		InputStream is = null;
		String result = null;
		StringBuilder sb = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(link);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("HTTP Request CATCHBLOCK", "fail");
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"), 8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.i("HTTP Response", result);
			if (result.contains("e")) {

				return true;
			}
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		return false;
	}
}
