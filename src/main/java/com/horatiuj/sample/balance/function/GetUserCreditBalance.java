package com.horatiuj.sample.balance.function;

import com.horatiuj.sample.balance.model.Transaction;
import org.immutables.value.Value;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Calculate the balance of a particular customer using his Id and a list of transactions
 */
public class GetUserCreditBalance implements Function<GetUserCreditBalance.Input, Double> {

    @Override
    public Double apply(Input input) {
        Objects.requireNonNull(input);

        List<Transaction> transactions = input.transactions();
        final String customerId = input.customerId();

        Objects.requireNonNull(transactions);
        Objects.requireNonNull(customerId);

        return transactions.stream()
                .filter(t -> customerId.equals(t.fromCustomerId()) ^ customerId.equals(t.toCustomerId()))
                .mapToDouble(t -> t.amount() * (customerId.equals(t.fromCustomerId()) ? -1 : 1))
                .sum();
    }

    @Value.Immutable
    public interface Input {
        String customerId();
        List<Transaction> transactions();
    }
}
