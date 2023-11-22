package io.github.flightsimroutes.util;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import io.github.flightsimroutes.model.Airport;

public class AirportUtils {
        public static boolean verifyICAO(String icao) {
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
