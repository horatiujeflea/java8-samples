package com.horatiuj.sample.gps.function;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

class UpdateStatsTest {

    private UpdateStats f;

    @BeforeEach
    void setUp() {
        f = new UpdateStats();
    }

    @Test
    void emptyTest() {
        f.apply(ImmutableUpdateStats.Input.builder()
                .stats(Optional.empty())
                .gpsPoints(new ArrayList<>())
                .build()).ifPresent((o) -> Assert.fail());

        f.apply(ImmutableUpdateStats.Input.builder()
                .stats(Optional.empty())
                .build()).ifPresent((o) -> Assert.fail());
    }
}