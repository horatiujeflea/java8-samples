package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableStats;
import com.horatiuj.sample.gps.model.Stats;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.horatiuj.sample.gps.util.TestUtil.stringToDate;

class UpdateStatsTest {

    private UpdateStats f;

    @BeforeEach
    void setUp() {
        f = new UpdateStats();
    }

    @Test
    void noPreviousStatsTest() throws ParseException {
        String tripId1 = UUID.randomUUID().toString();
        String tripId2 = UUID.randomUUID().toString();

        Stats stats = f.apply(ImmutableUpdateStats.Input.builder()
                .stats(Optional.empty())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId1)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(46.775004)
                        .lon(23.587317)
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId1)
                        .timestamp(stringToDate("2018-12-03T08:08:17.000-00:00"))
                        .lat(46.775502)
                        .lon(23.587261)
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId1)
                        .timestamp(stringToDate("2018-12-03T08:08:20.000-00:00"))
                        .lat(46.776195)
                        .lon(23.587201)
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId1)
                        .timestamp(stringToDate("2018-12-03T08:08:22.000-00:00"))
                        .lat(46.776384)
                        .lon(23.587180)
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId2)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(46.776855)
                        .lon(23.607117)
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .trip(tripId2)
                        .timestamp(stringToDate("2018-12-03T08:08:20.000-00:00"))
                        .lat(46.780095)
                        .lon(23.624429)
                        .build())
                .build()).get();

        Assert.assertEquals(69.11d, stats.avgSpeed(), 0.01);
        Assert.assertEquals(11.0d, stats.avgTimeBetweenPoints(), 0.01);
        Assert.assertEquals(760.21, stats.avgDistanceBetweenPoints(), 0.01);
        Assert.assertEquals(3.0, stats.avgPointsPerTrip(), 0.01);
        Assert.assertEquals(4, stats.maxTripPoints(), 0.01);
        Assert.assertEquals(2, stats.minTripPoints(), 0.01);
        Assert.assertEquals(6, stats.pointsProcessed(), 0.01);
        Assert.assertEquals(2, stats.tripsProcessed(), 0.01);
    }

    @Test
    void emptyTest() {
        f.apply(ImmutableUpdateStats.Input.builder()
                .stats(Optional.empty())
                .gpsPoints(new ArrayList<>())
                .build()).ifPresent((o) -> Assert.fail());

        f.apply(ImmutableUpdateStats.Input.builder()
                .stats(Optional.empty())
                .build()).ifPresent((o) -> Assert.fail());
    }
}