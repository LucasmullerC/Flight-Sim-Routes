package io.github.flightsimroutes.util;

import java.util.ArrayList;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.Route;

public class GenerateFiles {

    public String generateGrateCircleMapper(ArrayList<Route> route) {
        String link = "https://www.greatcirclemap.com/?routes=";

        for (Route rt : route) {
            link += rt.getDpt_airport() + "-" + rt.getArr_airport() + "%2C%20";
        }
        return link;
    }

    public String generateAirportsCsv(ArrayList<Airport> database) {
        String airportCsv = "";

        airportCsv += "icao,iata,name,location,country,timezone,hub,lat,lon,ground_handling_cost,fuel_100ll_cost,fuel_jeta_cost,fuel_mogas_cost,notes\r\n";
        for (Airport airport : database) {
            airportCsv += airport.getIcao()
                    + ",,\"" + airport.getName()
                    + "\",\"\",,,," + airport.getLat() + "," + airport.getLon() + ",,,,,\r\n";
        }

        return airportCsv;
    }

    public String generateFlightsCsv(ArrayList<Route> routes) {
        String flightsCsv = "";

        flightsCsv += "airline,flight_number,route_code,callsign,route_leg,dpt_airport,arr_airport,alt_airport,days,dpt_time,arr_time,level,distance,flight_time,flight_type,load_factor,load_factor_variance,pilot_pay,route,notes,start_date,end_date,active,subfleets,fares,fields\r\n";
        for (Route route : routes) {
            flightsCsv += route.getAirline() + "," + route.getFlight_number()
                    + ",,,," + route.getDpt_airport() + "," + route.getArr_airport()
                    + ",,,,,," + route.getDistance() + "," + route.getFlight_time() + ",J,,,,,,,,1," + route.getSubfleets()
                    + ",,\r\n";
        }

        return flightsCsv;
    }

}
