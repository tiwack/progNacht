package com.example.geomessaging;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private EditText loginMail;
	private EditText loginPassword;
	private EditText regMail;
	private EditText regPassword;
	private Button login;
	private Button register;
	private TextView errorField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginMail = (EditText)findViewById(R.id.editText1);
		loginPassword = (EditText)findViewById(R.id.editText2);
		regMail = (EditText)findViewById(R.id.editText3);
		regPassword = (EditText)findViewById(R.id.editText4);
		errorField = (TextView)findViewById(R.id.textView1);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
