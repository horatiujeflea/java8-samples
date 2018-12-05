package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.Trip;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageTime implements Function<List<Trip>, OptionalDouble> {

    @Override
    public OptionalDouble apply(List<Trip> trips) {
        return trips.stream()
                .map(Trip::points)
                .map(ps -> ps.stream()
                        .mapToLong(GpsPoint::timestamp)
                        .average())
                .filter(OptionalDouble::isPresent)
                .mapToDouble(OptionalDouble::getAsDouble)
                .average();
    }
}
