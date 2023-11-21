package io.github.flightsimroutes.model;

public class RandomRouteRequest {
    private int quantity;
    private String depCountry,arrCountry,depAirport,arrAirport;
    private double minDistance,maxDistance;
    private boolean continous;
    
    public RandomRouteRequest(int quantity, String depCountry, String arrCountry, String depAirport,
            String arrAirport, double minDistance, double maxDistance, boolean continous) {
        this.quantity = quantity;
        this.depCountry = depCountry;
        this.arrCountry = arrCountry;
        this.depAirport = depAirport;
        this.arrAirport = arrAirport;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.continous = continous;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDepCountry() {
        return depCountry;
    }

    public void setDepCountry(String depCountry) {
        this.depCountry = depCountry;
    }

    public String getArrCountry() {
        return arrCountry;
    }

    public void setArrCountry(String arrCountry) {
        this.arrCountry = arrCountry;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean isContinous() {
        return continous;
    }

    public void setContinous(boolean continous) {
        this.continous = continous;
    }

}
