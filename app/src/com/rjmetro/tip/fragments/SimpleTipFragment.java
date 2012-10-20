package com.rjmetro.tip.fragments;

import java.text.ParseException;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.R;
import com.rjmetro.tip.views.HalfHintEditText;
import com.rjmetro.tip.views.HalfHintEditText.PostEditListener;

@EFragment(R.layout.simple_tip)
public class SimpleTipFragment extends TipFragment {
	public static final String TAG = "SimpleTipFragment";
	
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
		Log.d(TAG, "Updating data: "+newdata);
		setInputText(bill, dataman.formatMoney(newdata.tipPercent), true);
		setInputText(tipper, dataman.formatPercent(newdata.tipPercent), newdata.tipPercentEnabled);
		setInputText(tipdol, dataman.formatMoney(newdata.tipAmount), newdata.tipAmountEnabled);
		setInputText(total, dataman.formatMoney(newdata.total), newdata.totalEnabled);
	}
	
	private void setInputText(HalfHintEditText edit, String text, boolean enabled) {
		if (edit.isFocused()) {
			edit.setUnformattedText(edit.getText().toString(), true);
		} else if (enabled) {
			edit.setUnformattedText(text, true);
		} else {
			edit.setUnformattedText(text, false);
		}
	}
	
	@AfterViews
	public void setup() {
		bill.setPostEditListener(new PostEditListener() {
			@Override
			public void newText(String text) {
				try {
					callback.updateBill(text);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		callback.notifyChange();
	}
	
	
	public abstract class ValueWatcher implements TextWatcher {		
		@Override
		public void afterTextChanged(Editable s) {
			changed(s.toString());
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		public abstract void changed(String text);
	}
	
	
}
