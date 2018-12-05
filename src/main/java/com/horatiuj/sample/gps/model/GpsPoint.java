package com.horatiuj.sample.gps.model;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
public interface GpsPoint {
    Double lat();
    Double lon();
    Long timestamp();
    @Nullable
    Integer accuracy();
    String trip();
}
