package io.github.flightsimroutes.model.request;

public class DatabaseRouteRequest {
    private int quantity;
    private String depAirport,arrAirport,beginTime,endTime;
    private double minDistance,maxDistance;
    private boolean continuous;

    public int getQuantity() {
        return quantity;
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
    public String getEndTime() {
        return endTime;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public boolean isContinuous() {
        return continuous;
    }
}
