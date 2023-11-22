package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.service.RouteService;

public class RouteServiceTest {
    
    @Test
    public void generateRandomRoute(){
        ArrayList<Airport> airportsList = new ArrayList<>();
        AirportsService readAirports = new AirportsService();

        airportsList = readAirports.generateAirportsRoutes("");
        RouteService routeService = new RouteService();
        ArrayList<Route> routes = routeService.generateRoute(airportsList,"SBRF", "", "", "", 500, 100, false, 1);

        assertEquals("SBRF",routes.get(0).getDpt_airport());
    }

    @Test
    public void generateRandomRouteAirports(){
        ArrayList<Airport> airportsList = new ArrayList<>();
        AirportsService readAirports = new AirportsService();

        airportsList = readAirports.generateAirportsRoutes("");
        RouteService routeService = new RouteService();
        ArrayList<Route> routes = routeService.generateRoute(airportsList,"SBRF", "SBGR", "", "", 1500, 100, false, 1);

        assertEquals("SBRF",routes.get(0).getDpt_airport());
        assertEquals("SBGR",routes.get(0).getArr_airport());
    }

    @Test
    public void generateRandomRouteOnlyArr(){
        ArrayList<Airport> airportsList = new ArrayList<>();
        AirportsService readAirports = new AirportsService();

        airportsList = readAirports.generateAirportsRoutes("");
        RouteService routeService = new RouteService();
        ArrayList<Route> routes = routeService.generateRoute(airportsList,"", "SBRF", "", "", 1500, 100, false, 1);

        assertEquals("SBRF",routes.get(0).getArr_airport());
    }
}
