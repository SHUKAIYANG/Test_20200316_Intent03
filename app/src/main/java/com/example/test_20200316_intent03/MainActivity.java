package com.example.test_20200316_intent03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonData, buttonScore;
    private TextView textViewReturn;
    private Context context;
    private final int DataRequestCode = 100;
    private final int ScoreRequestCode = 200;
    private final int ScoreReturn = 10;
    private final int DataReturn = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        buttonData = (Button) findViewById(R.id.button_data);
        buttonScore = (Button) findViewById(R.id.button_score);

        textViewReturn = (TextView) findViewById(R.id.textView_return);
        textViewReturn.setText("");

        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DataActivity.class);
                startActivityForResult(intent, DataRequestCode);
            }
        });


        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScoreActivity.class);
                startActivityForResult(intent, ScoreRequestCode);
            }
        });

    } // onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ScoreRequestCode) {
            if(resultCode == ScoreReturn ) {
                String ScoreResult = data.getStringExtra("scoreresult");
                textViewReturn.setText(ScoreResult);
            }

        }else if (requestCode == DataRequestCode) {
            if(resultCode == DataReturn) {
                String DataResult = data.getStringExtra("dataresult");
                textViewReturn.setText(DataResult);
            }
        }
    }

} // class mainActivity
