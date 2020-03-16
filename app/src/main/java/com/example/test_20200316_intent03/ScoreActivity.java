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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    private Context context;
    private EditText editTextName, editTextID, editTextMath, editTextEng;
    private TextView textViewResult;
    private Button buttonCancel, buttonOK;
    private final int ScoreReturn = 10;
    private String ScoreResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        context = this;

        editTextName = (EditText) findViewById(R.id.editText_nameScore);

        editTextID = (EditText) findViewById(R.id.editText_idScore);

        editTextMath = (EditText) findViewById(R.id.editText_math);

        editTextEng = (EditText) findViewById(R.id.editText_english);

        textViewResult = (TextView) findViewById(R.id.textView_resultScore);

        textViewResult.setText("");

        buttonCancel = (Button) findViewById(R.id.button_cancelScore);

        buttonOK = (Button) findViewById(R.id.button_okScore);

        buttonCancel.setOnClickListener(new MyButton());

        buttonOK.setOnClickListener(new MyButton());

    } // onCreate()

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button_cancelScore:
                    editTextName.setText("");
                    editTextID.setText("");
                    editTextMath.setText("");
                    editTextEng.setText("");
                    textViewResult.setText("");
                    break;

                case R.id.button_okScore:
                    String name = editTextName.getText().toString();
                    String id = editTextID.getText().toString();
                    String math = editTextMath.getText().toString();
                    String english = editTextEng.getText().toString();
                    Log.d("main", "math=" + math);
                    Log.d("main","english=" + english);

                    // 如果 name 的長度或 id 的長度等於 0，表示沒有輸入 name 或 id
                    if(name.length() == 0 || id.length() == 0) {
                        Toast.makeText(context, "Please input your name and id.", Toast.LENGTH_SHORT).show();
                    }else {
                        if(math.length() == 0) {
                            Toast.makeText(context, "Please input math score.", Toast.LENGTH_SHORT).show();
                            math = "0"; // math跟english是要做計算的變數，因為接收的是String，所以要給一個String的初始值 "0"
                        }

                        if(english.length() == 0) {
                            Toast.makeText(context, "Please input English score.", Toast.LENGTH_SHORT).show();
                            english = "0";
                        }

                        int sum = Integer.parseInt(math) + Integer.parseInt(english);

                        Log.d("main", "sum=" + sum);

                        textViewResult.setText("Name :" + name + "\n");

                        textViewResult.append("ID :" + id +"\n");

                        textViewResult.append(("math = " + math + ", english = " + english + "\n"));

                        textViewResult.append("The sum = " + sum);

                        Intent intent = new Intent();
                        ScoreResult= textViewResult.getText().toString();
                        intent.putExtra("scoreresult", ScoreResult);
                        setResult(ScoreReturn, intent);

                        // 呼叫 dialog_1 ()方法
                        showDialog_1();


                    }
                    break;

            }
        }

    }

    private void showDialog_1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Score information");
        builder.setMessage(ScoreResult);

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
