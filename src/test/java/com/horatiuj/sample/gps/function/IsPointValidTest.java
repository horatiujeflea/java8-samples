package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.ImmutableGpsPoint;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

class IsPointValidTest {

    private IsPointValid p;

    @BeforeEach
    void setUp() {
        p = new IsPointValid();
    }

    @Test
    void accuracyTest() {
        Assert.assertTrue(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(23.587317)
                .trip(UUID.randomUUID().toString())
                .timestamp(new Date().getTime())
                .build()));

        Assert.assertTrue(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(23.587317)
                .accuracy(8)
                .trip(UUID.randomUUID().toString())
                .timestamp(new Date().getTime())
                .build()));

        Assert.assertFalse(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(23.587317)
                .accuracy(11)
                .trip(UUID.randomUUID().toString())
                .timestamp(new Date().getTime())
                .build()));
    }

    @Test
    void tripTest() {
        Assert.assertTrue(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(23.587317)
                .trip("test")
                .accuracy(8)
                .timestamp(new Date().getTime())
                .build()));
    }

    @Test
    void latLonTest() {
        Assert.assertTrue(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(23.587317)
                .trip(UUID.randomUUID().toString())
                .accuracy(8)
                .timestamp(new Date().getTime())
                .build()));

        Assert.assertFalse(p.test(ImmutableGpsPoint.builder()
                .lat(469.775004)
                .lon(23.587317)
                .trip(UUID.randomUUID().toString())
                .accuracy(8)
                .timestamp(new Date().getTime())
                .build()));

        Assert.assertFalse(p.test(ImmutableGpsPoint.builder()
                .lat(46.775004)
                .lon(234.587317)
                .trip(UUID.randomUUID().toString())
                .accuracy(8)
                .timestamp(new Date().getTime())
                .build()));
    }
}