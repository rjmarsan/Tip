package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.R;
import com.rjmetro.tip.views.HalfHintEditText;

@EFragment(R.layout.simple_tip)
public class SimpleTipFragment extends TipFragment {
	@ViewById(R.id.bill_input)
	HalfHintEditText bill;
	
	@ViewById(R.id.tippercent_input)
	HalfHintEditText tipper;
	
	@ViewById(R.id.tipdollars_input)
	HalfHintEditText tipdol;
	
	@ViewById(R.id.total_value)
	HalfHintEditText total;
	
	
	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		// TODO Auto-generated method stub
		
	}
	
}
