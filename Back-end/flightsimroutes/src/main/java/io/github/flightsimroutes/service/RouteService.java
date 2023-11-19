package io.github.flightsimroutes.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import io.github.flightsimroutes.model.Aircraft;
import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;

public class RouteService {
    ArrayList<Aircraft> aircraft;
    ArrayList<Airport> airports;
    ArrayList<Route> routes;
    ArrayList<String> hubs;
    int flight_number;
    boolean isRepetitive;

    public RouteService(ArrayList<Aircraft> aircraft, ArrayList<Airport> airports, int flight_number,
            ArrayList<Route> routes,
            ArrayList<String> hubs, boolean isRepetitive) {
        this.aircraft = aircraft;
        this.flight_number = flight_number;
        this.routes = routes;
        this.hubs = hubs;
        this.isRepetitive = isRepetitive;
        this.airports = airports;
    }

    public ArrayList<Route> createDemand() {
        connectHubs();
        for (Airport airport : airports) {
            generateDemands(airport);
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
                    if (!airport.getType().equals("lessDemand")) {
                        switch (airport.getType()) { // to other airports
                            case "extremeDemand":
                                if (randomNumber >= 8) {
                                    createRoute(airport.getIcao(), depAirport.getIcao(), airport.getType());
                                }
                            case "bigDemand":
                                if (randomNumber >= 9) {
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
        Airport depAirport = searchAirport(dep);
        Airport arrAirport = searchAirport(arr);

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
                addRoute(subfleets, dep, arr);
                addRoute(subfleets, arr, dep);
            }
        } else {
            addRoute(subfleets, dep, arr);
            addRoute(subfleets, arr, dep);
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

    private void addRoute(String subfleets, String dep, String arr) {
        Route route = new Route(Integer.toString(flight_number), dep, arr, subfleets);
        routes.add(route);
        flight_number++;
    }

    public Airport searchAirport(String icao) {
        for (Airport airport : this.airports) {
            if (airport.getIcao().equals(icao)) {
                return airport;
            }
        }
        return null;
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
