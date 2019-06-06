package com.example.hw5;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import android.content.Context;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.when;

public class TemperatureTest {
    Temperature ourTemperature;

    @Mock
    Context mockContext;
    //initializing mocks
    @Before
    public void setUp() {
        ourTemperature = new Temperature(32, 89.6);

        MockitoAnnotations.initMocks((this));

        when(mockContext.getString(R.string.celsius))
                .thenReturn("32");
        when(mockContext.getString(R.string.fahrenheit))
                .thenReturn("89.6");

    }

    @Test
    public void temperatureTestingFahrenheitToCelsius() {
        //Temperature fahrenheitTemperature = new Temperature(32,45);
        double fahrenheit = Temperature.fahrenheitToCelsius(32);
        assertThat(fahrenheit).isEqualTo(0.0);
    }

    @Test
    public void temperatureTestingCelsiusToFahrenheit() {
        double celsius = Temperature.celsiusToFahrenheit(32);
        assertThat(celsius).isEqualTo(89.6);
    }
    @Test
    public void celsiusIsCelsius(){
        Temperature sameTemperature = new Temperature(mockContext);
        assertThat(sameTemperature.getCelsius()).isEqualTo(32.0);
    }
    @Test
    public void fahrenheitIsFahrenheit(){
        Temperature temp = new Temperature(mockContext);
        assertThat(temp.getFarenheit()).isEqualTo(89.6);
    }
}
