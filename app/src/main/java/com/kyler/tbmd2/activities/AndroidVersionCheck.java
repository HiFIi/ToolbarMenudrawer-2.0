package com.kyler.tbmd2.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kyler.tbmd2.R;

/**
 * Created by Kyler on 2/8/2015.
 */
public class AndroidVersionCheck extends Activity {
    private static TextView androidVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.version_checker);

        androidVersion = (TextView) findViewById(R.id.androidVersion);
        int currentVer = android.os.Build.VERSION.SDK_INT;
        //    String androidVersionDec = String.valueOf(currentVer);

        if (currentVer == 21) {
            androidVersion.setText(R.string.android_rel_lollipop);
        } else if (currentVer == 19) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        } else if (currentVer == 18) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        } else if (currentVer == 17) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        } else if (currentVer == 16) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        } else if (currentVer == 15) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        } else if (currentVer == 14) {
            androidVersion.setText(R.string.android_rel_kitkat);
            Toast.makeText(this, "Hang tight! I'm working on it.", Toast.LENGTH_LONG).show();
        }
    }
}
