package com.sda.pogodynka;

import com.sda.pogodynka.dao.LocationRepositoryInMemory;
import com.sda.pogodynka.forecast.RandomForecast;
import com.sda.pogodynka.model.Location;
import com.sda.pogodynka.model.Weather;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello in pogodynka application");
        RandomForecast forecastService = new RandomForecast();

        LocationRepositoryInMemory locationRepository = initLocationRepository();
        Location selectedLocation = selectLocation(locationRepository);
        Weather forecast = forecastService.getForecast(selectedLocation);
        presentForecast(selectedLocation, forecast);
    }

    private static void presentForecast(Location selectedLocation, Weather forecast) {
        System.out.printf("In %s it's %.1f degrees and %s\n",
                selectedLocation.getName(), forecast.getTemperature(), forecast.getDescription());
    }

    private static Location selectLocation(LocationRepositoryInMemory locationRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Locations in repo:");
        for (Location location : locationRepository.getLocationList()) {
            System.out.printf("id: %s, name: %s, lat: %.2f, lon: %.2f\n",
                    location.getId(), location.getName(), location.getLat(), location.getLon());
        }

        System.out.println("Please input location id:");
        String selectedId = scanner.nextLine();

        Location selectedLocation = locationRepository.getLocationById(selectedId);
        System.out.printf("We'll present random forecast for %s\n", selectedLocation.getName());
        return selectedLocation;
    }

    private static LocationRepositoryInMemory initLocationRepository() {
        LocationRepositoryInMemory repo = new LocationRepositoryInMemory();

        repo.addLocation(new Location(
                "rzeszow",
                "Rzeszów",
                "Poland",
                50.0,
                21.8
        ));
        repo.addLocation(new Location(
                "london",
                "London",
                "UK",
                51.5,
                -0.12
        ));
        return repo;
    }
}
