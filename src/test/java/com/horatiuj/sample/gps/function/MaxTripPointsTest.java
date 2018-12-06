package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import com.horatiuj.sample.gps.model.ImmutableTrip;
import com.horatiuj.sample.gps.model.Trip;
import com.horatiuj.sample.gps.util.TripGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

import static com.horatiuj.sample.gps.util.TestUtil.stringToDate;
import static org.junit.jupiter.api.Assertions.*;

class MaxTripPointsTest {

    @Test
    void basicTest() throws ParseException {
        MaxTripPoints f = new MaxTripPoints();
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(TripGenerator.sampleTrip1());
        trips.add(TripGenerator.sampleTrip2());

        Assert.assertEquals(4, f.apply(trips).getAsInt());
    }
}