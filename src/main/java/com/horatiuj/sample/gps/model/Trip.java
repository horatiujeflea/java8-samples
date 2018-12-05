package com.horatiuj.sample.gps.model;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface Trip {
    String id();
    List<GpsPoint> points();
}
