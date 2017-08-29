package com.claudiodegio.timenavigator.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_screen1)
                .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {
            case R.id.bt_screen1:

                intent = new Intent(this, Screen1Activity.class);
                break;

        }

        if (intent != null) {
            startActivity(intent);
        }

    }
}
