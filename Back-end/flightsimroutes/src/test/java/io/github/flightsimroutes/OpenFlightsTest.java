package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.service.OpenFlightsService;
import io.github.flightsimroutes.service.RouteService;

public class OpenFlightsTest {
    
    @Test
    public void generatePairs(){
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Route> pairs = new ArrayList<>();
        OpenFlightsService openFlightsService = new OpenFlightsService();

        routes = openFlightsService.getFlights();

        RouteService routeService = new RouteService();
        pairs = routeService.generateRoutePairs(routes, "", "", true, 5);
        assertNotNull(pairs);
    }

        @Test
    public void generatePairsAirport(){
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Route> pairs = new ArrayList<>();
        OpenFlightsService openFlightsService = new OpenFlightsService();

        routes = openFlightsService.getFlights();

        RouteService routeService = new RouteService();
        pairs = routeService.generateRoutePairs(routes, "SBRF", "", true, 5);
        for(Route route: pairs){
            System.out.println(route.getDpt_airport()+" - "+route.getArr_airport());
        }
        assertNotNull(pairs);
    }
}
