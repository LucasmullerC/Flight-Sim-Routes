package io.github.flightsimroutes.service;

import java.util.ArrayList;
import java.util.Collections;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.util.GeographicUtils;

public class RouteService {

    public ArrayList<Route> generateRoute(ArrayList<Airport> airports, String depAirport, String arrAirport,
            String depCountry, String arrCountry,
            double maxDistance, double minDistance,
            boolean continuous, int quantity) {
        ArrayList<Route> routes = new ArrayList<Route>();

        for (int i = 0; i < quantity; i++) {
            Route route = generateRandomRoute(depAirport, arrAirport, depCountry, arrCountry, maxDistance, minDistance,
                    airports);
            if (route != null) {
                routes.add(route);

                if (continuous) {
                    depAirport = route.getArr_airport();
                    arrAirport = "";
                } else {
                    break;
                }
            }
        }

        return routes.isEmpty() ? null : routes;
    }

    private Route generateRandomRoute(String depIcao, String arrIcao, String depCountry, String arrCountry,
            double maxDistance,
            double minDistance, ArrayList<Airport> airports) {
        Collections.shuffle(airports);

        for (Airport depAirport : airports) {
            if (isValidAirport(depAirport, depIcao, depCountry)) {
                for (Airport arrAirport : airports) {
                    if (isValidAirport(arrAirport, arrIcao, arrCountry) && !depAirport.equals(arrAirport)) {
                        double distance = checkDistance(depAirport, arrAirport, maxDistance, minDistance);
                        if (distance != 0) {
                            return new Route("", "0", depAirport.getIcao(), arrAirport.getIcao(), "", distance);
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean isValidAirport(Airport airport, String icao, String country) {
        return (country.isEmpty() || country.equals(airport.getCountry())) &&
                (icao.isEmpty() || icao.equals(airport.getIcao()));
    }

    private double checkDistance(Airport dep, Airport arr, double maxDistance, double minDistance) {
        double distance = GeographicUtils.getAirportsDistanceinMiles(
                Double.valueOf(dep.getLat()), Double.valueOf(dep.getLon()),
                Double.valueOf(arr.getLat()), Double.valueOf(arr.getLon()));

        return (distance >= minDistance && distance <= maxDistance) ? distance : 0;
    }

}
