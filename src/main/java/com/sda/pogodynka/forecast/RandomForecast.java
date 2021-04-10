package com.sda.pogodynka.forecast;

import com.sda.pogodynka.model.Location;
import com.sda.pogodynka.model.Weather;

import java.util.List;
import java.util.Random;

public class RandomForecast {

    List<String> DESCRIPTIONS = List.of("Sunny", "Rainy", "Windy");
    List<String> DIRECTIONS = List.of("North", "South", "East", "West");

    public Weather getForecast(Location location) {
        Random r = new Random();
        String description = DESCRIPTIONS.get(r.nextInt(DESCRIPTIONS.size()));
        String direction = DIRECTIONS.get(r.nextInt(DIRECTIONS.size()));
        return new Weather(
            r.nextDouble()*30, r.nextDouble()*1000, r.nextDouble()*100,
                r.nextDouble()*100, direction, description
        );
    }
}
