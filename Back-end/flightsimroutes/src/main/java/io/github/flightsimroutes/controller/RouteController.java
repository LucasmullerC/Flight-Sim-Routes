package io.github.flightsimroutes.controller;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.flightsimroutes.model.Airport;
import io.github.flightsimroutes.model.RandomRouteRequest;
import io.github.flightsimroutes.model.Route;
import io.github.flightsimroutes.model.ScheduleRequest;
import io.github.flightsimroutes.service.AirportsService;
import io.github.flightsimroutes.service.DemandService;
import io.github.flightsimroutes.service.RouteService;
import io.github.flightsimroutes.util.GenerateFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

@RestController
public class RouteController {

    @PostMapping("/RandomRoute")
    public ResponseEntity<Object> randomRoute(@RequestBody final RandomRouteRequest request) {
        ArrayList<Airport> airportsList = new ArrayList<>();
        AirportsService readAirports = new AirportsService();
        airportsList = readAirports.generateAirportsRoutes(request.getDepCountry());
        
        RouteService routeService = new RouteService(airportsList);
        ArrayList<Route> routes = routeService.generateRoute(request.getDepAirport(), request.getArrAirport(), request.getDepCountry(), request.getArrCountry(), request.getMaxDistance(), request.getMinDistance(), request.isContinous(), request.getQuantity());
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping("/GenerateSchedules")
    public ResponseEntity<byte[]> generateSchedules(@RequestBody final ScheduleRequest request) {
        ArrayList<Airport> airportsList = new ArrayList<>();
        AirportsService readAirports = new AirportsService();
        airportsList = readAirports.generateAirportsSchedules(request.getExtremeDemand(), request.getBigDemand(),
                request.getMediumDemand(), request.getBaseCountry());

        DemandService generateSchedule = new DemandService(request.getAirline(), request.getAircraft(), airportsList,
                request.getFlight_number(),
                request.getHubs(), request.isRepetitive(), request.getQuantity());
        ArrayList<Route> routes = generateSchedule.createDemand(request.getInternational(), request.getBaseCountry());

        GenerateFiles generateFiles = new GenerateFiles();

        try {
            String metaLink = "<meta http-equiv=\"refresh\" charset=\"utf-8\" content=\"0; url=";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(baos);

            zipOutputStream = createFiles(zipOutputStream, generateFiles.generateFlightsCsv(routes), "routes.csv");
            zipOutputStream = createFiles(zipOutputStream, generateFiles.generateAirportsCsv(airportsList),
                    "airports.csv");
            zipOutputStream = createFiles(zipOutputStream,
                    metaLink + generateFiles.generateGrateCircleMapper(routes) + "\" />", "RouteMap.html");
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

    private ZipOutputStream createFiles(ZipOutputStream zipOutputStream, String content, String name) {
        try {
            zipOutputStream.putNextEntry(new ZipEntry(name));
            zipOutputStream.write(content.getBytes());
            zipOutputStream.closeEntry();
            return zipOutputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // GET Generate random route (ICAO - DEP airport(not mandatory),ICAO - ARR
    // airport (not mandatory), AIRCRAFT - Aircraft, INT - miles(?))
}
