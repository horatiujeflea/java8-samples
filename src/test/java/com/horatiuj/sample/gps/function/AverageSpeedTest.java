package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.UUID;

import static com.horatiuj.sample.gps.util.TestUtil.stringToDate;
import static org.junit.jupiter.api.Assertions.*;

class AverageSpeedTest {

    private AverageSpeed f;

    @BeforeEach
    void setUp() {
        f = new AverageSpeed();
    }

    @Test
    void oneTripTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(46.775004)
                        .lon(23.587317)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:17.000-00:00"))
                        .lat(46.775502)
                        .lon(23.587261)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:20.000-00:00"))
                        .lat(46.776195)
                        .lon(23.587201)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:22.000-00:00"))
                        .lat(46.776384)
                        .lon(23.587180)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(12.81d, result.getAsDouble(), 0.01);
    }

    @Test
    void multipleTripsTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(46.775004)
                        .lon(23.587317)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:17.000-00:00"))
                        .lat(46.775502)
                        .lon(23.587261)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:20.000-00:00"))
                        .lat(46.776195)
                        .lon(23.587201)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:22.000-00:00"))
                        .lat(46.776384)
                        .lon(23.587180)
                        .build())
                .build());

        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(46.776855)
                        .lon(23.607117)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:20.000-00:00"))
                        .lat(46.780095)
                        .lon(23.624429)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(69.11d, result.getAsDouble(), 0.01);
    }

}