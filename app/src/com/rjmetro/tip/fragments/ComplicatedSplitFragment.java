package com.rjmetro.tip.fragments;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
	public void updatedData(Data newdata, DataManager dataman, boolean forced) {
		Log.d(TAG, "COMPLICATED got Updating data: "+newdata);
		setInputText(bill, dataman.formatMoney(newdata.bill), true, forced);
		setInputText(tax, dataman.formatMoney(newdata.tax), true, forced);		
		setInputText(tipPercent, dataman.formatPercent(newdata.tipPercent), newdata.tipPercentEnabled, forced);
		setInputText(yourTipDollars, dataman.formatMoney(newdata.tipAmountYour), newdata.tipAmountYourEnabled, forced);
		setInputText(yourTotal, dataman.formatMoney(newdata.totalYour), newdata.totalYourEnabled, forced);
		
		Log.d(TAG, "COMPLICATED childcount: "+itemsHolder.getChildCount()+ " items: "+newdata.items.size());
		while (itemsHolder.getChildCount() < newdata.items.size()) {
			Log.d(TAG, "Adding new row!!!");
			View v = LayoutInflater.from(getActivity()).inflate(R.layout.itemized_row, itemsHolder, false);
			setupItemizedRow(v, itemsHolder.getChildCount(), newdata.items.get(itemsHolder.getChildCount()));
			itemsHolder.addView(v);
		}
		while (itemsHolder.getChildCount() > newdata.items.size()) {
			itemsHolder.removeViewAt(itemsHolder.getChildCount()-1);
		}
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
		tax.setPermanentText(callback.getCurrencySymbol());
		tax.setPermanentTextPre(true);
		tax.setPostEditListener(new SmartEditListener(tax) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTax(text);				
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
		yourTipDollars.setPermanentText(callback.getCurrencySymbol());
		yourTipDollars.setPermanentTextPre(true);
		yourTipDollars.setPostEditListener(new SmartEditListener(yourTipDollars) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTipDollarsYour(text);				
			}
		});
		yourTotal.setPermanentText(callback.getCurrencySymbol());
		yourTotal.setPermanentTextPre(true);
		yourTotal.setPostEditListener(new SmartEditListener(yourTotal) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateTotalYour(text);				
			}
		});
	}
	
	public void setupItemizedRow(View row, final int index, final float value) {
		TextView title = (TextView)row.findViewById(R.id.item_n_text);
		title.setText(getActivity().getResources().getString(R.string.item_n, (index+1) ));
		
		HalfHintEditText item = (HalfHintEditText)row.findViewById(R.id.item_n_input);
		item.setPermanentText(callback.getCurrencySymbol());
		item.setPermanentTextPre(true);
		item.setPostEditListener(new SmartEditListener(item) {
			@Override
			public void runEvent(String text) throws Exception {
				callback.updateItem(text, index);				
			}
		});
		if (value != 0f) item.setUnformattedText(callback.formatMoney(value), false);
	}
	
	
	
	
}
