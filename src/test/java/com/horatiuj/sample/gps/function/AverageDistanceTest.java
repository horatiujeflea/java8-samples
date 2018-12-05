package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.UUID;

class AverageDistanceTest {

    private AverageDistance f;

    @BeforeEach
    void setUp() {
        f = new AverageDistance();
    }

    @Test
    void oneTripTest() {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        long now = new Date().getTime();
        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.775004)
                        .lon(23.587317)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.775502)
                        .lon(23.587261)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.776195)
                        .lon(23.587201)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.776384)
                        .lon(23.587180)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(153.8d, result.getAsDouble(), 0.01);
    }

    @Test
    void multipleTripsTest() {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        long now = new Date().getTime();
        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.775004)
                        .lon(23.587317)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.775502)
                        .lon(23.587261)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.776195)
                        .lon(23.587201)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.776384)
                        .lon(23.587180)
                        .build())
                .build());

        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.776855)
                        .lon(23.607117)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(now)
                        .lat(46.780095)
                        .lon(23.624429)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(760.21d, result.getAsDouble(), 0.01);
    }

    @Test
    void noTripsTest() {
        ArrayList<Trip> trips = new ArrayList<>();
        trips.add(ImmutableTrip.builder()
                .id(UUID.randomUUID().toString())
                .build()
        );
        OptionalDouble result = f.apply(trips);
        result.ifPresent((d) -> Assert.fail());
    }
}