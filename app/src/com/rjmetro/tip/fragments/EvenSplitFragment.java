package com.rjmetro.tip.fragments;

import android.util.Log;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.R;
import com.rjmetro.tip.views.HalfHintEditText;

@EFragment(R.layout.split_tip)
public class EvenSplitFragment extends TipFragment {
	public static final String TAG = "EvenSplitFragment";
	
	@ViewById(R.id.bill_input)
	HalfHintEditText bill;
	
	@ViewById(R.id.tippercent_input)
	HalfHintEditText tipPercent;

	@ViewById(R.id.people_input)
	HalfHintEditText numberPeople;

	@ViewById(R.id.each_tipdollars_input)
	HalfHintEditText eachTipDollars;
	
	@ViewById(R.id.each_tipdollars_text)
	TextView eachTipDollarsText;
	
	@ViewById(R.id.each_total_value)
	HalfHintEditText eachTotal;
	
	
	@Override
	public void updatedData(Data newdata, DataManager dataman, boolean forced) {
		Log.d(TAG, "got Updating data: "+newdata);
		setInputText(bill, dataman.formatMoney(newdata.bill), true, forced);
		setInputText(tipPercent, dataman.formatPercent(newdata.tipPercent), newdata.tipPercentEnabled, forced);
		setInputText(numberPeople, dataman.formatInteger(newdata.numberOfPeople), true, forced);
		setInputText(eachTipDollars, dataman.formatMoney(newdata.tipAmountEach), newdata.tipAmountEachEnabled, forced);
		setInputText(eachTotal, dataman.formatMoney(newdata.totalEach), newdata.totalEachEnabled, forced);
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
		numberPeople.setPermanentText("");
		numberPeople.setPermanentTextPre(true);
		numberPeople.setPostEditListener(new SmartEditListener(numberPeople) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateNumber(text);				
			}
		});
		eachTipDollarsText.setText(getResources().getString(R.string.each_tip_dollar, callback.getCurrencySymbol().trim()));
		eachTipDollars.setHint(getResources().getString(R.string.each_tip_dollar_hint, callback.getCurrencySymbol().trim()));
		eachTipDollars.setPermanentText(callback.getCurrencySymbol());
		eachTipDollars.setPermanentTextPre(true);
		eachTipDollars.setPostEditListener(new SmartEditListener(eachTipDollars) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTipDollarsEach(text);				
			}
		});
		eachTotal.setPermanentText(callback.getCurrencySymbol());
		eachTotal.setPermanentTextPre(true);
		eachTotal.setPostEditListener(new SmartEditListener(eachTotal) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTotalEach(text);				
			}
		});
	}
	
	
	
	
}
