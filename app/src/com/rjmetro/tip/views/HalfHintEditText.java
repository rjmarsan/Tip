package com.rjmetro.tip.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.rjmetro.tip.R;

public class HalfHintEditText extends EditText {
	public static final String TAG = "HalfHintEditText";
	String permanentText = "";
	boolean permTextInFront = false;
	
	public HalfHintEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup(attrs);
	}
	public HalfHintEditText(Context context, AttributeSet attrs, int whatever) {
		super(context, attrs, whatever);
		setup(attrs);
	}

	
	private void setupCustomAttributes(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.HalfHintEditText);
		permanentText = getString(a, R.styleable.HalfHintEditText_permanentHint);
		permTextInFront = a.getBoolean(R.styleable.HalfHintEditText_permanentHintPre, permTextInFront);
		Log.d(TAG, "Permanent Text: "+permanentText);
	}
	
	private void setup(AttributeSet attrs) {
		setupCustomAttributes(attrs);
		styleStringAndSet(formatString(getText().toString()));
		addTextWatcher();
	}
	
	private String getString(TypedArray a, int index) {
		String s =  a.getString(index);
		if (s != null)
			return s;
		else
			return "";
	}
	
	
	private void addTextWatcher() {
		addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String text = s.toString();
				String target = formatString(text);
				Log.d(TAG, String.format("afterTextChanged: pre:%s, post:%s", text, target));
				if (text.equals(target)) {
					//cool. done.
				} else {
					styleStringAndSet(target);
					if (permTextInFront)
						setSelection(target.length(), target.length());
					else
						setSelection(target.length()-permanentText.length(), target.length()-permanentText.length());
				}
			}
		});
//		this.setOnFocusChangeListener(new OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if (hasFocus) {
//					String target = getText().toString();
//					if (target.equals("") || target.length() < permanentText.length()) {
//						Log.d(TAG, "No selection! "+target);
//						return;
//					}
//					Log.d(TAG, "Selection!!! ");
//					if (permTextInFront)
//						setSelection(permanentText.length(), target.length());
//					else
//						setSelection(0, target.length()-permanentText.length());
//				}
//			}
//		}); 
		this.setSelectAllOnFocus(true);
	}
	
	private String formatString(String text) {
		if (text.equals("")) return text;
		String target = text.replace(permanentText, "");
		if (permTextInFront)
			target = permanentText + target;
		else
			target = target + permanentText;
		return target;
	}
	
	private void styleStringAndSet(String text) {
		if (text.equals("")) {
			setText("");
			return;
		}
		SpannableString s = new SpannableString(text);
		int permlen = permanentText.length();
		if (permTextInFront) {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), 0, permlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), text.length()-permlen, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		setText(s);
	}
	
	
}
