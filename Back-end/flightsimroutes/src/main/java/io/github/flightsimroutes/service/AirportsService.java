package io.github.flightsimroutes.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.ResourceUtils;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.util.AirportUtils;

public class AirportsService {
    Set<String> extremeDemand = new HashSet<>();
    Set<String> bigDemand = new HashSet<>();
    Set<String> mediumDemand = new HashSet<>();
    String airportsCsvFilename = "airports.csv";

    /**
     * Generates airports based on schedules.
     *
     * @param extremeDemand List of ICAO codes for airports with extreme demand.
     * @param bigDemand     List of ICAO codes for airports with big demand.
     * @param mediumDemand  List of ICAO codes for airports with medium demand.
     * @param baseCountry   The base country for airport generation.
     * @return List of generated airports.
     */
    public ArrayList<Airport> generateAirportsSchedules(Set<String> extremeDemand, Set<String> bigDemand,
            Set<String> mediumDemand, String baseCountry) {
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;

        return readAirports(baseCountry);
    }

    /**
     * Generates airports for random routes.
     */
    public ArrayList<Airport> generateAirportsRoutes(String depCountry) {
        return readAirports(depCountry);
    }

    /**
     * Reads airports from the CSV file.
     */
    private ArrayList<Airport> readAirports(String baseCountry) {
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:"+airportsCsvFilename);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line; (line = br.readLine()) != null;) {
                    String[] attributes = line.split(",");
                    Airport airport = createAirports(attributes, baseCountry);
                    if (airport != null) {
                        airports.add(airport);
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return airports;

    }

    private Airport createAirports(String[] metadata, String baseCountry) {
        String country = metadata[8].replace("\"", "");
        String type = metadata[2].replace("\"", "");

        // If airport country is located at the base country create large and medium
        // airports.
        if (country.equals(baseCountry)) {
            if (type.equals("large_airport")) {
                return defineAirport(metadata, "large_airport");
            } else if (type.equals("medium_airport")) {
                return defineAirport(metadata, "medium_airport");
            }
            // If base country is empty create large and medium airports of any country.
        } else if (baseCountry.equals("")) {
            if (type.equals("large_airport")) {
                return defineAirport(metadata, "large_airport");
            } else if (type.equals("medium_airport")) {
                return defineAirport(metadata, "medium_airport");
            }
            // If airport country is not located at the base country create only large
            // airports.
        } else {
            return defineAirport(metadata, "large_airport");
        }

        return null;
    }

    private Airport defineAirport(String[] metadata, String decisionType) {
        String type = metadata[2].replace("\"", "");
        String service = metadata[11].replace("\"", "");
        String country = metadata[8].replace("\"", "");

        if (type.equals(decisionType) && service.equals("yes")) {
            String icao = metadata[1].replace("\"", "");
            if (AirportUtils.verifyICAO(icao) == false) {
                return null;
            } else {
                String name = metadata[3].replace("\"", "");
                String lat = metadata[4].replace("\"", "");
                String lon = metadata[5].replace("\"", "");
                String continent = metadata[7].replace("\"", "");

                return new Airport(icao, name, lat, lon, continent, country, getDemand(icao));
            }
        } else {
            return null;
        }
    }

    private String getDemand(String icao) {
        if (!this.extremeDemand.isEmpty() || !this.bigDemand.isEmpty() || !this.mediumDemand.isEmpty()) {
            if (this.extremeDemand.contains(icao)) {
                return "extremeDemand";
            } else if (this.bigDemand.contains(icao)) {
                return "bigDemand";
            } else if (this.mediumDemand.contains(icao)) {
                return "mediumDemand";
            } else {
                return "lessDemand";
            }
        } else {
            return "lessDemand";
        }
    }
}
