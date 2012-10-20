package com.rjmetro.tip;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
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
//		try {
//			updateBill("$30.00");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//data.items.add(15f);
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
	
	public String formatInteger(int value) {
		if (data.isValue(value) == false) return "";
		return NumberFormat.getIntegerInstance().format(value);
	}
	
	public float parseMoney(String value) throws ParseException {
		return NumberFormat.getCurrencyInstance().parse(value).floatValue();
	}

	public float parsePercent(String value) throws ParseException {
		return NumberFormat.getPercentInstance().parse(value).floatValue();
	}
	
	public int parseInteger(String value) throws ParseException {
		return NumberFormat.getIntegerInstance().parse(value).intValue();
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
		data.bill = value;
		//this should be the first condition we hit.
		if (data.tipPercentEnabled == false && data.isValue(data.tipPercent)) {
			calculateTipPercent(data.tipPercent);
		} else if (data.tipPercentEnabled && data.isValue(data.tipPercent)) {
			calculateTipPercent(data.tipPercent);
		} else if (data.tipAmountEnabled && data.isValue(data.tipAmount)) {
			calculateTipDollars(data.tipAmount);
		} else if (data.totalEnabled && data.isValue(data.total)) {
			calculateTotal(data.total);
		}
		updateOthers();
		notifyChange();

	}
	public void updateTipPercentage(String text) throws ParseException {
		if (text.equals("")) {
			clearSimpleEnabled();
			data.tipAmountEnabled = true;
		} else {
			float value = parsePercent(text);
			clearSimpleEnabled();
			data.tipPercentEnabled = true; 
			
			calculateTipPercent(value);
			updateOthers();
		}
		notifyChange();		
	}
	public void updateTipDollars(String text) throws ParseException {
		if (text.equals("")) {
			clearSimpleEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearSimpleEnabled();
			data.tipAmountEnabled = true; 
			
			calculateTipDollars(value);
			updateOthers();
		}
		notifyChange();		
		
	}
	public void updateTotal(String text) throws ParseException {
		if (text.equals("")) {
			clearSimpleEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearSimpleEnabled();
			data.totalEnabled = true; 
			
			calculateTotal(value);
			updateOthers();
		}
		notifyChange();		

	}
	
	/** Stuff for the split screen **/
	
	public void updateNumber(String text) throws ParseException {
		if (text.equals("")) {
			return;
		} else {
			int value = parseInteger(text);

			calculateNumberOfPeople(value);
			updateOthers();
		}
		notifyChange();		

	}
	public void updateTotalEach(String text) throws ParseException {
		if (text.equals("")) {
			clearSplitEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearSplitEnabled();
			data.totalEachEnabled = true; 
			
			calculateTotalEach(value);
			updateOthers();
		}
		notifyChange();		

	}
	public void updateTipDollarsEach(String text) throws ParseException {
		if (text.equals("")) {
			clearSplitEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearSplitEnabled();
			data.tipAmountEachEnabled = true; 
			
			calculateTipDollarsEach(value);
			updateOthers();
		}
		notifyChange();		

	}
	
	/** stuff for the custom screen **/
	public void updateTax(String text) throws ParseException {
		if (text.equals("")) {
			return;
		} else {
			float value = parseMoney(text);

			calculateTax(value);
			updateOthers();
		}
		notifyChange();		

	}
	public void updateTotalYour(String text) throws ParseException {
		if (text.equals("")) {
			clearCustomEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearCustomEnabled();
			data.totalYourEnabled = true; 
			
			calculateTotalYour(value);
			updateOthers();
		}
		notifyChange();		

	}
	public void updateTipDollarsYour(String text) throws ParseException {
		if (text.equals("")) {
			clearCustomEnabled();
			data.tipPercentEnabled = true;
		} else {
			float value = parseMoney(text);
			clearCustomEnabled();
			data.tipAmountYourEnabled = true; 
			
			calculateTipDollarsYour(value);
			updateOthers();
		}
		notifyChange();		

	}
	public void updateItem(String text, int index) throws ParseException {
		if (text.equals("")) {
			return;
		} else {
			float value = parseMoney(text);
			
			calculateItem(value, index);
			updateOthers();
		}
		notifyChange();		

	}


	
	

	/**                             **/
	
	
	public void calculateTipPercent(float value) {
		float bill = data.bill;
		float percent = value;
		float tipamount = bill*percent;
		float total = bill + tipamount;
		
		data.tipAmount = tipamount;
		data.total = total;
		data.tipPercent = value;
	}


	public void calculateTipDollars(float value) {
		float bill = data.bill;
		float tipamount = value;
		float percent = tipamount/bill;
		float total = bill + tipamount;
		
		data.tipPercent = percent;
		data.total = total;
		data.tipAmount = value;
	}
	
	public void calculateTotal(float value) {
		float bill = data.bill;
		float total = value;
		float tipamount = total-bill;
		float percent = tipamount/bill;
		
		data.tipAmount = tipamount;
		data.tipPercent = percent;
		data.total = value;
	}
	
	public void calculateNumberOfPeople(int number) {
		data.numberOfPeople = number;
	}
	public void calculateTotalEach(float value) {
		calculateTotal(value*data.numberOfPeople);
		data.totalEach = value;
	}
	public void calculateTipDollarsEach(float value) {
		calculateTipDollars(value*data.numberOfPeople);
		data.tipAmountEach = value;
	}
	
	public void calculateTax(float value) {
		data.tax = value;
	}
	public void calculateTotalYour(float value) {
		if (getSumOfItems() > 0)
			calculateTipPercent( (value-getSumOfItems())/getSumOfItems() );
		data.totalYour = value;
	}
	public void calculateTipDollarsYour(float value) {
		if (getSumOfItems() > 0)
			calculateTipPercent(value/getSumOfItems());
		data.tipAmountYour = value;
	}
	public void calculateItem(float value, int index) {
		data.items.set(index, value);
	}
	
	
	

	
	private void updateOthers() {
		updateSplit();
		updateCustom();
	}
	
	private void updateSplit() {
		data.tipAmountEach = data.tipAmount / data.numberOfPeople;
		data.totalEach = data.total / data.numberOfPeople;
		
	}
	
	private void updateCustom() {
		float yoursum = getSumOfItems();
		float taxPercent = data.tax/data.bill;
		yoursum = yoursum + yoursum * taxPercent;
		data.tipAmountYour = yoursum * data.tipPercent;
		data.totalYour = yoursum + data.tipAmountYour;
		
		Log.d(TAG, "should we add an item? "+data.items.size() + " last: ");
		if (data.items.size() <= 0 || data.items.get(data.items.size()-1) != 0f) {
			Log.d(TAG, "Adding an item");
			data.items.add(Float.valueOf(0f));
		}
	}
	
	
	
	private void clearSimpleEnabled() {
		data.tipPercentEnabled = false;
		data.tipAmountEnabled = false;
		data.totalEnabled = false;
	}
	private void clearSplitEnabled() {
		data.tipAmountEachEnabled = false;
		data.totalEachEnabled = false;
		data.tipPercentEnabled = false;
	}
	private void clearCustomEnabled() {
		data.tipAmountYourEnabled = false;
		data.totalYourEnabled = false;
		data.tipPercentEnabled = false;
	}

	
	
	private float getSumOfItems() {
		float out = 0;
		for (float f : data.items) out += f;
		Log.d(TAG, "Sum of items: "+out);
		return out;
	}
	
	
	
	
	
	
	public String getCurrencySymbol() {
		return Currency.getInstance(Locale.getDefault()).getSymbol();
	}
	public String getPercentSymbol() {
		return "%";
	}



	
	
}
