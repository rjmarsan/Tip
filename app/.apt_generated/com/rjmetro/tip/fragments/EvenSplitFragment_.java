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

public final class EvenSplitFragment_
    extends EvenSplitFragment
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
        eachTotal = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.each_total_value));
        eachTipDollars = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.each_tipdollars_input));
        eachTipDollarsText = ((TextView) findViewById(com.rjmetro.tip.R.id.each_tipdollars_text));
        tipPercent = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.tippercent_input));
        bill = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.bill_input));
        numberPeople = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.people_input));
        setup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.split_tip, container, false);
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
