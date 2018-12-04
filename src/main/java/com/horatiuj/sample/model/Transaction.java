package com.horatiuj.sample.model;

import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
public interface Transaction {
    String id();
    Double amount();
    String fromCustomerId();
    String toCustomerId();
    Date date();
}
