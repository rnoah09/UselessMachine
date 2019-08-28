package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch uselessSwitch;
    private Button uselessButton;
    private int timesSwitched;

    private void wireWidgets() {
        uselessSwitch = findViewById(R.id.switch_useless_main);
        uselessButton = findViewById(R.id.button_refresh_main);
    }

    private void setListeners() {
        uselessSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    if(timesSwitched >= 30){
                        Toast.makeText(MainActivity.this, "yeet", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        new CountDownTimer((5000 - (timesSwitched * 1000)) + 600,100) {
                            @Override
                            public void onTick(long l) {
                                if(!uselessSwitch.isChecked()){
                                    cancel();
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
