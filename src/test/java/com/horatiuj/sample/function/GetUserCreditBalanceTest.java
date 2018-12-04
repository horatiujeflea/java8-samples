package com.horatiuj.sample.function;

import com.horatiuj.sample.model.ImmutableTransaction;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetUserCreditBalanceTest {

    private GetUserCreditBalance f;

    @BeforeEach
    void setUp() {
        f = new GetUserCreditBalance();
    }

    @Test
    void basicTest() {
        ImmutableInput input = ImmutableInput.builder()
                .customerId("1")
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(50.0)
                        .fromCustomerId("2")
                        .toCustomerId("1")
                        .build())
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(25.0)
                        .fromCustomerId("1")
                        .toCustomerId("3")
                        .build())
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(83.0)
                        .fromCustomerId("4")
                        .toCustomerId("1")
                        .build())
                .build();
        double balance = f.apply(input);
        Assert.assertEquals(108.0d, balance, 1e-15);
    }

    @Test
    void missingCustomerIdTest() {
        ImmutableInput input = ImmutableInput.builder()
                .customerId("1")
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(50.0)
                        .fromCustomerId("2")
                        .toCustomerId("5")
                        .build())
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(25.0)
                        .fromCustomerId("6")
                        .toCustomerId("3")
                        .build())
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(83.0)
                        .fromCustomerId("4")
                        .toCustomerId("8")
                        .build())
                .build();
        double balance = f.apply(input);
        Assert.assertEquals(0.0d, balance, 1e-15);
    }

    @Test
    void sameCustomerIdTest() {
        ImmutableInput input = ImmutableInput.builder()
                .customerId("1")
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(50.0)
                        .fromCustomerId("1")
                        .toCustomerId("1")
                        .build())
                .addTransactions(ImmutableTransaction.builder()
                        .id(UUID.randomUUID().toString())
                        .date(new Date())
                        .amount(25.0)
                        .fromCustomerId("3")
                        .toCustomerId("1")
                        .build())
                .build();
        double balance = f.apply(input);
        Assert.assertEquals(25.0d, balance, 1e-15);
    }
}