package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.TripGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.OptionalDouble;

class AveragePointsPerTripTest {

    @Test
    void basicTest() throws ParseException {
        AveragePointsPerTrip f = new AveragePointsPerTrip();

        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(TripGenerator.sampleTrip1());
        trips.add(TripGenerator.sampleTrip2());

        OptionalDouble result = f.apply(trips);
        Assert.assertEquals(3.0d, result.getAsDouble(), 0.01);
    }
}