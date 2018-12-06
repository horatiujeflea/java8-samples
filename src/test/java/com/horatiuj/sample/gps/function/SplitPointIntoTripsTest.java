package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.Trip;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SplitPointIntoTripsTest {

    @Test
    void basicTest() {
        SplitPointIntoTrips f = new SplitPointIntoTrips();
        List<Trip> trips = f.apply(ImmutableSplitPointIntoTrips.Input.builder()
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip1")
                        .timestamp(new Date().getTime())
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip1")
                        .timestamp(new Date().getTime())
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip2")
                        .timestamp(new Date().getTime())
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip4")
                        .timestamp(new Date().getTime())
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip4")
                        .timestamp(new Date().getTime())
                        .build())
                .addGpsPoints(ImmutableGpsPoint.builder()
                        .lat(46.775004)
                        .lon(23.587317)
                        .trip("trip4")
                        .timestamp(new Date().getTime())
                        .build())
                .build());

        Assert.assertEquals(2, trips.stream().filter(t -> t.id().equals("trip1")).findFirst().get().points().size());
        Assert.assertEquals(1, trips.stream().filter(t -> t.id().equals("trip2")).findFirst().get().points().size());
        Assert.assertEquals(3, trips.stream().filter(t -> t.id().equals("trip4")).findFirst().get().points().size());
    }
}