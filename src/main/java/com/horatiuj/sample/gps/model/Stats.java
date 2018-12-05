package com.horatiuj.sample.gps.model;

import org.immutables.value.Value;

@Value.Immutable
public interface Stats {
    Double avgSpeed();
    Double avgTimeBetweenPoints();
    Double avgDistanceBetweenPoints();
    Double avgPointsPerTrip();
    Integer maxTripPoints();
    Integer minTripPoints();
    Long pointsProcessed();
    Long tripsProcessed();
}
