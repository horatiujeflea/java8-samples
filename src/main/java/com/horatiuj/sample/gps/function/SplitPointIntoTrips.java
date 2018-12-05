package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Value.Enclosing
public class SplitPointIntoTrips implements Function<SplitPointIntoTrips.Input, List<Trip>> {

    @Override
    public List<Trip> apply(Input input) {
        Objects.requireNonNull(input);

        List<GpsPoint> gpsPoints = input.gpsPoints();
        Optional<Predicate<GpsPoint>> filter = input.filter();

        Objects.requireNonNull(gpsPoints);
        Objects.requireNonNull(filter);

        Map<String, List<GpsPoint>> groupedPoints = gpsPoints
                .stream()
                .filter(filter.orElse((p) -> true))
                .collect(Collectors.groupingBy(GpsPoint::trip));

        return groupedPoints.entrySet()
                .stream()
                .map(e -> ImmutableTrip.builder()
                        .id(e.getKey())
                        .addAllPoints(e.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Value.Immutable
    public interface Input {
        Optional<Predicate<GpsPoint>> filter();
        List<GpsPoint> gpsPoints();
    }


}
