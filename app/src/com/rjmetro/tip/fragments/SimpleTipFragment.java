package com.rjmetro.tip.fragments;

import java.text.ParseException;

import android.util.Log;
import android.view.View;

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
	HalfHintEditText tipPercent;
	
	@ViewById(R.id.tipdollars_input)
	HalfHintEditText tipDollars;
	
	@ViewById(R.id.total_value)
	HalfHintEditText total;
	
	
	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		Log.d(TAG, "got Updating data: "+newdata);
		setInputText(bill, dataman.formatMoney(newdata.bill), true);
		setInputText(tipPercent, dataman.formatPercent(newdata.tipPercent), newdata.tipPercentEnabled);
		setInputText(tipDollars, dataman.formatMoney(newdata.tipAmount), newdata.tipAmountEnabled);
		setInputText(total, dataman.formatMoney(newdata.total), newdata.totalEnabled);
	}
	
	
	@AfterViews
	public void setup() {
		bill.setPermanentText(callback.getCurrencySymbol());
		bill.setPermanentTextPre(true);
		bill.setPostEditListener(new SmartEditListener(bill) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateBill(text);				
			}
		});
		tipPercent.setPermanentText(callback.getPercentSymbol());
		tipPercent.setPermanentTextPre(false);
		tipPercent.setPostEditListener(new SmartEditListener(tipPercent) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTipPercentage(text);				
			}
		});
		tipDollars.setPermanentText(callback.getCurrencySymbol());
		tipDollars.setPermanentTextPre(true);
		tipDollars.setPostEditListener(new SmartEditListener(tipDollars) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTipDollars(text);				
			}
		});
		total.setPermanentText(callback.getCurrencySymbol());
		total.setPermanentTextPre(true);
		total.setPostEditListener(new SmartEditListener(total) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTotal(text);				
			}
		});
	}
	
	
}
