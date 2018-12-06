package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableStats;
import com.horatiuj.sample.gps.model.Stats;

import java.util.function.BiFunction;

public class MergeStats implements BiFunction<Stats, Stats, Stats> {
    @Override
    public Stats apply(Stats stats1, Stats stats2) {
        if (stats1 == null) {
            return stats2;
        }

        if (stats2 == null) {
            return stats1;
        }

        return ImmutableStats.builder()
                .avgSpeed(avg(stats1.avgSpeed(), stats2.avgSpeed(), stats1.tripsProcessed(), stats2.tripsProcessed()))
                .avgTimeBetweenPoints(avg(stats1.avgTimeBetweenPoints(), stats2.avgTimeBetweenPoints(), stats1.tripsProcessed(), stats2.tripsProcessed()))
                .avgDistanceBetweenPoints(avg(stats1.avgDistanceBetweenPoints(), stats2.avgDistanceBetweenPoints(), stats1.tripsProcessed(), stats2.tripsProcessed()))
                .avgPointsPerTrip(avg(stats1.avgPointsPerTrip(), stats2.avgPointsPerTrip(), stats1.tripsProcessed(), stats2.tripsProcessed()))
                .maxTripPoints(Math.max(stats1.maxTripPoints(), stats2.maxTripPoints()))
                .minTripPoints(Math.min(stats1.minTripPoints(), stats2.minTripPoints()))
                .pointsProcessed(stats1.pointsProcessed() + stats2.pointsProcessed())
                .tripsProcessed(stats1.tripsProcessed() + stats2.tripsProcessed())
                .build();
    }

    private Double avg(Double value1, Double value2, Long count1, Long count2) {
        Double totalCount = (double) count1 + count2;
        return Math.round((count1 / totalCount * value1 + count2 / totalCount * value2) * 100.0) / 100.0;
    }
}
