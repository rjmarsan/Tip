package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.DataManager.DataListener;
import com.rjmetro.tip.views.HalfHintEditText;
import com.rjmetro.tip.views.HalfHintEditText.PostEditListener;

public abstract class TipFragment extends Fragment implements DataListener {
	public final static String TAG = "TipFragment";
	DataManager callback;
	
	public void setCallback(DataManager callback) {
		this.callback = callback;
	}
		
	@Override
	public void onStart() {
		super.onStart();
		callback.addListener(this);
		callback.notifyChange();
	}

	@Override
	public abstract void updatedData(Data newdata, DataManager dataman);
	
	@Override
	public void onStop() {
		super.onStop();
		callback.removeListener(this);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
//		// TODO Auto-generated method stub
//		super.onSaveInstanceState(outState);
		//Prevents an ugly crash. why? no idea.
	}
	
	
	protected void setInputText(HalfHintEditText edit, String text, boolean enabled) {
		if (edit == null) return;
		if (edit.isFocused() && edit.getText().toString().equals("")) {
			Log.d(TAG, "blank input text for "+edit.getId()+". fixing: "+text);
			edit.setUnformattedText(text, true);
			edit.selectAll();
		} if (edit.isFocused()) {
			Log.d(TAG, "isfocused input text for "+edit.getId()+". fixing: "+text);
			//edit.setUnformattedText(edit.getText().toString(), true);
		} else if (enabled) {
			Log.d(TAG, "enabled text for "+edit.getId()+". fixing: "+text);
			edit.setUnformattedText(text, true);
		} else {
			Log.d(TAG, "disabled text for "+edit.getId()+". fixing: "+text);
			edit.setUnformattedText(text, false);
		}
	}

	
	public abstract class SmartEditListener implements PostEditListener {
		View target;
		public SmartEditListener(View target) {
			this.target = target;
		}
		@Override
		public void newText(String text) {
			if (target.isFocused()) {
				try {
					runEvent(text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		public abstract void runEvent(String text) throws Exception;

	}

	
	
}
