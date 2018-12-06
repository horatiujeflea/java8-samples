package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.TripGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.OptionalDouble;

class AverageSpeedTest {

    private AverageSpeed f;

    @BeforeEach
    void setUp() {
        f = new AverageSpeed();
    }

    @Test
    void oneTripTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(TripGenerator.sampleTrip1());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(12.81d, result.getAsDouble(), 0.01);
    }

    @Test
    void multipleTripsTest() throws ParseException {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(TripGenerator.sampleTrip1());
        trips.add(TripGenerator.sampleTrip2());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(69.11d, result.getAsDouble(), 0.01);
    }

}