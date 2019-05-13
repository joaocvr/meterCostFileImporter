package com.meterCostFileImporter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.meterCostFileImporter.entity.Location;

public class LocationController implements ItemProcessor<Location, Location> {

    private static final Logger log = LoggerFactory.getLogger(LocationController.class);

    @Override
    public Location process(final Location location) throws Exception {
        final String country = location.getCountry().toUpperCase();
        final String state = location.getState().toUpperCase();

        Location transformedLocation = new Location();
        transformedLocation.setCountry(country);
        transformedLocation.setState(state);

        log.info("Converting (" + location + ") into (" + transformedLocation + ")");

        return transformedLocation;
    }

}