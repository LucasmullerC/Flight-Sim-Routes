package io.github.flightsimroutes.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.github.flightsimroutes.model.Aircraft;
import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;

public class RouteService {
    ArrayList<Aircraft> aircraft;
    int flight_number;
    ArrayList<Route> routes;
    ArrayList<String> hubs;
    boolean isRepetitive;
    
    public RouteService(ArrayList<Aircraft> aircraft, int flight_number, ArrayList<Route> routes, ArrayList<String> hubs,boolean isRepetitive) {
        this.aircraft = aircraft;
        this.flight_number = flight_number;
        this.routes = routes;
        this.hubs = hubs;
        this.isRepetitive = isRepetitive;
    }

    public ArrayList<Route> createDemand(ArrayList<Airport> airports){
        connectHubs();
        return routes;
    }

    private void connectHubs(){
        Set<String> existingRoutes = new HashSet<>();
        for (String hub : hubs) {
            for(String hubArrival : hubs){
                if(!existingRoutes.contains(hub+","+hubArrival) && !hub.equals(hubArrival)){
                    createRoute(hub, hubArrival, "extremeDemand");
                    existingRoutes.add(hub+","+hubArrival);
                    existingRoutes.add(hubArrival+","+hub);
                }
            }
        }
    }

    private void createRoute(String dep, String arr, String demand){
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

        if(isRepetitive){
            for (int i = 0; i <= 5; i++) {
                addRoute(subfleets, dep, arr);
                addRoute(subfleets, arr, dep);
            }
        }
        else{
                addRoute(subfleets, dep, arr);
                addRoute(subfleets, arr, dep);
        }
    }

    private void addRoute(String subfleets, String dep, String arr){
        Route route = new Route(Integer.toString(flight_number), dep, arr, subfleets);
        routes.add(route);
        flight_number++;
    }
}
