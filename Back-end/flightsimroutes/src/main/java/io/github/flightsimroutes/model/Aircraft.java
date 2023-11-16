package io.github.flightsimroutes.model;
import java.util.ArrayList; 

public class Aircraft {
    private String subfleets;
    private String hub;
    private ArrayList<String> countries;

    private boolean extremeDemand;
    private boolean bigDemand;
    private boolean mediumDemand;
    private boolean lessDemand;

    public Aircraft(String subfleets, String hub,ArrayList<String> countries, boolean extremeDemand, boolean bigDemand, boolean mediumDemand,
            boolean lessDemand) {
        this.subfleets = subfleets;
        this.hub = hub;
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
