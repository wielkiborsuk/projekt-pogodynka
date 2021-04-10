package com.sda.pogodynka.model;

public class Location {
    private String id;
    private String name;
    private String country;
    private Double lat;
    private Double lon;

    public Location() {
    }

    public Location(String id, String name, String country, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
