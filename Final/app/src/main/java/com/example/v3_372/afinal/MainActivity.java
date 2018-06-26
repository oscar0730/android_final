package com.example.v3_372.afinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button button1;
    private TextView text1;
    private TextView total;
    private EditText EdtAddress;
    private String[] s1 = { "美味蟹堡", "義式香腸堡", "蔬菜水果堡", "香蕉潛艇堡", "香烤雞肉堡" };
    private Integer[] price = {20,10,15,30,40};
    private String[] s2 = { "美味蟹堡 20", "義式香腸堡 10", "蔬菜水果堡 15", "香蕉潛艇堡 30", "香烤雞肉堡 40" };
    private boolean check[] = { false, false, false, false, false };
    private int money=0;
    private TimePicker TimePicker;
    private int hour=0,minute=0;
    private String address="";
    private Button btnResult;
    private Intent link = new Intent();
    private Bundle bundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);

        text1 = (TextView) findViewById(R.id.text1);
        total = (TextView) findViewById(R.id.total);
        TimePicker=(TimePicker)findViewById(R.id.timePicker);

        EdtAddress=(EditText)findViewById(R.id.address);
        btnResult = (Button) findViewById(R.id.btnShowResult);
        link.setClass(this, ResultActivity.class);
        btnResult.setOnClickListener(btnOnclick);



        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour=TimePicker.getCurrentHour();
                minute=TimePicker.getCurrentMinute();
                address=EdtAddress.getText().toString();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.str2)
                        .setMultiChoiceItems(
                                s2,
                                check,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface arg0,
                                                        int which, boolean isCheck) {
                                        if (isCheck) {
                                            check[which] = true;
                                        }
                                    }
                                })
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        String tmp = "";
                                        money = 0;
                                        for (int i = 0; i < check.length; i++) {
                                            if (check[i]) {
                                                tmp += s1[i] + "\n";
                                                money += price[i];
                                            }
                                        }
                                        text1.setText(MainActivity.this.getResources()
                                                .getString(R.string.str2)
                                                + "\n" + tmp);
                                        total.setText(MainActivity.this.getResources()
                                                .getString(R.string.str3)+money);
                                    }
                                })
                        .setNegativeButton("離開",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();

            }
        });
        btnResult.setOnClickListener(btnOnclick);
    }
    private View.OnClickListener btnOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btnShowResult:
                    hour=TimePicker.getCurrentHour();
                    minute=TimePicker.getCurrentMinute();
                    address=EdtAddress.getText().toString();
                    bundle.putInt("miCountSet",money);
                    bundle.putInt("miHourSet",hour);
                    bundle.putInt("miMinuteSet",minute);
                    bundle.putString("miAddressSet",address);
                    link.putExtras(bundle);
                    startActivity(link);
                    break;
            }
        }
    };
}
