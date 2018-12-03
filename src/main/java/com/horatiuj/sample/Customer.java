package com.horatiuj.sample;

import org.immutables.value.Value;

@Value.Immutable
public interface Customer {
    String name();
    Integer age();
}
