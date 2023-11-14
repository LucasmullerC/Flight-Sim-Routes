package io.github.flightsimroutes.model;

public class Airport {
    private String icao;
    private String name;
    private String lat;
    private String lon;
    private String continent;
    private String country;
    private String type;

    public Airport(String icao, String name, String lat, String lon, String continent, String country, String type) {
        this.icao = icao;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.continent = continent;
        this.country = country;
        this.type = type;
    }

    public String getIcao() {
        return icao;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        Airport airport = (Airport) o;
        return this.icao.equals(airport.getIcao());
    }

}
