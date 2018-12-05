package com.horatiuj.sample.gps.util;

import com.horatiuj.sample.gps.model.GpsPoint;
import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GeoUtilTest {

    @Test
    void distanceTest() {
        double distance = GeoUtil.distance(ImmutableGpsPoint.builder()
                        .lat(46.771891)
                        .lon(23.596406)
                        .timestamp(new Date().getTime())
                        .trip(UUID.randomUUID().toString())
                        .build(),
                ImmutableGpsPoint.builder()
                        .lat(46.769971)
                        .lon(23.589584)
                        .timestamp(new Date().getTime())
                        .trip(UUID.randomUUID().toString())
                        .build());
        Assert.assertEquals(561.71d, distance, 0.01);
    }
}