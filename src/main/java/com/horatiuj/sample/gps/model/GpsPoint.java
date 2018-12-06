package com.horatiuj.sample.gps.model;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface GpsPoint {
    Double lat();
    Double lon();
    Long timestamp();
    Optional<Integer> accuracy();
    String trip();
}
