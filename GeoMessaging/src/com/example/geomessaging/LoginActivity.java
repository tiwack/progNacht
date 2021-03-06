package com.example.geomessaging;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		errorField = (TextView) findViewById(R.id.textView1);
		login = (Button) findViewById(R.id.button1);
		register = (Button) findViewById(R.id.button2);

		// einloggen
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				loginMail = (EditText) findViewById(R.id.editText1);
				loginPassword = (EditText) findViewById(R.id.editText2);
				if (User.login(loginMail.getText().toString(), loginPassword
						.getText().toString())) {
					Intent geheZurMainSeite = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(geheZurMainSeite);
				} else {
					errorField.setText("Login Ungültig");
				}
			}
		});
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				regMail = (EditText) findViewById(R.id.editText3);
				regPassword = (EditText) findViewById(R.id.editText4);
				if (User.checkRegexMail(regMail.getText().toString())) {
					if (User.register(regMail.getText().toString(), regPassword
							.getText().toString())) {
						Intent geheZurMainSeite = new Intent(
								LoginActivity.this, MainActivity.class);
						startActivity(geheZurMainSeite);
					} else {
						errorField.setText("Registrierung fehlgeschlagen");
					}
				} else {
					errorField.setText("E-Mail Adresse ist ungültig");
				}
			}
		});
	}
}
