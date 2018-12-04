package com.horatiuj.sample.expiry;

import com.horatiuj.sample.expiry.util.DateUtil;

import com.horatiuj.sample.expiry.model.Product;
import org.immutables.value.Value;

import java.util.*;
import java.util.function.Function;

import static com.horatiuj.sample.expiry.GetExpiredProducts.ExpiryType.*;
import static java.util.stream.Collectors.groupingBy;

/**
 * Find the products which are expired from a list of products
 * Soon - at most 3 days left (including expiration day)
 * Urgent - expires that day
 * Expired - already expired
 *
 * An iteration over the products list is considered expensive
 */
public class GetExpiredProducts implements Function<GetExpiredProducts.Input, GetExpiredProducts.Output> {

    public enum ExpiryType {
        SOON,
        URGENT,
        EXPIRED,
        NOT_APPLIES
    }

    @Override
    public Output apply(Input input) {
        Objects.requireNonNull(input);

        final Date current = input.current();
        final List<Product> products = input.products();

        Objects.requireNonNull(current);
        Objects.requireNonNull(products);

        Function<Product, ExpiryType> dateToLabel = (p) ->
        {
            ExpiryType soonCheck = DateUtil.getDifferenceDays(current, p.expiryDate()) <= 3 ? SOON : NOT_APPLIES;
            ExpiryType urgentCheck = DateUtil.getDifferenceDays(current, p.expiryDate()) <= 1 ? URGENT : soonCheck;
            return current.after(p.expiryDate()) ? EXPIRED : urgentCheck;
        };

        Map<ExpiryType, List<Product>> collect = products.stream()
                .collect(groupingBy(dateToLabel));

        return ImmutableOutput.builder()
                .soon(collect.getOrDefault(SOON, Collections.emptyList()))
                .urgent(collect.getOrDefault(URGENT, Collections.emptyList()))
                .expired(collect.getOrDefault(EXPIRED, Collections.emptyList()))
                .build();
    }

    @Value.Immutable
    public interface Input {
        Date current();
        List<Product> products();
    }

    @Value.Immutable
    public interface Output {
        List<Product> soon();
        List<Product> urgent();
        List<Product> expired();
    }
}
