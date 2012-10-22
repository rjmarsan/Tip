package com.rjmetro.tip;

import android.app.AlertDialog;
import android.content.Context;

public class About {
	
	public static void showAbout(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.about_title);
        builder.setMessage(R.string.about_message);
        final AlertDialog alert = builder.create();
        alert.show();
	}

}
