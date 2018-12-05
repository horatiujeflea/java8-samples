package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.Function;

public class MaxTripPoints implements Function<List<Trip>, OptionalInt> {
    @Override
    public OptionalInt apply(List<Trip> trips) {
        Objects.requireNonNull(trips);

        return trips.stream()
                .mapToInt(t -> t.points().size())
                .max();
    }
}

