//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.rjmetro.tip.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rjmetro.tip.R.layout;
import com.rjmetro.tip.views.HalfHintEditText;

public final class ComplicatedSplitFragment_
    extends ComplicatedSplitFragment
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
        itemsHolder = ((LinearLayout) findViewById(com.rjmetro.tip.R.id.itemized_holder));
        yourTipDollarsText = ((TextView) findViewById(com.rjmetro.tip.R.id.your_tipdollars_text));
        taxText = ((TextView) findViewById(com.rjmetro.tip.R.id.tax_text));
        tax = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.tax_input));
        yourTotal = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.your_total_value));
        tipPercent = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.tippercent_input));
        bill = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.bill_input));
        yourTipDollars = ((HalfHintEditText) findViewById(com.rjmetro.tip.R.id.your_tipdollars_input));
        setup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.custom_tip, container, false);
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
