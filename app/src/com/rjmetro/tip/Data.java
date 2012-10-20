package com.rjmetro.tip;

import java.util.ArrayList;

public class Data {
	public final static float NO_VALUE = Float.MAX_VALUE;
	
	public float   bill = NO_VALUE;
	public boolean tipPercentEnabled = true;
	public float   tipPercent = 20f/100f;
	public boolean tipAmountEnabled = false;
	public float   tipAmount = NO_VALUE;
	public boolean totalEnabled = false;
	public float   total = NO_VALUE;
	
	public float   tipAmountEach = NO_VALUE;
	public boolean tipAmountEachEnabled = false;
	public float   totalEach = NO_VALUE;
	public boolean totalEachEnabled = false;
	
	public int numberOfPeople = 3;
	
	public float   tax = NO_VALUE;

	public ArrayList<Float> items = new ArrayList<Float>();
	public float   tipAmountYour = NO_VALUE;
	public boolean tipAmountYourEnabled = false;
	public float   totalYour = NO_VALUE;
	public boolean totalYourEnabled = false;

	
	public Data() {
		items.add(0f);
	}
	
	public boolean isValue(float value) {
		return (value != NO_VALUE);
	}
	
	
	
}
