package io.github.flightsimroutes.model;

import java.util.ArrayList;

public class ScheduleRequest {    
    private ArrayList<Aircraft> aircraft;
    private ArrayList<String> extremeDemand;
    private ArrayList<String> bigDemand;
    private ArrayList<String> mediumDemand;
    private int international;
    private String baseCountry;
    private int flight_number;
    private boolean isRepetitive;
    private ArrayList<String> hubs;

    public ScheduleRequest(ArrayList<Aircraft> aircraft, ArrayList<String> extremeDemand, ArrayList<String> bigDemand,
            ArrayList<String> mediumDemand, int international, String baseCountry, int flight_number,
            boolean isRepetitive, ArrayList<String> hubs) {
        this.aircraft = aircraft;
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;
        this.international = international;
        this.baseCountry = baseCountry;
        this.flight_number = flight_number;
        this.isRepetitive = isRepetitive;
        this.hubs = hubs;
    }

    public ArrayList<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(ArrayList<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

    public ArrayList<String> getExtremeDemand() {
        return extremeDemand;
    }

    public void setExtremeDemand(ArrayList<String> extremeDemand) {
        this.extremeDemand = extremeDemand;
    }

    public ArrayList<String> getBigDemand() {
        return bigDemand;
    }

    public void setBigDemand(ArrayList<String> bigDemand) {
        this.bigDemand = bigDemand;
    }

    public ArrayList<String> getMediumDemand() {
        return mediumDemand;
    }

    public void setMediumDemand(ArrayList<String> mediumDemand) {
        this.mediumDemand = mediumDemand;
    }

    public int getInternational() {
        return international;
    }

    public void setInternational(int international) {
        this.international = international;
    }

    public String getBaseCountry() {
        return baseCountry;
    }

    public void setBaseCountry(String baseCountry) {
        this.baseCountry = baseCountry;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public boolean isRepetitive() {
        return isRepetitive;
    }

    public void setRepetitive(boolean isRepetitive) {
        this.isRepetitive = isRepetitive;
    }

    public ArrayList<String> getHubs() {
        return hubs;
    }

    public void setHubs(ArrayList<String> hubs) {
        this.hubs = hubs;
    }

}
