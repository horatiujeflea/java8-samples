package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageSpeed implements Function<List<Trip>, OptionalDouble> {
    @Override
    public OptionalDouble apply(List<Trip> trips) {
        OptionalDouble avgDistanceResult = new AverageDistance().apply(trips);
        OptionalDouble avgTimeResult = new AverageTime().apply(trips);
        if (avgDistanceResult.isPresent() && avgTimeResult.isPresent()) {
            return OptionalDouble.of(avgDistanceResult.getAsDouble() / avgTimeResult.getAsDouble());
        } else {
            return OptionalDouble.empty();
        }
    }
}
