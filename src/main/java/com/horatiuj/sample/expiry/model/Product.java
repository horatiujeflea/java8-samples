package com.horatiuj.sample.expiry.model;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Value.Immutable
public interface Product {
    String id();
    @Nullable
    String name();
    @Nullable String type();
    @Nullable Date productionDate();
    Date expiryDate();
}
