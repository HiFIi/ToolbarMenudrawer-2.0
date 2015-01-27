package com.kyler.tbmd2.activities;

import android.os.Bundle;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

/**
 * Created by Kyler on 1/27/2015.
 */
public class Palette extends ToolbarMenudrawer {

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_PALETTE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palette);
    }
}
