package io.github.flightsimroutes.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import io.github.flightsimroutes.model.entity.Aircraft;
import io.github.flightsimroutes.model.entity.Airport;
import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.util.AirportUtils;
import io.github.flightsimroutes.util.GeographicUtils;

public class DemandService {
    String airline;
    ArrayList<Aircraft> aircraft;
    ArrayList<Airport> airports;
    ArrayList<Route> routes = new ArrayList<Route>();
    Set<String> hubs;
    int flight_number, routeDensity;
    boolean isRepetitive;

    private static final int EXTREME_DEMAND_PROBABILITY = 0;
    private static final int BIG_DEMAND_PROBABILITY = 3;
    private static final int MEDIUM_DEMAND_PROBABILITY = 5;
    private static final int LESS_DEMAND_PROBABILITY = 7;
    private static final int EXTREME_DEMAND_PROBABILITY_NON_HUB = 7; //9
    private static final int BIG_DEMAND_PROBABILITY_NON_HUB = 9; //10
    private static final int MEDIUM_DEMAND_PROBABILITY_NON_HUB = 10; //10
    private static final int VERY_LOW_DENSITY = 9;
    private static final int LOW_DENSITY = 6;
    private static final int MID_DENSITY = 3;
    private static final int HIGH_DENSITY = 0;

    public DemandService(String airline, ArrayList<Aircraft> aircraft, ArrayList<Airport> airports, int flight_number,
            Set<String> hubs, boolean isRepetitive, int routeDensity) {
        this.airline = airline;
        this.aircraft = aircraft;
        this.flight_number = flight_number;
        this.hubs = hubs;
        this.isRepetitive = isRepetitive;
        this.airports = airports;
        this.routeDensity = routeDensity;
    }

    public ArrayList<Route> createDemand(int flightType, String baseCountry) {
        connectHubs();
        int chanceNum = getRouteDensity();
        for (Airport airport : airports) {
            if (verifyFlightType(flightType, baseCountry, airport) == true) {
                int randomNumber = new Random().nextInt(10);
                if (randomNumber >= chanceNum) {
                    generateDemands(airport, flightType, baseCountry);
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

    private void generateDemands(Airport depAirport, int flightType, String baseCountry) {
        for (Airport airport : airports) {
            if (!airport.getIcao().equals(depAirport.getIcao()) && verifyFlightType(flightType, baseCountry, airport)) {
                int randomNumber = new Random().nextInt(10);
                if (isHub(airport.getIcao())) {
                    generateDemands(depAirport, airport, randomNumber, true);
                } else {
                    if (!airport.getType().equals("lessDemand") && !depAirport.getType().equals("lessDemand")) {
                        generateDemands(depAirport, airport, randomNumber, false);
                    }
                }
            }
        }
    }

    private void generateDemands(Airport depAirport, Airport airport, int randomNumber, boolean isHub) {
        String demandType = depAirport.getType();
        int probability = getProbabilityByDemandType(demandType, isHub);

        if (randomNumber >= probability) {
            createRoute(airport.getIcao(), depAirport.getIcao(), depAirport.getType());
        }
    }

    private void createRoute(String dep, String arr, String demand) {
        Airport depAirport = AirportUtils.searchAirport(this.airports, dep);
        Airport arrAirport = AirportUtils.searchAirport(this.airports, arr);

        String subfleets = generateSubfleets(depAirport, arrAirport, demand);

        if (!subfleets.equals("")) {
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
    }

    private void addRoute(String subfleets, Airport dep, Airport arr) {
        double distance = GeographicUtils.getAirportsDistanceinMiles(Double.valueOf(dep.getLat()),
                Double.valueOf(dep.getLon()), Double.valueOf(arr.getLat()), Double.valueOf(arr.getLon()));
        Route route = new Route(airline, Integer.toString(flight_number), dep.getIcao(), arr.getIcao(), subfleets,
                distance);
        routes.add(route);
        flight_number++;
    }

    private String generateSubfleets(Airport depAirport, Airport arrivalAirport, String demand) {

        StringBuilder subfleetsBuilder = new StringBuilder();
        for (Aircraft selectedAircraft : aircraft) {
            if (isDemandSatisfied(selectedAircraft, demand) && aircraftHasCountry(arrivalAirport, selectedAircraft)) {
                if (subfleetsBuilder.length() > 0) {
                    subfleetsBuilder.append(";");
                }
                subfleetsBuilder.append(selectedAircraft.getSubfleets());
            }
        }
        return subfleetsBuilder.toString();
    }

    private boolean aircraftHasCountry(Airport arrival, Aircraft aircraft) {
        for (String country : aircraft.getCountries()) {
            if (arrival.getCountry().equals(country)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDemandSatisfied(Aircraft selectedAircraft, String demand) {
        switch (demand) {
            case "extremeDemand":
                return selectedAircraft.isextremeDemand();
            case "bigDemand":
                return selectedAircraft.isbigDemand();
            case "mediumDemand":
                return selectedAircraft.ismediumDemand();
            case "lessDemand":
                return selectedAircraft.islessDemand();
            default:
                return false;
        }
    }

    private int getProbabilityByDemandType(String demandType, boolean isHub) {
        if (isHub) {
            switch (demandType) {
                case "extremeDemand":
                    return EXTREME_DEMAND_PROBABILITY;
                case "bigDemand":
                    return BIG_DEMAND_PROBABILITY;
                case "mediumDemand":
                    return MEDIUM_DEMAND_PROBABILITY;
                case "lessDemand":
                    return LESS_DEMAND_PROBABILITY;
                default:
                    return 0;
            }
        } else {
            switch (demandType) {
                case "extremeDemand":
                    return EXTREME_DEMAND_PROBABILITY_NON_HUB;
                case "bigDemand":
                    return BIG_DEMAND_PROBABILITY_NON_HUB;
                case "mediumDemand":
                    return MEDIUM_DEMAND_PROBABILITY_NON_HUB;
                default:
                    return 0;
            }
        }
    }

    private boolean verifyFlightType(int flightType, String baseCountry, Airport airport) {
        boolean typeCheck = false;
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
        return typeCheck;
    }

    private int getRouteDensity() {
        switch (routeDensity) {
            case 1: // Very Low
                return VERY_LOW_DENSITY;
            case 2: // Low
                return LOW_DENSITY;
            case 3: // Mid
                return MID_DENSITY;
            case 4: // High
                return HIGH_DENSITY;
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
