package com.sda.pogodynka.forecast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sda.pogodynka.model.Location;
import com.sda.pogodynka.model.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherstackForecastProvider {
    Gson gson = new Gson();

    public Weather getForecast(Location location) {
        String response = getForecastResponse(location);
        return decodeWeatherstackJson(response);
    }

    private String getForecastResponse(Location location) {
        try {
            Path responseFile = Paths.get(ClassLoader.getSystemResource("static_response.json").toURI());
            return Files.readString(responseFile);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("resources are not in place!!");
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


        Weather forecast = new Weather();
        forecast.setDescription(desc);
        forecast.setTemperature(temp);
        forecast.setHumidity(hum);
        forecast.setPressure(pres);
        forecast.setWindDirection(windDir);
        forecast.setWindSpeed(windSp);

        return forecast;
    }
}
