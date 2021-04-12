package com.sda.pogodynka.dao;

import com.sda.pogodynka.model.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationRepositoryInMemory {

    private Map<String, Location> locations = new HashMap<>();

    public void addLocation(Location location) {
        locations.put(location.getId(), location);
    }

    public List<Location> getLocationList() {
        return List.copyOf(locations.values());
    }

    public Location getLocationById(String id) {
        return locations.get(id);
    }
}
