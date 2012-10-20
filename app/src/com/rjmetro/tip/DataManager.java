package com.rjmetro.tip;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import android.util.Log;

public class DataManager {
	public final static String TAG = "DataManager";

	public static interface DataListener {
		public void updatedData(Data newdata, DataManager dataman);
	}
	
	Data data;
	
	public DataManager(Data data) {
		this.data = data;
		try {
			updateBill("$30.00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	private ArrayList<DataListener> listeners = new ArrayList<DataListener>();
	
	public void addListener(DataListener listener) {
		listeners.add(listener);
	}
	public void removeListener(DataListener listener) {
		listeners.remove(listener);
	}
	
	
	public void notifyChange() {
		for (DataListener l : listeners) {
			l.updatedData(data, this);
		}
	}
	
	
	public String formatMoney(float value) {
		if (data.isValue(value) == false) return "";
		String s = NumberFormat.getCurrencyInstance().format(value);
		if (s.endsWith(".00")) {
			s = s.replace(".00", "");
		} 
		return s;
	}
	
	public String formatPercent(float value) {
		if (data.isValue(value) == false) return "";
		return NumberFormat.getPercentInstance().format(value);
	}
	
	public float parseMoney(String value) throws ParseException {
		return NumberFormat.getCurrencyInstance().parse(value).floatValue();
	}

	public float parsePercent(String value) throws ParseException {
		return NumberFormat.getPercentInstance().parse(value).floatValue();
	}
	
	
	
	
	
	public void updateBill(String text) throws ParseException {
		Log.d(TAG, "Updating bill: "+text);
		if (text.equals("")) {
			//well... actually, nothing here.
			return;
		}
		float value = parseMoney(text);
		Log.d(TAG, "Updating bill: value:"+value);
		if (data.isValue(value) == false) return;
		if (data.tipPercentEnabled && data.isValue(data.tipPercent)) {
			float bill = value;
			float percent = data.tipPercent;
			float tipamount = bill*percent;
			float total = bill + tipamount;
			
			data.tipAmount = tipamount;
			data.total = total;
		} else if (data.tipAmountEnabled && data.isValue(data.tipAmount)) {
			float bill = value;
			float tipamount = data.tipAmount;
			float percent = tipamount/bill;
			float total = bill + tipamount;
			
			data.tipPercent = percent;
			data.total = total;
		} else if (data.totalEnabled && data.isValue(data.total)) {
			float bill = value;
			float total = data.total;
			float tipamount = total-bill;
			float percent = tipamount/bill;
			
			data.tipAmount = tipamount;
			data.tipPercent = percent;
		}
		data.bill = value;
		notifyChange();

	}
	public void updateTipPercentage(String text) throws ParseException {
		if (text.equals("")) {
			clearEnabled();
			data.tipAmountEnabled = true;
		} else {
			float value = parsePercent(text);
			clearEnabled();
			data.tipPercentEnabled = true; //yeah, because we're calling this, it must be from the user....?
			
			float bill = data.bill;
			float percent = value;
			float tipamount = bill*percent;
			float total = bill + tipamount;
			
			data.tipAmount = tipamount;
			data.total = total;
		}
		notifyChange();		
	}
	public void updateTipDollars(String text) throws ParseException {
		if (text.equals("")) {
			clearEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearEnabled();
			data.tipAmountEnabled = true; //yeah, because we're calling this, it must be from the user....?
			
			float bill = data.bill;
			float tipamount = value;
			float percent = tipamount/bill;
			float total = bill + tipamount;
			
			data.tipPercent = percent;
			data.total = total;
		}
		notifyChange();		
		
	}
	public void updateTotal(String text) throws ParseException {
		if (text.equals("")) {
			clearEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearEnabled();
			data.totalEnabled = true; //yeah, because we're calling this, it must be from the user....?
			
			float bill = data.bill;
			float total = value;
			float tipamount = total-bill;
			float percent = tipamount/bill;
			
			data.tipAmount = tipamount;
			data.tipPercent = percent;
		}
		notifyChange();		

	}
	
	private void clearEnabled() {
		data.tipPercentEnabled = false;
		data.tipAmountEnabled = false;
		data.totalEnabled = false;
	}
	
	public String getCurrencySymbol() {
		return Currency.getInstance(Locale.getDefault()).getSymbol();
	}
	public String getPercentSymbol() {
		return "%";
	}



	
	
}
