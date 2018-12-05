package com.horatiuj.sample.gps.model;

import org.immutables.value.Value;

@Value.Immutable
public interface GpsPoint {
    Double lat();
    Double lon();
    Long timestamp();
    Integer accuracy();
    String trip();
}
