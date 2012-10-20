package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.DataManager.DataListener;

public class EvenSplitFragment extends TipFragment implements DataListener {
	public EvenSplitFragment(DataManager callback) {
		super(callback);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText("EvenSplitFragment");
        return textView;
	}

	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		// TODO Auto-generated method stub
		
	}
	
	
}
