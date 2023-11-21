package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.service.AirportsService;

public class ReadAirportsTest {
    ArrayList<String> extremeDemand = new ArrayList<>(), bigDemand = new ArrayList<>(), mediumDemand = new ArrayList<>();

    @Test
    public void VerifyArray() {
        AirportsService readAirports = new AirportsService();
        ArrayList<Airport> airports = readAirports.generateAirportsSchedules(extremeDemand,bigDemand,mediumDemand,"BR");

        assertNotNull(airports);
    }

    @Test
    public void SearchArray() {
        AirportsService readAirports = new AirportsService();

        ArrayList<Airport> airports = readAirports.generateAirportsSchedules(extremeDemand,bigDemand,mediumDemand,"BR");

        Airport Recife = AirportsService.searchAirport(airports, "SBRF");

        System.out.println(Recife.getIcao());

        assertEquals("SBRF", Recife.getIcao());
    }
}
