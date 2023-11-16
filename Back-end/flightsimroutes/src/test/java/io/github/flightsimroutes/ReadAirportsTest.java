package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.service.RouteService;

public class ReadAirportsTest {

    @Test
    public void VerifyArray() {
        ArrayList<Airport> airports = RouteService.readAirports(3,"BR");

        assertNotNull(airports);
    }

    @Test
    public void SearchArray() {
        RouteService readAirports = new RouteService();

        ArrayList<Airport> airports = RouteService.readAirports(3,"BR");

        Airport Recife = readAirports.searchAirport(airports, "SBRF");

        System.out.println(Recife.getIcao());

        assertEquals(Recife.getIcao(), "SBRF");
    }

}
