package com.example.hwfive;
import android.content.Context;


public class Temperature {
    private  double celsius;
    private double fahrenheit;

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double getFarenheit() {
        return fahrenheit;
    }

    public void setFarenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public static double celsiusToFahrenheit(double celsius){
        return 32 + celsius * 9/5;
    }
    public static double fahrenheitToCelsius(double fahrenheit){
        return ( fahrenheit - 32)*5/9;

    }
    public Temperature(double celsius, double fahrenheit){
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }

    public Temperature(Context context) {
        this.celsius = Double.parseDouble(context.getString((R.string.celsius)));
        this.fahrenheit = Double.parseDouble(context.getString((R.string.fahrenheit)));
    }
}
