package io.github.flightsimroutes.service;

import java.util.ArrayList;
import java.util.Collections;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.util.GeographicUtils;

public class RouteService {
    ArrayList<Airport> airports;
    ArrayList<Route> routes = new ArrayList<Route>();

    public RouteService(ArrayList<Airport> airports) {
        this.airports = airports;
    }

    public ArrayList<Route> generateRoute(String depAirport, String arrAirport, String depCountry, String arrCountry,
            double maxDistance, double minDistance,
            boolean continous, int quantity) {
        generateRandomRouteCountry(depAirport, arrAirport, depCountry, arrCountry, maxDistance, minDistance);

        if (routes.isEmpty()) {
            return null;
        } else {
            if (continous == true) {
                int cont = 1;

                do {
                    Route lastRoute = routes.get(routes.size() - 1);
                    generateRandomRouteCountry(lastRoute.getArr_airport(), "", "", arrCountry, maxDistance,
                            minDistance);
                    cont++;
                } while (cont < quantity);
                return routes;
            } else {
                return routes;
            }
        }
    }

    private void generateRandomRouteCountry(String depIcao, String arrIcao, String depCountry, String arrCountry,
            double maxDistance,
            double minDistance) {
        Airport depAirport = null, arrAirport = null;
        Collections.shuffle(airports);
        for (Airport airport : airports) {
            if (depCountry.equals(airport.getCountry()) && depIcao.equals("")
                    || depCountry.equals("") && depIcao.equals(airport.getIcao())
                    || depCountry.equals("") && depIcao.equals("")) {
                if (depAirport == null && airport != arrAirport) {
                    depAirport = airport;
                }
            }
            if (arrCountry.equals(airport.getCountry()) && arrIcao.equals("")
                    || arrCountry.equals("") && arrIcao.equals(airport.getIcao())
                    || arrCountry.equals("") && arrIcao.equals("")) {
                if (arrAirport == null && airport != depAirport) {
                    arrAirport = airport;
                }
            }
            if (depAirport != null && arrAirport != null) {
                double distance = checkDistance(depAirport, arrAirport, maxDistance, minDistance);
                if (distance != 0) {
                    addRoute(depAirport, arrAirport, distance);
                    break;
                } else if (!arrIcao.equals("") && depIcao.equals("")
                        || !arrCountry.equals("") && depCountry.equals("")) {
                    depAirport = null;
                }
                else {
                    arrAirport = null;
                }
            }
        }
    }

    private double checkDistance(Airport dep, Airport arr, double maxDistance, double minDistance) {
        double distance = GeographicUtils.getAirportsDistanceinMiles(Double.valueOf(dep.getLat()),
                Double.valueOf(dep.getLon()), Double.valueOf(arr.getLat()), Double.valueOf(arr.getLon()));

        if (distance >= minDistance && distance <= maxDistance) {
            return distance;
        } else {
            return 0;
        }
    }

    private void addRoute(Airport dep, Airport arr, double distance) {
        Route route = new Route("", "0", dep.getIcao(), arr.getIcao(), "", distance);
        routes.add(route);
    }

}
