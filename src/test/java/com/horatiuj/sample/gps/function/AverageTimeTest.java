package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.UUID;

class AverageTimeTest {

    private AverageTime f;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");


    @BeforeEach
    void setUp() {
        f = new AverageTime();
    }

    @Test
    void oneTripTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        Double lat = 46.775004;
        Double lon = 23.587317;

        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:00:00.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:00.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:30.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:45.400-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(105.0d, result.getAsDouble(), 0.01);
    }

    @Test
    void multipleTripsTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        String tripId = UUID.randomUUID().toString();
        Double lat = 46.775004;
        Double lon = 23.587317;

        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:00:00.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:00.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:30.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:01:45.400-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .build());

        trips.add(ImmutableTrip.builder()
                .id(tripId)
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:08:10.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .addPoints(ImmutableGpsPoint.builder()
                        .trip(tripId)
                        .timestamp(stringToDate("2018-12-03T08:18:00.000-00:00"))
                        .lat(lat)
                        .lon(lon)
                        .build())
                .build());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(347.5d, result.getAsDouble(), 0.01);
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

    private Long stringToDate(String date) throws ParseException {
        return df.parse(date).getTime();
    }
}