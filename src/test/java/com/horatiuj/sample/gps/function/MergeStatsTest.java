package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableStats;
import com.horatiuj.sample.gps.model.Stats;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeStatsTest {

    @Test
    void apply() {
        MergeStats f = new MergeStats();

        ImmutableStats stats1 = ImmutableStats.builder()
                .avgSpeed(60.0)
                .avgTimeBetweenPoints(4.0)
                .avgDistanceBetweenPoints(100.0)
                .avgPointsPerTrip(330.0)
                .maxTripPoints(2350)
                .minTripPoints(60)
                .pointsProcessed(80000L)
                .tripsProcessed(160L)
                .build();

        ImmutableStats stats2 = ImmutableStats.builder()
                .avgSpeed(40.0)
                .avgTimeBetweenPoints(8.0)
                .avgDistanceBetweenPoints(150.0)
                .avgPointsPerTrip(530.0)
                .maxTripPoints(1350)
                .minTripPoints(140)
                .pointsProcessed(20000L)
                .tripsProcessed(40L)
                .build();

        Stats mergedStats = f.apply(stats1, stats2);

        Assert.assertEquals(ImmutableStats.builder()
                .avgSpeed(56.0)
                .avgTimeBetweenPoints(4.8)
                .avgDistanceBetweenPoints(110.0)
                .avgPointsPerTrip(370.0)
                .maxTripPoints(2350)
                .minTripPoints(60)
                .pointsProcessed(100000L)
                .tripsProcessed(200L)
                .build(), mergedStats);
    }
}