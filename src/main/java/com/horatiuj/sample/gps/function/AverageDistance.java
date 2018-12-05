package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.GeoUtil;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageDistance implements Function<List<Trip>, OptionalDouble> {
    @Override
    public OptionalDouble apply(List<Trip> trips) {
        return trips.stream()
                .map(Trip::points)
                .map(ps -> ps.stream()
                        .map(p -> Pair.with(0.0d, p))
                        .reduce((pair1, pair2) -> Pair.with(pair1.getValue0() + GeoUtil.distance(pair1.getValue1(), pair2.getValue1()), pair2.getValue1()))
                        .map(Pair::getValue0)
                )
                .filter(Optional::isPresent)
                .mapToDouble(Optional::get)
                .average();
    }
}
