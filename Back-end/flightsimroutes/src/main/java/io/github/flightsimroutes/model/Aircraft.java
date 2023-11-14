package io.github.flightsimroutes.model;

public class Aircraft {
    private String subfleets;
    private String continents;
    private boolean extremeDemand;
    private boolean bigDemand;
    private boolean mediumDemand;
    private boolean lessDemand;

    public Aircraft(String subfleets, String continents, boolean extremeDemand, boolean bigDemand, boolean mediumDemand,
            boolean lessDemand) {
        this.subfleets = subfleets;
        this.continents = continents;
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;
        this.lessDemand = lessDemand;
    }

    public String getSubfleets() {
        return subfleets;
    }

    public String getContinents() {
        return continents;
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

    public void setSubfleets(String subfleets) {
        this.subfleets = subfleets;
    }

    public void setContinents(String continents) {
        this.continents = continents;
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
