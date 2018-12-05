package com.horatiuj.sample.expiry.model;

import com.sun.istack.internal.Nullable;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
public interface Product {
    String id();
    @Nullable  String name();
    @Nullable String type();
    @Nullable Date productionDate();
    Date expiryDate();
}
