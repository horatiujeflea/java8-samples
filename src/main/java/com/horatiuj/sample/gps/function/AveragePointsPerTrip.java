package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AveragePointsPerTrip implements Function<List<Trip>, OptionalDouble> {
    @Override
    public OptionalDouble apply(List<Trip> trips) {
        Objects.requireNonNull(trips);

        return trips.stream()
                .mapToInt(t -> t.points().size())
                .average();
    }
}
