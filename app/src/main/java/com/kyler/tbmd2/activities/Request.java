package com.kyler.tbmd2.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

public class Request extends ToolbarMenudrawer {

    private EditText recipient;
    private EditText subject;
    private EditText body;

    @SuppressLint("InflateParams")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_REQUEST;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.md_green_500));
            getWindow().setStatusBarColor(getResources().getColor(R.color.md_green_700));
        }

        recipient = (EditText) findViewById(R.id.recipient);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);

        Button sendBtn = (Button) findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                // after sending the email, clear the fields
                recipient.setText("MBQSniper@hotmail.com");
                subject.setText("Feature Request");
                body.setText("");
            }
        });
    }

    protected void sendEmail() {

        String[] recipients = {recipient.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // prompts email clients only
        email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email,
                    "Please choose an Email Client"));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Request.this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }
}