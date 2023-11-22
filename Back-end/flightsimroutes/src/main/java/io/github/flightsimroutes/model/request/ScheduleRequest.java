package io.github.flightsimroutes.model.request;

import java.util.ArrayList;
import java.util.Set;

import io.github.flightsimroutes.model.entity.Aircraft;

/**
 * Represent the request for the schedules generator.
 */

public class ScheduleRequest {    
    private ArrayList<Aircraft> aircraft;
    private Set<String> extremeDemand;
    private Set<String> bigDemand;
    private Set<String> mediumDemand;
    private int international;
    private String baseCountry;
    private int flight_number;
    private boolean isRepetitive;
    private Set<String> hubs;
    private String airline;
    private int quantity;

    public ArrayList<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(ArrayList<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

    public Set<String> getExtremeDemand() {
        return extremeDemand;
    }

    public void setExtremeDemand(Set<String> extremeDemand) {
        this.extremeDemand = extremeDemand;
    }

    public Set<String> getBigDemand() {
        return bigDemand;
    }

    public void setBigDemand(Set<String> bigDemand) {
        this.bigDemand = bigDemand;
    }

    public Set<String> getMediumDemand() {
        return mediumDemand;
    }

    public void setMediumDemand(Set<String> mediumDemand) {
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

    public Set<String> getHubs() {
        return hubs;
    }

    public void setHubs(Set<String> hubs) {
        this.hubs = hubs;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
