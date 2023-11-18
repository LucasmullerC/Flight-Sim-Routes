package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.Aircraft;
import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.service.RouteService;

public class RouteServiceTest {
    ArrayList<String> extremeDemand = new ArrayList<>(), bigDemand = new ArrayList<>(), mediumDemand = new ArrayList<>(), hub = new ArrayList<>();
    ArrayList<Aircraft> aircrafts = new ArrayList<>();
     ArrayList<Route> routes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        ArrayList<String> countries = new ArrayList<>();
        countries.add("BR");
        countries.add("AR");
        Aircraft aircraft = new Aircraft("A20N", countries, true, true, true, false);
        aircrafts.add(aircraft);
        
        hub.add("SBRF");
        hub.add("SBBR");
        hub.add("SBGR");

    }

    @Test
    public void createRouteHubTest(){
        AirportsService readAirports = new AirportsService(extremeDemand,bigDemand,mediumDemand);
        ArrayList<Airport> airports = readAirports.readAirports(3,"BR");

        RouteService routeService = new RouteService(aircrafts, 6000, routes ,hub,false);
        routes = routeService.createDemand(airports);

        System.out.println(routes.toString());
        for(Route route: routes){
            System.out.println(route.getDpt_airport()+" - "+route.getArr_airport());
        }
        assertEquals("SBRF",routes.get(0).getDpt_airport());
        assertEquals("SBBR", routes.get(0).getArr_airport());
    }
}
