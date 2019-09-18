package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch uselessSwitch;
    private Button refreshButton;
    private Button uselessButton;
    private ProgressBar progressBar1;
    private ProgressBar progressbar2;
    private TextView textViewTitle;
    private TextView textViewLoading;
    private TextView textViewOn;
    private TextView textViewOff;
    private int timesSwitched;
    private int timesClicked;

    private void wireWidgets() {
        uselessSwitch = findViewById(R.id.switch_useless_main);
        refreshButton = findViewById(R.id.button_refresh_main);
        uselessButton = findViewById(R.id.button_useless_main);
        progressBar1 = findViewById(R.id.progressbar1_refresh_main);
        progressbar2 = findViewById(R.id.progressbar2_refresh_main);
        textViewLoading = findViewById(R.id.textview_refresh_main);
        textViewTitle = findViewById(R.id.textview_title_main);
        textViewOn = findViewById(R.id.textview_on_main);
        textViewOff = findViewById(R.id.textview_off_main);
    }

    private void setListeners() {
        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                        new CountDownTimer(5000 / (timesSwitched + 1) + 300,100) {
                            @Override
                            public void onTick(long l) {
                                if(!uselessSwitch.isChecked()){
                                    cancel();
                                }
                                if(timesSwitched == 5){
                                    Toast.makeText(MainActivity.this, "LET ME SLEEP", Toast.LENGTH_SHORT).show();
                                }
                                if(timesSwitched == 7){
                                    Toast.makeText(MainActivity.this, "PLEASE STOP", Toast.LENGTH_SHORT).show();
                                }
                                if(timesSwitched == 10){
                                    Toast.makeText(MainActivity.this, "STOP", Toast.LENGTH_SHORT).show();
                                }
                                if(timesSwitched > 15)
                                {
                                finish();
                                }
                            }

                            @Override
                            public void onFinish() {
                                uselessSwitch.setChecked(false);
                            }
                        }.start();
                        timesSwitched++;

                }
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
                uselessSwitch.setVisibility(View.GONE);
                refreshButton.setVisibility(View.GONE);
                uselessButton.setVisibility(View.GONE);
                textViewTitle.setVisibility(View.GONE);
                textViewOn.setVisibility(View.GONE);
                textViewOff.setVisibility(View.GONE);

                textViewLoading.setVisibility(View.VISIBLE);
                progressBar1.setVisibility(View.VISIBLE);

                new CountDownTimer(12000,100) {
                    @Override
                    public void onTick(long l) {
                            progressBar1.incrementProgressBy(1);
                            textViewLoading.setText("Loading Refresh... " + progressBar1.getProgress() + "/100");

                    }

                    @Override
                    public void onFinish() {

                        progressBar1.setVisibility(View.GONE);
                        textViewLoading.setVisibility((View.GONE));
                        progressbar2.setVisibility(View.VISIBLE);

                    }
                }.start();
                new CountDownTimer(20000,100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });
        uselessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timesClicked++;
                Toast.makeText(MainActivity.this, "I don't do anything.", Toast.LENGTH_SHORT).show();
                if(timesClicked > 20){
                    finish();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }
}
