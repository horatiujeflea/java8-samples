package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.ImmutableStats;
import com.horatiuj.sample.gps.model.Stats;
import com.horatiuj.sample.gps.model.Trip;
import org.immutables.value.Value;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        Stats currentStats = ImmutableStats.builder()
                .avgDistanceBetweenPoints(new AverageDistance().apply(trips).orElse(0.0d))
                .avgTimeBetweenPoints(new AverageTime().apply(trips).orElse(0.0d))
                .avgSpeed(new AverageSpeed().apply(trips).orElse(0.0d))
                .avgPointsPerTrip(new AveragePointsPerTrip().apply(trips).orElse(0.0d))
                .maxTripPoints(new MaxTripPoints().apply(trips).orElse(0))
                .minTripPoints(new MinTripPoints().apply(trips).orElse(0))
                .tripsProcessed((long) trips.size())
                .pointsProcessed((long) trips.stream().mapToInt(t -> t.points().size()).sum())
                .build();

        return stats.map(s -> new MergeStats().apply(s, currentStats)).orElse(currentStats);

    }

    @Value.Immutable
    public interface Input {
        Optional<Stats> stats();
        List<GpsPoint> gpsPoints();
    }

}
