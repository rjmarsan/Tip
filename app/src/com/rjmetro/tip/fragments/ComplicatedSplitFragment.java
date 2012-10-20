package com.rjmetro.tip.fragments;

import android.util.Log;
import android.widget.LinearLayout;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.rjmetro.tip.Data;
import com.rjmetro.tip.DataManager;
import com.rjmetro.tip.R;
import com.rjmetro.tip.views.HalfHintEditText;

@EFragment(R.layout.custom_tip)
public class ComplicatedSplitFragment extends TipFragment {
	public static final String TAG = "EvenSplitFragment";
	
	@ViewById(R.id.bill_input)
	HalfHintEditText bill;
	
	@ViewById(R.id.tax_input)
	HalfHintEditText tax;
	
	@ViewById(R.id.tippercent_input)
	HalfHintEditText tipPercent;

	@ViewById(R.id.itemized_holder)
	LinearLayout itemsHolder;
	
	@ViewById(R.id.your_tipdollars_input)
	HalfHintEditText yourTipDollars;
	
	@ViewById(R.id.your_total_value)
	HalfHintEditText yourTotal;
	
	
	@Override
	public void updatedData(Data newdata, DataManager dataman) {
		Log.d(TAG, "got Updating data: "+newdata);
		setInputText(bill, dataman.formatMoney(newdata.bill), true);
		setInputText(tax, dataman.formatMoney(newdata.tax), true);		
		setInputText(tipPercent, dataman.formatPercent(newdata.tipPercent), newdata.tipPercentEnabled);
		setInputText(yourTipDollars, dataman.formatMoney(newdata.tipAmountYour), newdata.tipAmountYourEnabled);
		setInputText(yourTotal, dataman.formatMoney(newdata.totalYour), newdata.totalYourEnabled);
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
//		numberPeople.setPermanentText("");
//		numberPeople.setPermanentTextPre(true);
//		numberPeople.setPostEditListener(new SmartEditListener(numberPeople) {
//			@Override
//			public void runEvent(String text) throws Exception {
//				callback.updateNumber(text);				
//			}
//		});
//		eachTipDollars.setPermanentText(callback.getCurrencySymbol());
//		eachTipDollars.setPermanentTextPre(true);
//		eachTipDollars.setPostEditListener(new SmartEditListener(eachTipDollars) {
//			@Override
//			public void runEvent(String text) throws Exception {
//				callback.updateTipDollarsEach(text);				
//			}
//		});
//		eachTotal.setPermanentText(callback.getCurrencySymbol());
//		eachTotal.setPermanentTextPre(true);
//		eachTotal.setPostEditListener(new SmartEditListener(eachTotal) {
//			@Override
//			public void runEvent(String text) throws Exception {
//				callback.updateTotalEach(text);				
//			}
//		});
	}
	
	
	
	
}
