package io.github.flightsimroutes.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import io.github.flightsimroutes.model.entity.Route;

public class OpenFlightsService {
    final String routesFilename = "/open_flights/routes.dat";
    final String airportFilename = "/open_flights/airports.dat";
    final String airlineFilename = "/open_flights/airlines.dat";

    HashMap<String, String> airportIataToIcaoMap = new HashMap<>();
    HashMap<String, String> airlineIataToIcaoMap = new HashMap<>();

    public OpenFlightsService() {
        addAirports();
        addAirlines();
    }

    public ArrayList<Route> getFlights() {
        ArrayList<Route> flights = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource(routesFilename);
        try (InputStream inputStream = resource.getInputStream()) {
            String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] attributes = line.split(",");
                flights.add(createRoutes(attributes));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return flights;
    }

    private Route createRoutes(String[] attributes) {
        String airline = convertIataToIcaoAirlines(attributes[0]);
        String depAirport = convertIataToIcaoAirports(attributes[2]);
        String arrAirport = convertIataToIcaoAirports(attributes[4]);
        return new Route(airline, "", depAirport, arrAirport, attributes[8].trim().replaceAll("\\s+$", ""), 0);
    }

    private void addAirports() {
        ClassPathResource resource = new ClassPathResource(airportFilename);
        try (InputStream inputStream = resource.getInputStream()) {
            String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] attributes = line.split(",");
                airportIataToIcaoMap.put(attributes[4].replace("\"", ""), attributes[5].replace("\"", ""));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void addAirlines() {
        ClassPathResource resource = new ClassPathResource(airlineFilename);
        try (InputStream inputStream = resource.getInputStream()) {
            String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] attributes = line.split(",");

                airlineIataToIcaoMap.put(attributes[3].replace("\"", ""), attributes[4].replace("\"", ""));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String convertIataToIcaoAirports(String iata) {
        return airportIataToIcaoMap.getOrDefault(iata, "");
    }

    private String convertIataToIcaoAirlines(String iata) {
        return airlineIataToIcaoMap.getOrDefault(iata, "");
    }
}
