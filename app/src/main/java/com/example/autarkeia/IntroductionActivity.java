package com.example.autarkeia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    Button butss, btncontinue;
    TextView textIntro;
    int page = 1;

    Intent intent;
    ScrollView sv;
    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "saved_te";
    public static final String APP_PREFERENCES_STATUS = "has entered";

    String status, hasCome = "no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        textIntro = (TextView) findViewById(R.id.textViewIntro);

        sv = (ScrollView)findViewById(R.id.scrolintroduction);

        butss = (Button) findViewById(R.id.buttonss);
        butss.setOnClickListener(this);

        intent = getIntent();
        hasCome = intent.getStringExtra("hasCome");

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonss:
                continueText();
                break;
        }
    }

    void continueText() {
        page++;

        sv.scrollTo(0, sv.getBottom());

        switch (page) {
            case 1:
                textIntro.setText(R.string.intro1);
                break;
            case 2:
                textIntro.setText(R.string.intro2);
                break;
            case 3:
                textIntro.setGravity(Gravity.LEFT);
                textIntro.setText(R.string.intro3);
                break;
            case 4:
                textIntro.setGravity(Gravity.CENTER);
                textIntro.setText(R.string.intro4);
                break;
            case 5:
                textIntro.setText(R.string.intro5);
                break;
            case 6:
                butss.setBackgroundResource(R.drawable.pplunge);
                textIntro.setText(R.string.intro6);
                break;
            case 7:
                NextActivity();
                break;
        }
    }

    void NextActivity() {

        if (hasCome.equals("yes")) {

            super.finish();

        } else {

            saveText();
            Intent i = new Intent(this, MainContents.class);
            startActivity(i);
            super.finish();
        }

    }


    void saveText() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_STATUS, "has");
        editor.apply();

    }
}
