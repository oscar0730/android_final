package com.example.v3_372.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private Button btnReturn;
    private EditText edtCountSet,edtTimeSet,edtAddressSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(btnOnReturn);
        edtCountSet = (EditText) findViewById(R.id.edtCountSet);
        edtTimeSet = (EditText) findViewById(R.id.edtTimeSet);
        edtAddressSet =(EditText) findViewById(R.id.edtAddressSet);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtCountSet.setText(String.valueOf(bundle.getInt("miCountSet")));
            edtTimeSet.setText(String.valueOf(bundle.getInt("miHourSet"))+":"+String.valueOf(bundle.getInt("miMinuteSet")));
            edtAddressSet.setText(bundle.getString("miAddressSet"));
        } else {
            Toast.makeText(this, "資料不能為空！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private View.OnClickListener btnOnReturn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}