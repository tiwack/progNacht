package com.example.geomessaging;

public class Nachricht {
	private String name;
	private String date;
	private double longi;
	private double lati;
	private String msg;

	public Nachricht( String name, String date, double longi,
			double lati, String msg) {
		super();
		this.name = name;
		this.date = date;
		this.longi = longi;
		this.lati = lati;
		this.msg = msg;
	}

	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}

	public double getLati() {
		return lati;
	}

	public void setLati(double lati) {
		this.lati = lati;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// definiert wie jede einzelne Nachricht auf dem Device aussieht
	public String toString() {
		return name + " " + date + "\n " + msg + "\n\n";
	}

}
