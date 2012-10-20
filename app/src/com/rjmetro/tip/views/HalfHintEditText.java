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
	public static interface PostEditListener {
		public void newText(String text);
	}
	
	public static final String TAG = "HalfHintEditText";
	String permanentText = "";
	boolean permTextInFront = false;
	PostEditListener listener;
	
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
					if (listener != null) listener.newText(text);
				} else {
					styleStringAndSet(target);
					if (getText().toString().length() > permanentText.length()) {
						if (permTextInFront)
							setSelection(target.length(), target.length());
						else
							setSelection(target.length()-permanentText.length(), target.length()-permanentText.length());
					}
				}
			}
		});
		this.setSelectAllOnFocus(true);
	}
	
	public void setPostEditListener(PostEditListener listener) {
		this.listener = listener;
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
	
	
	private SpannableString styleString(String text) {
		if (text.equals("")) {
			setText("");
			return new SpannableString("");
		}
		SpannableString s = new SpannableString(text);
		int permlen = permanentText.length();
		if (permTextInFront) {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), 0, permlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), text.length()-permlen, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return s;
	}
	private void styleStringAndSet(String target) {
		SpannableString s = styleString(target);
		setText(s);
	}
	
	
	public void setUnformattedText(String text) {
		if (text.equals("")) {
			setText("");
			return;
		}
		String target = formatString(text);
		if (target.equals(getText().toString()) == false)
			styleStringAndSet(target);
	}
	public void setUnformattedHint(String text) {
		String target = formatString(text);
		setHint(target);
	}


	
}
