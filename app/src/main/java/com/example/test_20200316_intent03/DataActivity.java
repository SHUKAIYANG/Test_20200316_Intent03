package com.example.test_20200316_intent03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    private static final String TAG = "main"; // main 代表主程式的意思

    private EditText editTextName, editTextId;
    private TextView textViewResult;
    private RadioGroup radioGroupSex;
    private boolean maleFlag, femaleFlag;
    private Context context;
    private CheckBox checkBoxSport, checkBoxReading, checkBoxPainting;
    private boolean sportFlag, readingFlag, paintingFlag;
    private Button buttonCancle, buttonOK;
    private final int DataReturn = 20;
    private String DataResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        context = this;

        editTextName = (EditText) findViewById(R.id.editText_nameData);
        editTextId = (EditText) findViewById(R.id.editText_idData);

        textViewResult = (TextView) findViewById(R.id.textView_resultData);
        textViewResult.setText("");


        maleFlag = false;
        femaleFlag = false;

        radioGroupSex = (RadioGroup) findViewById(R.id.radioGroup_id);

        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_male:
                        Log.d(TAG, "male");
                        maleFlag = true;
                        femaleFlag = false;
                        break;

                    case R.id.radioButton_female:
                        Log.d(TAG, "female");
                        maleFlag = false;
                        femaleFlag = true;
                        break;
                }
            }
        });


        sportFlag = false;
        readingFlag = false;
        paintingFlag = false;

        checkBoxSport = (CheckBox) findViewById(R.id.checkBox_sport);
        checkBoxSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                sportFlag = isChecked;
                Log.d(TAG,"sportFlag = " + sportFlag);
            }
        });


        checkBoxReading = (CheckBox) findViewById(R.id.checkBox_reading);
        checkBoxReading.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                readingFlag = isChecked;
                Log.d(TAG, "readingFlag = " + readingFlag);
            }
        });


        checkBoxPainting = (CheckBox) findViewById(R.id.checkBox_painting);
        checkBoxPainting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                paintingFlag = isChecked;
                Log.d(TAG,"paintingFlag = " + paintingFlag);
            }
        });


        buttonCancle = (Button) findViewById(R.id.button_cancelData);
        buttonOK = (Button) findViewById(R.id.button_okData);

        buttonOK.setOnClickListener(new MyButton());
        buttonCancle.setOnClickListener(new MyButton());

    } // onCreate

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button_cancelData:

                    // 按 cancel 把全部東西都清掉
                    editTextName.setText(""); // 清除
                    editTextId.setText("");   // 清除
                    radioGroupSex.clearCheck();  // 清除
                    checkBoxSport.setChecked(false);   // 清除
                    checkBoxReading.setChecked(false); // 清除
                    checkBoxReading.setChecked(false); // 清除
                    maleFlag = false;
                    femaleFlag = false;
                    sportFlag = false;
                    readingFlag = false;
                    paintingFlag = false;
                    textViewResult.setText("");
                    break;

                case R.id.button_okData:

                    if(editTextName.length() == 0 || editTextId.length() == 0){
                        Toast.makeText(context, "Please input name and id.", Toast.LENGTH_SHORT).show();

                    }else {

                        String data = "Name :" + editTextName.getText().toString() + ",ID:" + editTextId.getText().toString() + "\n";
                        textViewResult.setText(data);

                        if(maleFlag) {
                            textViewResult.append("Sex : Male \n");
                        }else if(femaleFlag) {
                            textViewResult.append("Sex : Female \n");
                        }else {
                            Toast.makeText(context, "Please select sex.", Toast.LENGTH_SHORT).show();
                        }

                        textViewResult.append("Hobby :");

                        if(sportFlag)
                            textViewResult.append("Sport ,");

                        if(readingFlag)
                            textViewResult.append("Reading ,");

                        if(paintingFlag)
                            textViewResult.append("Painting");

                        Intent intent = new Intent();
                        DataResult = textViewResult.getText().toString();
                        intent.putExtra("dataresult", DataResult);
                        setResult(DataReturn, intent);

                        // 呼叫 showDialog_1()
                        showDialog_1();
                    }

                    break;


            }
        }
    }

    private void showDialog_1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Data information");
        builder.setMessage(DataResult);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

} // class mainActivity
