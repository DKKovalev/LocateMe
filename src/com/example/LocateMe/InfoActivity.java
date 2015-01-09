package com.example.LocateMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class InfoActivity extends Activity {

    TextView titleView;
    TextView descriptionView;
    ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        titleView = (TextView)findViewById(R.id.title_text_view);
        descriptionView = (TextView)findViewById(R.id.description_text_view);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        titleView.setText(title);
        descriptionView.setText(description);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
