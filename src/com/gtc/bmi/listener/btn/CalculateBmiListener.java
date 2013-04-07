/*
 * CalculateBmiListener.java Apr 7, 2013
 * 
 * Copyright 2013 General TECH , Inc. All rights reserved.
 */
package com.gtc.bmi.listener.btn;

import java.text.DecimalFormat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.gtc.bmi.R;

/**
 * @description
 * @author AndyHun
 * 
 * @Version 1.0
 */
public class CalculateBmiListener implements OnClickListener {

	private EditText heightText = null;
	private EditText weightText = null;
	private TextView result = null;
	private TextView suggest = null;

	@Override
	public void onClick(View v) {
		this.findViews(v);
		outputUi(v, calculateBmi());
	}

	private void outputUi(View v, double bmi) {
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		result.setText(v.getResources().getString(R.string.bmi_result) + decimalFormat.format(bmi));

		if (bmi > 25) {
			suggest.setText(R.string.advice_heavy);
		} else if (bmi < 20) {
			suggest.setText(R.string.advice_light);
		} else {
			suggest.setText(R.string.advice_average);
		}
	}

	private double calculateBmi() {
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		double height = Double.parseDouble(heightText.getText().toString()) / 100;
		double weight = Double.parseDouble(weightText.getText().toString());
		double bmi = weight / (height * height);
		return bmi;
	}

	private void findViews(View v) {
		if (heightText != null) {
			return;
		}
		heightText = (EditText) v.getRootView().findViewById(R.id.height);
		weightText = (EditText) v.getRootView().findViewById(R.id.weight);
		result = (TextView) v.getRootView().findViewById(R.id.result);
		suggest = (TextView) v.getRootView().findViewById(R.id.suggest);
	}

}
