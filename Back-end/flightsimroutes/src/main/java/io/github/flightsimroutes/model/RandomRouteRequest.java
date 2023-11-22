package io.github.flightsimroutes.model;

/**
 * Represent the request for the random route generator.
 */
public class RandomRouteRequest {
    private int quantity;
    private String depCountry,arrCountry,depAirport,arrAirport;
    private double minDistance,maxDistance;
    private boolean continuous;

    public int getQuantity() {
        return quantity;
    }

    public String getDepCountry() {
        return depCountry;
    }

    public String getArrCountry() {
        return arrCountry;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public boolean isContinuous() {
        return continuous;
    }

}
