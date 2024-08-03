package io.github.flightsimroutes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.util.StringUtils;

public class OpenSkyNetworkService {

    public ArrayList<Route> getFlights(String unixBegin, String unixEnd) {
        try {
            ArrayList<Route> flights = new ArrayList<>();
            String url = "https://opensky-network.org/api/flights/all?begin=" + unixBegin + "&end=" + unixEnd;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                flights = createRoutes(response);
                return flights;
            } else {
                System.out.println("An error occured trying to call OpenSky API: "+responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Route> createRoutes(StringBuilder response) {
        try {
            ArrayList<Route> flights = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode;
            jsonNode = objectMapper.readTree(response.toString());

            for (JsonNode flightNode : jsonNode) {
                String estDepartureAirport = flightNode.get("estDepartureAirport").asText();
                String estArrivalAirport = flightNode.get("estArrivalAirport").asText();
                String callsign = flightNode.get("callsign").asText();
                String airline = StringUtils.getFirstCharacters(callsign, 3);
                String icao24 = flightNode.get("icao24").asText();

                if(!estArrivalAirport.equals("null") && !estArrivalAirport.equals("null")){
                    Route newRoute = new Route(airline, callsign, estDepartureAirport, estArrivalAirport, icao24, 0,"0","0","0");
                    flights.add(newRoute);
                }
            }
            return flights;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
