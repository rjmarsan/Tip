package com.rjmetro.tip;

public class Data {
	public final static float NO_VALUE = Float.MAX_VALUE;
	
	public float bill = NO_VALUE;
	public boolean tipPercentEnabled = true;
	public float tipPercent = 15f/100f;
	public boolean tipAmountEnabled = false;
	public float tipAmount = NO_VALUE;
	public boolean totalEnabled = false;
	public float total = NO_VALUE;
	
	public int numberOfPeople;
	public boolean splitEvenly;
	
	public boolean isValue(float value) {
		return (value != NO_VALUE);
	}
	
}
