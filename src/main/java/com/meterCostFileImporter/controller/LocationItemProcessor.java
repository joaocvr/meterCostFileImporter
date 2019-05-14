package com.meterCostFileImporter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.meterCostFileImporter.entity.Location;

public class LocationItemProcessor implements ItemProcessor<Location, Location> {

    private static final Logger log = LoggerFactory.getLogger(LocationItemProcessor.class);

    @Override
    public Location process(final Location location) throws Exception {

        Location transformedLocation = new Location();
        transformedLocation.setCountry(location.getCountry().toUpperCase());
        transformedLocation.setState(location.getState().toUpperCase());
        transformedLocation.setCity(location.getCity().toUpperCase());
        transformedLocation.setNeighborhood(location.getNeighborhood().toUpperCase());
        transformedLocation.setCost(location.getCost().setScale(2));

        log.info("Converting (" + location + ") into (" + transformedLocation + ")");

        return transformedLocation;
    }

}