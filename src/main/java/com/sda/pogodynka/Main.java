package com.sda.pogodynka;

import com.sda.pogodynka.forecast.WeatherstackForecastProvider;
import com.sda.pogodynka.model.Location;
import com.sda.pogodynka.model.Weather;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello in pogodynka application");
        Location rzeszow = new Location(
                "id",
                "Rzeszów",
                "Poland",
                50.0,
                21.8
        );

        System.out.println("We'll present random forecast for Rzeszów");

        WeatherstackForecastProvider forecastService = new WeatherstackForecastProvider();
        Weather forecast = forecastService.getForecast(rzeszow);

        System.out.printf("In %s it's %.1f degrees and %s",
                rzeszow.getName(), forecast.getTemperature(), forecast.getDescription());
    }
}
