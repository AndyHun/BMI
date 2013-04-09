/*
 * CalculateBmiListener.java Apr 7, 2013
 * 
 * Copyright 2013 General TECH , Inc. All rights reserved.
 */
package com.gtc.bmi.listener.btn;

import java.text.DecimalFormat;

import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gtc.bmi.R;
import com.gtc.bmi.core.Constants;
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
		this.findViews(v);
		resources = v.getResources();
		try {
			outputUi(v, calculateBmi());
		} catch (BaseException ex) {
			Log.e(Constants.TAG_LOG_BMI, ex.getMessage());
		}
	}

	private void outputUi(View v, double bmi) {
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

	private double calculateBmi()throws BaseException {
		double height = Double.parseDouble(heightText.getText().toString()) / 100;
		Log.d(Constants.TAG_LOG_BMI, "height:"+height);
		double weight = Double.parseDouble(weightText.getText().toString());
		Log.d(Constants.TAG_LOG_BMI, "weight:"+weight);
		validateHeight(height);
		validateWeight(weight);
		double bmi = weight / (height * height);
		return bmi;
	}
	
	private void validateHeight(double height) throws BaseException{
		if(height<=0){
			/*Toast*/
			Toast heightToast = Toast.makeText(rootView.getContext(),R.string.height_wrong, Toast.LENGTH_SHORT);
			heightToast.setGravity(Gravity.TOP|Gravity.LEFT, heightText.getScrollX(), heightText.getScrollY());
			heightToast.show();
			throw new BaseException(resources.getText(R.string.height_wrong).toString());
		}
	}
	
	private void validateWeight(double weight) throws BaseException{
		if(weight<=0){
			/*Toast*/
			Toast.makeText(rootView.getContext(),R.string.weight_wrong, Toast.LENGTH_SHORT).show();
			throw new BaseException(resources.getText(R.string.weight_wrong).toString());
		}
	}

	private void findViews(View v) {
		if (heightText != null) {
			return;
		}
		heightText = (EditText) v.getRootView().findViewById(R.id.height);
		weightText = (EditText) v.getRootView().findViewById(R.id.weight);
		result = (TextView) v.getRootView().findViewById(R.id.result);
		suggest = (TextView) v.getRootView().findViewById(R.id.suggest);
		rootView = v.getRootView();
	}

}
