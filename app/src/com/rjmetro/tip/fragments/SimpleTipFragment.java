package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.R;

public class SimpleTipFragment extends TipFragment {
	
	public SimpleTipFragment(DataManager callback) {
		super(callback);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_tip, null);
	}


	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		// TODO Auto-generated method stub
		
	}
	
}
