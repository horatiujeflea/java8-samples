package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;
import org.javatuples.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageTime implements Function<List<Trip>, OptionalDouble> {

    @Override
    public OptionalDouble apply(List<Trip> trips) {
        Objects.requireNonNull(trips);

        return trips.stream()
                .map(Trip::points)
                .map(ps -> ps.stream()
                        .map(p -> Pair.with(0L, p))
                        .reduce((pair1, pair2) ->
                                Pair.with(
                                        pair1.getValue0() + (pair2.getValue1().timestamp() - pair1.getValue1().timestamp()),
                                        pair2.getValue1()
                                ))
                        .map(Pair::getValue0)
                        .map(time -> Math.round(time / 1000d))
                )
                .filter(Optional::isPresent)
                .mapToDouble(Optional::get)
                .average();
    }
}
