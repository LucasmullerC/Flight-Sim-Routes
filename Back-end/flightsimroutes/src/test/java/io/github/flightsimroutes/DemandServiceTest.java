package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.entity.Aircraft;
import io.github.flightsimroutes.model.entity.Airport;
import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.service.DemandService;

public class DemandServiceTest {
    Set<String> extremeDemand = new HashSet<>();
    Set<String> bigDemand = new HashSet<>();
    Set<String> mediumDemand = new HashSet<>();
    Set<String> hub = new HashSet<>();
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

        extremeDemand.add("SBSV");
        extremeDemand.add("SBPA");
        extremeDemand.add("SBFZ");

        bigDemand.add("SBJP");
        bigDemand.add("SBRJ");
        bigDemand.add("SBCF");

        mediumDemand.add("SBSP");
        mediumDemand.add("SBCG");
        mediumDemand.add("SBEG");
    }

    @Test
    public void createRouteHubTest() {
        AirportsService readAirports = new AirportsService();
        ArrayList<Airport> airports = readAirports.generateAirportsSchedules(extremeDemand, bigDemand, mediumDemand, "BR");

        DemandService routeService = new DemandService("ABV",aircrafts, airports, 6000, hub, false, 4);
        routes = routeService.createDemand(3, "BR");

        System.out.println(routes.toString());
        for (Route route : routes) {
            System.out.println(route.getDpt_airport() + " - " + route.getArr_airport());
        }
        assertEquals("SBRF", routes.get(0).getDpt_airport());
        assertEquals("SBBR", routes.get(0).getArr_airport());
    }

        @Test
    public void RouteDistanceTest() {
        double epsilon = 0.100000d;
        AirportsService readAirports = new AirportsService();
        ArrayList<Airport> airports = readAirports.generateAirportsSchedules(extremeDemand, bigDemand, mediumDemand, "BR");

        DemandService routeService = new DemandService("ABV",aircrafts, airports, 6000, hub, false, 4);
        routes = routeService.createDemand(3, "BR");

        System.out.println(routes.toString());
        for (Route route : routes) {
            System.out.println(route.getDpt_airport() + " - " + route.getArr_airport());
        }
        assertEquals(893.107, Double.valueOf(routes.get(0).getDistance()),epsilon);
    }

}
