/*
 * CalculateBmiListener.java Apr 7, 2013
 * 
 * Copyright 2013 General TECH , Inc. All rights reserved.
 */
package com.gtc.bmi.listener.btn;

import java.text.DecimalFormat;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.gtc.bmi.R;
import com.gtc.bmi.core.Constants;
import com.gtc.bmi.core.util.StringUtils;
import com.gtc.bmi.exception.BaseException;

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
	private View rootView = null;
	private Resources resources = null;

	@Override
	public void onClick(View v) {
		this.init(v);
		if (validate()) {
			try {
				makeDecision(v, calculateBmi());
			} catch (BaseException ex) {
				Log.e(Constants.TAG_LOG_BMI, ex.getMessage());
			}
		}

	}

	private void makeDecision(View v, double bmi) {
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		result.setText(resources.getString(R.string.bmi_result) + decimalFormat.format(bmi));
		if (bmi > 25) {
			suggest.setText(R.string.advice_heavy);
		} else if (bmi < 20) {
			suggest.setText(R.string.advice_light);
		} else {
			suggest.setText(R.string.advice_average);
		}
	}

	private double calculateBmi() throws BaseException {
		double height = Double.parseDouble(heightText.getText().toString()) / 100;
		Log.d(Constants.TAG_LOG_BMI, "height:" + height);
		double weight = Double.parseDouble(weightText.getText().toString());
		Log.d(Constants.TAG_LOG_BMI, "weight:" + weight);
		double bmi = weight / (height * height);
		return bmi;
	}

	private boolean validate() {
		boolean result = validateHeight();
		result &= validateWeight();
		return result;
	}

	private boolean validateHeight() {
		if (StringUtils.isEmpty(heightText.getText().toString()) || Double.parseDouble(heightText.getText().toString()) <= 0) {
			/*
			 * int[] location = new int[2];
			 * heightText.getLocationOnScreen(location); Toast heightToast =
			 * Toast.makeText(heightText.getContext(), R.string.height_wrong,
			 * Toast.LENGTH_SHORT); heightToast.setGravity(Gravity.TOP |
			 * Gravity.LEFT, location[0], location[1]); heightToast.show();
			 */
			heightText.setError(resources.getText(R.string.height_wrong));
			return false;
		} else {
			return true;
		}
	}

	private boolean validateWeight() {
		if (StringUtils.isEmpty(weightText.getText().toString()) || Double.parseDouble(weightText.getText().toString()) <= 0) {
			/*
			 * int[] location = new int[2];
			 * weightText.getLocationOnScreen(location); Toast weightToast =
			 * Toast.makeText(rootView.getContext(), R.string.weight_wrong,
			 * Toast.LENGTH_SHORT); weightToast.setGravity(Gravity.TOP |
			 * Gravity.LEFT, location[0], location[1]); weightToast.show();
			 */
			weightText.setError(resources.getText(R.string.weight_wrong));
			return false;
		} else {
			return true;
		}
	}

	private void init(View v) {
		if (heightText != null) {
			return;
		}
		heightText = (EditText) v.getRootView().findViewById(R.id.height);
		weightText = (EditText) v.getRootView().findViewById(R.id.weight);
		result = (TextView) v.getRootView().findViewById(R.id.result);
		suggest = (TextView) v.getRootView().findViewById(R.id.suggest);
		rootView = v.getRootView();
		resources = v.getResources();
	}

}
