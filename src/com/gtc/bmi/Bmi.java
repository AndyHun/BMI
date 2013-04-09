package com.gtc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.gtc.bmi.listener.btn.CalculateBmiListener;

public class Bmi extends Activity {
	Button submmit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		setListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bmi, menu);
		return true;
	}

	private void findViews() {
		this.submmit = (Button) findViewById(R.id.submmit);
	}

	private void setListeners() {
		this.submmit.setOnClickListener(new CalculateBmiListener());
	}
}
