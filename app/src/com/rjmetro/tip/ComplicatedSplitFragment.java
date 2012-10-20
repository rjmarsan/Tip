package com.rjmetro.tip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ComplicatedSplitFragment extends TipFragment {
	public ComplicatedSplitFragment(DataManager callback) {
		super(callback);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText("ComplicatedSplitFragment");
        return textView;
	}

	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		// TODO Auto-generated method stub
		
	}
	
}
