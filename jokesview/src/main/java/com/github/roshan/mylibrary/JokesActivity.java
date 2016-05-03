package com.github.roshan.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JokesActivity extends AppCompatActivity {

    public static final String ARG_JOKES = "ARG_JOKES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        String jokes = getIntent().getStringExtra(ARG_JOKES);
        if (jokes != null) {
            ((TextView) findViewById(R.id.textViewJokes)).setText(jokes);
        }
    }
}
