package com.horatiuj.sample.expiry.model;

import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
public interface Product {
    String id();
    String name();
    String type();
    Date productionDate();
    Date expiryDate();
}
