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
		setSaveEnabled(false);
		setupCustomAttributes(attrs);
		styleStringAndSet(formatString(getText().toString()), true);
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
					if (listener != null) listener.newText(text.replace(permanentText, ""));
				} else {
					styleStringAndSet(target, true);
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
	
	
	private SpannableString styleString(String text, boolean highlight) {
		if (textIsEmpty(text)) {
			setText("");
			return new SpannableString("");
		}
		SpannableString s = new SpannableString(text);
		int permlen = permanentText.length();
		if (highlight == false) {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else if (permTextInFront) {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), 0, permlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} else {
			s.setSpan(new ForegroundColorSpan(getHintTextColors().getDefaultColor()), text.length()-permlen, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return s;
	}
	private void styleStringAndSet(String target, boolean highlight) {
		SpannableString s = styleString(target, highlight);
		setText(s);
	}
	
	public void setUnformattedText(String text, boolean highlight) {
		if (textIsEmpty(text)) {
			setText("");
			return;
		}
		String target = formatString(text);
		if (target.equals(getText().toString()) == false)
			styleStringAndSet(target, highlight);
	}

	public void setPermanentText(String text) {
		this.permanentText = text;
		this.setUnformattedText("", false);
	}
	
	public void setPermanentTextPre(boolean infront) {
		this.permTextInFront = infront;
		this.setUnformattedText("", false);
	}
	
	private boolean textIsEmpty(String text) {
		return text.trim().equals("");
	}

	
}
