package com.kyler.tbmd2.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kyler.tbmd2.Config;
import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyler on 1/26/2015.
 */
public class VoiceCommands extends ToolbarMenudrawer {
    protected static final String test = "test";
    private static final int REQUEST_CODE = 1234;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_VOICE_COMMANDS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_commands);

        Button speakButton = (Button) findViewById(R.id.speakButton);

        // Disable button if no recognition service is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            speakButton.setEnabled(true);
            speakButton.setText("Recognizer not present");
        }
    }

    /**
     * Handle the action of the button being clicked
     */
    public void speakButtonClicked(View v) {
        startVoiceRecognitionActivity();
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition test...");
        startActivityForResult(intent, REQUEST_CODE);

    }

    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);

            if (Config.IS_DEV_BUILD && matches.contains("hello")) {
                Toast.makeText(this, "Hi!", Toast.LENGTH_LONG).show();

                /* So what we're doing above is basically checking to first see if this is a developer build
                 * (in the config class, there is a boolean that states that it is, and we're seeing if the
                 * word *only* contains "hello", and so if both of those end up being true, we display a Toast
                 * saying "Hi!". Nice and simple, but you get the point. */

             }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

