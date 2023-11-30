package io.github.flightsimroutes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.service.OpenSkyNetworkService;
import io.github.flightsimroutes.service.RouteService;

public class OpenSkyNetworkTest {
    
    @Test
    public void getAPI(){
        OpenSkyNetworkService openSky = new OpenSkyNetworkService();
        ArrayList<Route> routes = new ArrayList<>();
        routes = openSky.getFlights("1698595826","1698599426");

        assertNotNull(routes);
    }

    @Test
    public void generatePairs(){
        OpenSkyNetworkService openSky = new OpenSkyNetworkService();
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Route> pairs = new ArrayList<>();
        routes = openSky.getFlights("1698595826","1698599426");

        RouteService routeService = new RouteService();
        pairs = routeService.generateRoutePairs(routes, "", "", true, 5);
        for(Route route: pairs){
            System.out.println(route.getDpt_airport()+" - "+route.getArr_airport());
        }
        assertNotNull(pairs);
    }
}
