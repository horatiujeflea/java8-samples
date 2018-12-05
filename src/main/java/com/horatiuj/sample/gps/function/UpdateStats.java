package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.Stats;
import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.GeoUtil;
import org.immutables.value.Value;
import org.javatuples.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;

@Value.Enclosing
public class UpdateStats implements Function<UpdateStats.Input, Stats> {

    @Override
    public Stats apply(Input input) {
        Objects.requireNonNull(input);

        List<GpsPoint> gpsPoints = input.gpsPoints();
        Optional<Stats> stats = input.stats();

        Objects.requireNonNull(gpsPoints);
        Objects.requireNonNull(stats);

        List<Trip> trips = new SplitPointIntoTrips().apply(ImmutableSplitPointIntoTrips.Input.builder()
                .gpsPoints(gpsPoints)
                .filter(new IsPointValid())
                .build());


        return null;
    }

    @Value.Immutable
    public interface Input {
        Optional<Stats> stats();
        List<GpsPoint> gpsPoints();
    }

}
