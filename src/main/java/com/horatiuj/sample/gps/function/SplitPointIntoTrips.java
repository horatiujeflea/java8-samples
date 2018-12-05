package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SplitPointIntoTrips implements Function<List<GpsPoint>, List<Trip>> {

    @Override
    public List<Trip> apply(List<GpsPoint> gpsPoints) {
        Objects.requireNonNull(gpsPoints);

        Map<String, List<GpsPoint>> groupedPoints = gpsPoints
                .stream()
                .collect(Collectors.groupingBy(GpsPoint::trip));

        return groupedPoints.entrySet()
                .stream()
                .map(e -> ImmutableTrip.builder()
                        .id(e.getKey())
                        .addAllPoints(e.getValue())
                        .build())
                .collect(Collectors.toList());
    }

}
