package io.github.flightsimroutes.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.util.ResourceUtils;

import io.github.flightsimroutes.model.Airport;

public class AirportsService {
    ArrayList<String> extremeDemand, bigDemand, mediumDemand;
    
    public AirportsService(ArrayList<String> extremeDemand, ArrayList<String> bigDemand,
            ArrayList<String> mediumDemand) {
        this.extremeDemand = extremeDemand;
        this.bigDemand = bigDemand;
        this.mediumDemand = mediumDemand;
    }

    public ArrayList<Airport> readAirports(int inter,String baseCountry) {
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:airports.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null;) {
                String[] attributes = line.split(",");
                Airport airport = createAirports(attributes, inter,baseCountry);
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

    private Airport createAirports(String[] metadata, int inter,String baseCountry) {
        String country = metadata[8].replace("\"", "");
        String type = metadata[2].replace("\"", "");

        switch (inter) {
            case 1 /*ONLY INTER*/:
                if (!country.equals(baseCountry)) {
                    return defineAirport(metadata, "large_airport");
                }
            case 2 /*BOTH*/:
                if (country.equals(baseCountry)) {
                    if(type.equals("large_airport")){
                        return defineAirport(metadata, "large_airport");
                    }
                    else if(type.equals("medium_airport")){
                        return defineAirport(metadata, "medium_airport");
                    }
                }
                else{
                    return defineAirport(metadata, "large_airport");
                }
            case 3 /*DOMESTIC ONLY*/:
                if (country.equals(baseCountry)) {
                    if(type.equals("large_airport")){
                        return defineAirport(metadata, "large_airport");
                    }
                    else if(type.equals("medium_airport")){
                        return defineAirport(metadata, "medium_airport");
                    }
                }
            default:
                return null;
        }
    }

    private Airport defineAirport(String[] metadata, String decisionType){
        String type = metadata[2].replace("\"", "");
        String service = metadata[11].replace("\"", "");
        String country = metadata[8].replace("\"", "");

        if (type.equals(decisionType) && service.equals("yes")) {
            String icao = metadata[1].replace("\"", "");
            String name = metadata[3].replace("\"", "");
            String lat = metadata[4].replace("\"", "");
            String lon = metadata[5].replace("\"", "");
            String continent = metadata[7].replace("\"", "");

            return new Airport(icao, name, lat, lon, continent, country, getDemand(icao));
        } else {
            return null;
        }
    }

    private String getDemand(String icao){
        if(!this.extremeDemand.isEmpty() || !this.bigDemand.isEmpty() || !this.mediumDemand.isEmpty()){
            for(String extremeDemandIcao:this.extremeDemand){
                if(extremeDemandIcao.equals(icao)){
                    return "extremeDemand";
                }
            }
            for(String BigDemandIcao:this.bigDemand){
                if(BigDemandIcao.equals(icao)){
                    return "bigDemand";
                }
            }
            for(String MediumDemandIcao:this.mediumDemand){
                if(MediumDemandIcao.equals(icao)){
                    return "mediumDemand";
                }
            }
            return "lessDemand";
        }
        else{
            return null;
        }
    }
       
    public Airport searchAirport(ArrayList<Airport> airports, String icao) {
        for (Airport arpts : airports) {
            if (arpts.getIcao().equals(icao)) {
                return arpts;
            }
        }
        return null;
    }
}
