package com.example.hw5;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    EditText temp;
    RadioButton toC;
    RadioButton toF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //View
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = findViewById(R.id.editTextTemperature);
        toC = findViewById(R.id.radioButtonF2C);
        toF = findViewById(R.id.radioButtonC2F);
    }
    //onClick for Button
    public void convert(View v){
        //double value temp
        double value =  Double.valueOf(temp.getText().toString());
        if(toC.isChecked())
            value = Temperature.fahrenheitToCelsius(value);
        else
            value = Temperature.celsiusToFahrenheit(value);
        temp.setText(Double.toString(value));

    }


}
