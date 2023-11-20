package io.github.flightsimroutes.model;

public class Route {
    private String airline;
    private String flight_number;
    private String dpt_airport;
    private String arr_airport;
    private double distance = 0;
    private String flight_time = "0";
    private String flight_type = "J";
    private String subfleets;

    public Route(String airline, String flight_number, String dpt_airport, String arr_airport, String subfleets, double distance) {
        this.airline = airline;
        this.flight_number = flight_number;
        this.dpt_airport = dpt_airport;
        this.arr_airport = arr_airport;
        this.subfleets = subfleets;
        this.distance = distance;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public void setDpt_airport(String dpt_airport) {
        this.dpt_airport = dpt_airport;
    }

    public void setArr_airport(String arr_airport) {
        this.arr_airport = arr_airport;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public void setFlight_type(String flight_type) {
        this.flight_type = flight_type;
    }

    public void setSubfleets(String subfleets) {
        this.subfleets = subfleets;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public String getDpt_airport() {
        return dpt_airport;
    }

    public String getArr_airport() {
        return arr_airport;
    }

    public double getDistance() {
        return distance;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public String getFlight_type() {
        return flight_type;
    }

    public String getSubfleets() {
        return subfleets;
    }
}
