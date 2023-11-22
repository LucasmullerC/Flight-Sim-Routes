package io.github.flightsimroutes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.flightsimroutes.model.entity.Airport;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.util.AirportUtils;

public class ReadAirportsTest {
    Set<String> extremeDemand = new HashSet<>();
    Set<String> bigDemand = new HashSet<>();
    Set<String> mediumDemand = new HashSet<>();

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

        Airport Recife = AirportUtils.searchAirport(airports, "SBRF");

        System.out.println(Recife.getIcao());

        assertEquals("SBRF", Recife.getIcao());
    }
}
