package com.sda.pogodynka.forecast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sda.pogodynka.model.Location;
import com.sda.pogodynka.model.Weather;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WeatherstackForecastProvider {
    Gson gson = new Gson();
    private static final String WEATHERSTACK_URL = "http://api.weatherstack.com/current?access_key=%s&query=%s";
    private static String ACCESS_KEY = "";

    public Weather getForecast(Location location) {
        String response = getForecastResponse(location);
        return decodeWeatherstackJson(response);
    }

    private String getAccessKey() {
        if (ACCESS_KEY.isBlank()) {
            try {
                ACCESS_KEY = Files.readString(Path.of("weatherstack.key"));
            } catch (IOException e) {
                throw new RuntimeException("resources are not in place!! (API key)");
            }
        }
        return ACCESS_KEY;
    }

    private String getForecastResponse(Location location) {
        try {
            String query = String.format("%f,%f", location.getLat(), location.getLon());
            return Request.Get(String.format(WEATHERSTACK_URL, getAccessKey(), query)).execute().returnContent().asString();
        } catch (IOException e) {
            throw new RuntimeException("resources are not in place!! (static json)");
        }
    }

    private Weather decodeWeatherstackJson(String jsonResponse) {
        JsonObject response = gson.fromJson(jsonResponse, JsonObject.class);
        JsonObject current = response.get("current").getAsJsonObject();

        Double temp = current.get("temperature").getAsDouble();
        String desc = current.get("weather_descriptions").getAsJsonArray().get(0).getAsString();
        Double hum = current.get("humidity").getAsDouble();
        Double pres = current.get("pressure").getAsDouble();
        String windDir = current.get("wind_dir").getAsString();
        Double windSp = current.get("wind_speed").getAsDouble();


        return new Weather(temp, pres, hum, windSp, windDir, desc);
    }
}
