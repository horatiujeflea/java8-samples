package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.TripGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

class MinTripPointsTest {

    @Test
    void basicTest() throws ParseException {
        MinTripPoints f = new MinTripPoints();
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(TripGenerator.sampleTrip1());
        trips.add(TripGenerator.sampleTrip2());

        Assert.assertEquals(2, f.apply(trips).getAsInt());
    }
}