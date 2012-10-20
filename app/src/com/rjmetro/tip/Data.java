package com.rjmetro.tip;

public class Data {
	public final static float NO_VALUE = Float.MAX_VALUE;
	
	public float   bill = NO_VALUE;
	public boolean tipPercentEnabled = false;
	public float   tipPercent = 20f/100f;
	public boolean tipAmountEnabled = false;
	public float   tipAmount = NO_VALUE;
	public boolean totalEnabled = false;
	public float   total = NO_VALUE;
	
	public float   tipAmountEach = NO_VALUE;
	public boolean tipAmountEachEnabled = false;
	public float   totalEach = NO_VALUE;
	public boolean totalEachEnabled = false;
	
	public int numberOfPeople;
	public boolean splitEvenly;
	
	public boolean isValue(float value) {
		return (value != NO_VALUE);
	}
	
}
