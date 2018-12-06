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

/**
 * Having a list of gps data point, generate statistics @{@link Stats} from them.
 * Compatible with stream processing by providing actual statistics as input.
 */
@Value.Enclosing
public class UpdateStats implements Function<UpdateStats.Input, Optional<Stats>> {

    @Override
    public Optional<Stats> apply(Input input) {
        Objects.requireNonNull(input);

        List<GpsPoint> gpsPoints = input.gpsPoints();
        Optional<Stats> prevStats = input.stats();

        Objects.requireNonNull(gpsPoints);
        Objects.requireNonNull(prevStats);

        List<Trip> trips = new SplitPointIntoTrips().apply(ImmutableSplitPointIntoTrips.Input.builder()
                .gpsPoints(gpsPoints)
                .filter(new IsPointValid())
                .build());

        if (trips.isEmpty()) {
            return prevStats;
        }

        ImmutableStats.Builder currentStatsBuilder = ImmutableStats.builder();
        new AverageDistance().apply(trips).ifPresent(currentStatsBuilder::avgDistanceBetweenPoints);
        new AverageTime().apply(trips).ifPresent(currentStatsBuilder::avgTimeBetweenPoints);
        new AverageSpeed().apply(trips).ifPresent(currentStatsBuilder::avgSpeed);
        new AveragePointsPerTrip().apply(trips).ifPresent(currentStatsBuilder::avgPointsPerTrip);
        new MaxTripPoints().apply(trips).ifPresent(currentStatsBuilder::maxTripPoints);
        new MinTripPoints().apply(trips).ifPresent(currentStatsBuilder::minTripPoints);
        Stats currentStats = currentStatsBuilder.tripsProcessed((long) trips.size())
                .pointsProcessed((long) trips.stream().mapToInt(t -> t.points().size()).sum())
                .build();

        return Optional.of(prevStats.map(ps -> new MergeStats().apply(ps, currentStats)).orElse(currentStats));
    }

    @Value.Immutable
    public interface Input {
        Optional<Stats> stats();
        List<GpsPoint> gpsPoints();
    }

}
