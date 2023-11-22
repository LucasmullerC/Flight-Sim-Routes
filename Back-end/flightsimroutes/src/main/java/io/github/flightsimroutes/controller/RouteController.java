package io.github.flightsimroutes.controller;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.flightsimroutes.model.entity.Airport;
import io.github.flightsimroutes.model.entity.Route;
import io.github.flightsimroutes.model.request.RandomRouteRequest;
import io.github.flightsimroutes.model.request.ScheduleRequest;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.service.DemandService;
import io.github.flightsimroutes.service.RouteService;
import io.github.flightsimroutes.util.GenerateFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

@RestController
public class RouteController {

    /**
     * Generate a random route or a list of random routes based on the user request.
     *
     * @param request Object containing the request parameters.
     * @return Route list generated.
     */
    @PostMapping("/random-route")
    public ResponseEntity<Object> randomRoute(@RequestBody final RandomRouteRequest request) {
        AirportsService readAirports = new AirportsService();
        ArrayList<Airport> airportsList = readAirports.generateAirportsRoutes(request.getDepCountry());

        RouteService routeService = new RouteService();
        ArrayList<Route> routes = routeService.generateRoute(airportsList, request.getDepAirport(),
                request.getArrAirport(),
                request.getDepCountry(), request.getArrCountry(), request.getMaxDistance(), request.getMinDistance(),
                request.isContinuous(), request.getQuantity());
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    /**
     * Generate route schedules based on the user request.
     * The generated files can be uploaded at the PHPvms admin panel.
     *
     * @param request Object containing the request parameters.
     * @return a zip file containing two CSV and one HTML file "routes.csv",
     *         "airports.csv" and "RouteMap.html".
     */
    @PostMapping("/generate-schedules")
    public ResponseEntity<byte[]> generateSchedules(@RequestBody final ScheduleRequest request) {
        AirportsService airportsService = new AirportsService();
        ArrayList<Airport> airportsList = airportsService.generateAirportsSchedules(request.getExtremeDemand(),
                request.getBigDemand(), request.getMediumDemand(), request.getBaseCountry());

        DemandService demandService = new DemandService(request.getAirline(), request.getAircraft(), airportsList,
                request.getFlight_number(), request.getHubs(), request.isRepetitive(), request.getRouteDensity());
        ArrayList<Route> routes = demandService.createDemand(request.getInternational(), request.getBaseCountry());

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(baos);

            zipOutputStream = GenerateFiles.addToZip(zipOutputStream, GenerateFiles.generateFlightsCsv(routes), "routes.csv");
            zipOutputStream = GenerateFiles.addToZip(zipOutputStream, GenerateFiles.generateAirportsCsv(airportsList),
                    "airports.csv");
            zipOutputStream = GenerateFiles.addToZip(zipOutputStream,
                    String.format("<meta http-equiv=\"refresh\" charset=\"utf-8\" content=\"0; url=%s\" />",
            GenerateFiles.generateGrateCircleMapper(routes)), "RouteMap.html");
            zipOutputStream.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "schedules.zip");

            return new ResponseEntity<>(baos.toByteArray(), headers, 200);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
