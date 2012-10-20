package com.rjmetro.tip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjmetro.tip.DataManager.DataListener;

public abstract class TipFragment extends Fragment implements DataListener {
	DataManager callback;
	
	public TipFragment(DataManager callback) {
		this.callback = callback;
	}
		
	@Override
	public void onStart() {
		super.onStart();
		callback.addListener(this);
	}

	@Override
	public abstract void updatedData(Data newdata, DataManager dataman);
	
	@Override
	public void onStop() {
		super.onStop();
		callback.removeListener(this);
	}
	
}
