package io.github.flightsimroutes.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import io.github.flightsimroutes.model.Aircraft;
import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.util.GeographicUtils;

public class DemandService {
    String airline;
    ArrayList<Aircraft> aircraft;
    ArrayList<Airport> airports;
    ArrayList<Route> routes = new ArrayList<Route>();
    ArrayList<String> hubs;
    int flight_number,quantity;
    boolean isRepetitive;

    public DemandService(String airline, ArrayList<Aircraft> aircraft, ArrayList<Airport> airports, int flight_number,
            ArrayList<String> hubs, boolean isRepetitive, int quantity) {
        this.airline = airline;
        this.aircraft = aircraft;
        this.flight_number = flight_number;
        this.hubs = hubs;
        this.isRepetitive = isRepetitive;
        this.airports = airports;
        this.quantity = quantity;
    }

    public ArrayList<Route> createDemand(int flightType, String baseCountry) {
        connectHubs();
        int chanceNum = verifyQuantity();
        boolean typeCheck = false;
        for (Airport airport : airports) {
            switch (flightType) {
                case 1 /* ONLY INTER */:
                    if (!airport.getCountry().equals(baseCountry)) {
                        typeCheck = true;
                    }
                case 2 /* BOTH */:
                    typeCheck = true;
                case 3 /* DOMESTIC ONLY */:
                    if (airport.getCountry().equals(baseCountry)) {
                        typeCheck = true;
                    }
            }
            if(typeCheck == true){
                int randomNumber = new Random().nextInt(10);
                if(randomNumber >= chanceNum){
                    generateDemands(airport);
                }
            }
        }
        return routes;
    }

    private void connectHubs() {
        Set<String> existingRoutes = new HashSet<>();
        for (String hub : hubs) {
            for (String hubArrival : hubs) {
                if (!existingRoutes.contains(hub + "," + hubArrival) && !hub.equals(hubArrival)) {
                    createRoute(hub, hubArrival, "extremeDemand");
                    existingRoutes.add(hub + "," + hubArrival);
                    existingRoutes.add(hubArrival + "," + hub);
                }
            }
        }
    }

    private void generateDemands(Airport depAirport) {
        for (Airport airport : airports) {
            if (!airport.getIcao().equals(depAirport.getIcao())) {
                int randomNumber = new Random().nextInt(10);
                if (isHub(airport.getIcao())) {
                    switch (depAirport.getType()) { // to hubs
                        case "extremeDemand":
                            if (randomNumber >= 0) {
                                createRoute(airport.getIcao(), depAirport.getIcao(), depAirport.getType());
                            } else {
                                break;
                            }
                        case "bigDemand":
                            if (randomNumber >= 3) {
                                createRoute(airport.getIcao(), depAirport.getIcao(), depAirport.getType());
                            } else {
                                break;
                            }
                        case "mediumDemand":
                            if (randomNumber >= 5) {
                                createRoute(airport.getIcao(), depAirport.getIcao(), depAirport.getType());
                            } else {
                                break;
                            }
                        case "lessDemand":
                            if (randomNumber >= 7) {
                                createRoute(airport.getIcao(), depAirport.getIcao(), depAirport.getType());
                            } else {
                                break;
                            }
                    }
                } else {
                    if (!airport.getType().equals("lessDemand") && !depAirport.getType().equals("lessDemand")) {
                        switch (airport.getType()) { // to other airports
                            case "extremeDemand":
                                if (randomNumber >= 9) {
                                    createRoute(airport.getIcao(), depAirport.getIcao(), airport.getType());
                                }
                            case "bigDemand":
                                if (randomNumber >= 10) {
                                    createRoute(airport.getIcao(), depAirport.getIcao(), airport.getType());
                                }
                            case "mediumDemand":
                                if (randomNumber >= 10) {
                                    createRoute(airport.getIcao(), depAirport.getIcao(), airport.getType());
                                }
                        }
                    }
                }
            }
        }
    }

    private void createRoute(String dep, String arr, String demand) {
        String subfleets = "";
        Airport depAirport = AirportsService.searchAirport(this.airports,dep);
        Airport arrAirport = AirportsService.searchAirport(this.airports,arr);

        if (arrAirport.getCountry().equals(depAirport.getCountry())) {
            subfleets = generateSubfleetsDemand(demand);
        } else {
            subfleets = generateSubfleetsInternational(depAirport, arrAirport);
            if (subfleets.equals("")) {
                subfleets = generateSubfleetsDemand(demand);
            }
        }

        if (isRepetitive) {
            for (int i = 0; i <= 5; i++) {
                addRoute(subfleets, depAirport, arrAirport);
                addRoute(subfleets, depAirport, arrAirport);
            }
        } else {
            addRoute(subfleets, depAirport, arrAirport);
            addRoute(subfleets, depAirport, arrAirport);
        }
    }

    private String generateSubfleetsInternational(Airport dep, Airport arr) {
        String subfleets = "";
        for (Aircraft airc : aircraft) {
            for (String country : airc.getCountries()) {
                if (arr.getContinent().equals(country)) {
                    if (subfleets.equals("")) {
                        subfleets = airc.getSubfleets();
                    } else {
                        subfleets += ";" + airc.getSubfleets();
                    }
                }
            }
        }
        return subfleets;
    }

    private String generateSubfleetsDemand(String demand) {
        String subfleets = "";
        boolean found = false;
        for (Aircraft selectedAircraft : aircraft) {
            switch (demand) {
                case "extremeDemand":
                    if (selectedAircraft.isextremeDemand()) {
                        found = true;
                    }
                case "bigDemand":
                    if (selectedAircraft.isbigDemand()) {
                        found = true;
                    }
                case "mediumDemand":
                    if (selectedAircraft.ismediumDemand()) {
                        found = true;
                    }
                case "lessDemand":
                    if (selectedAircraft.islessDemand()) {
                        found = true;
                    }
            }

            if (found == true) {
                if (subfleets.equals("")) {
                    subfleets = selectedAircraft.getSubfleets();
                } else {
                    subfleets += ";" + selectedAircraft.getSubfleets();
                }
                found = false;
            }
        }
        return subfleets;
    }

    private void addRoute(String subfleets, Airport dep, Airport arr) {
        double distance = GeographicUtils.getAirportsDistanceinMiles(Double.valueOf(dep.getLat()),Double.valueOf(dep.getLon()),Double.valueOf(arr.getLat()),Double.valueOf(arr.getLon()));
        Route route = new Route(airline,Integer.toString(flight_number), dep.getIcao(), arr.getIcao(), subfleets, distance);
        routes.add(route);
        flight_number++;
    }

    private int verifyQuantity(){
        switch (quantity) {
            case 1: //Very Low
                return 9;
            case 2: //Low
                return 6;
            case 3: //Mid
                return 3;
            case 4: //High
                return 0;
        }
        return 0;
    }

    private boolean isHub(String icao) {
        for (String hub : hubs) {
            if (hub.equals(icao)) {
                return true;
            }
        }
        return false;
    }
}
