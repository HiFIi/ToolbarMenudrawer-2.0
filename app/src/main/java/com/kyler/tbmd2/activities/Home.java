package com.kyler.tbmd2.activities;

import android.os.Bundle;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

/**
 * Created by kyler on 1/19/15.
 */
public class Home extends ToolbarMenudrawer {
    //    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_HOME;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

   /*     if (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(this, "Awkward :(", Toast.LENGTH_LONG).show();
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(this, "Yay!", Toast.LENGTH_LONG).show();
        } */
    }
}
