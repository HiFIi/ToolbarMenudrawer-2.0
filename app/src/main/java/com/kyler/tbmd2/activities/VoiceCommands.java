package com.kyler.tbmd2.activities;

import android.os.Bundle;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

/**
 * Created by Kyler on 1/26/2015.
 */
public class VoiceCommands extends ToolbarMenudrawer {
    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_VOICE_COMMANDS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_commands);
    }
}

