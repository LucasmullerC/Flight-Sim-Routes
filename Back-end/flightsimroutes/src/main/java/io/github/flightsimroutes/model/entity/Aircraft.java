package io.github.flightsimroutes.model.entity;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Aircraft {
    private String subfleets;
    private String hub;
    private ArrayList<String> countries;
    
    private boolean extremeDemand;
    private boolean bigDemand;
    private boolean mediumDemand;
    private boolean lessDemand;
    
        public Aircraft() {
    }

    @JsonCreator
        public Aircraft(
            @JsonProperty("subfleets") String subfleets,
            @JsonProperty("countries") ArrayList<String> countries,
            @JsonProperty("extremeDemand") boolean extremeDemand,
            @JsonProperty("bigDemand") boolean bigDemand,
            @JsonProperty("mediumDemand") boolean mediumDemand,
            @JsonProperty("lessDemand") boolean lessDemand) {
        this.subfleets = subfleets;
        this.countries = countries;
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;
        this.lessDemand = lessDemand;
    }


    public String getSubfleets() {
        return subfleets;
    }

    public String getHub() {
        return hub;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public boolean isextremeDemand() {
        return extremeDemand;
    }

    public boolean isbigDemand() {
        return bigDemand;
    }

    public boolean ismediumDemand() {
        return mediumDemand;
    }

    public boolean islessDemand() {
        return lessDemand;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public void setSubfleets(String subfleets) {
        this.subfleets = subfleets;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public void setextremeDemand(boolean extremeDemand) {
        this.extremeDemand = extremeDemand;
    }

    public void setbigDemand(boolean bigDemand) {
        this.bigDemand = bigDemand;
    }

    public void setmediumDemand(boolean mediumDemand) {
        this.mediumDemand = mediumDemand;
    }

    public void setlessDemand(boolean lessDemand) {
        this.lessDemand = lessDemand;
    }
}
