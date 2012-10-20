//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rjmetro.tip.R.layout;
import com.rjmetro.tip.views.HalfHintEditText;

public final class SimpleTipFragment_
    extends SimpleTipFragment
{

    private View contentView_;

    private void init_() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_();
        super.onCreate(savedInstanceState);
    }

    private void afterSetContentView_() {
        tipDollars = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.tipdollars_input));
        tipPercent = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.tippercent_input));
        tipDollarsText = ((TextView) findViewById(com.rjmetro.tip.R.id.tipdollars_text));
        bill = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.bill_input));
        total = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.total_value));
        setup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.simple_tip, container, false);
        }
        afterSetContentView_();
        return contentView_;
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

}
