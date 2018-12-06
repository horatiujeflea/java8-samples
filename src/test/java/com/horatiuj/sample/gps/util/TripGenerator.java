package com.horatiuj.sample.gps.util;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;

import java.text.ParseException;
import java.util.UUID;

import static com.horatiuj.sample.gps.util.TestUtil.stringToDate;

public class TripGenerator {

    public static Trip sampleTrip1() throws ParseException {
        String tripId = UUID.randomUUID().toString();
        return ImmutableTrip.builder()
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
                .build();
    }

    public static Trip sampleTrip2() throws ParseException {
        String tripId = UUID.randomUUID().toString();
        return ImmutableTrip.builder()
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
                .build();
    }
}