package io.github.flightsimroutes.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ResourceUtils;

import io.github.flightsimroutes.model.Airport;

public class AirportsService {
    ArrayList<String> extremeDemand = new ArrayList<>(), bigDemand = new ArrayList<>(), mediumDemand = new ArrayList<>();

    public ArrayList<Airport> generateAirportsSchedules(ArrayList<String> extremeDemand, ArrayList<String> bigDemand,
            ArrayList<String> mediumDemand,String baseCountry) {
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;

        return readAirports(baseCountry);
    }

    public ArrayList<Airport> generateAirportsRoutes(String depCountry) {
        return readAirports(depCountry);
    }

    private ArrayList<Airport> readAirports(String baseCountry) {
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:airports.csv");
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

        if (country.equals(baseCountry)) {
            if (type.equals("large_airport")) {
                return defineAirport(metadata, "large_airport");
            } else if (type.equals("medium_airport")) {
                return defineAirport(metadata, "medium_airport");
            }
        } else if (baseCountry.equals("")) {
            if (type.equals("large_airport")) {
                return defineAirport(metadata, "large_airport");
            } else if (type.equals("medium_airport")) {
                return defineAirport(metadata, "medium_airport");
            }
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
            if (verifyICAO(icao) == false) {
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
            for (String extremeDemandIcao : this.extremeDemand) {
                if (extremeDemandIcao.equals(icao)) {
                    return "extremeDemand";
                }
            }
            for (String BigDemandIcao : this.bigDemand) {
                if (BigDemandIcao.equals(icao)) {
                    return "bigDemand";
                }
            }
            for (String MediumDemandIcao : this.mediumDemand) {
                if (MediumDemandIcao.equals(icao)) {
                    return "mediumDemand";
                }
            }
            return "lessDemand";
        } else {
            return "lessDemand";
        }
    }

    private static boolean verifyICAO(String icao) {
        String regex = "^[A-Z]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(icao);

        return matcher.matches();
    }

    public static Airport searchAirport(ArrayList<Airport> airports, String icao) {
        for (Airport airport : airports) {
            if (airport.getIcao().equals(icao)) {
                return airport;
            }
        }
        return null;
    }
}
