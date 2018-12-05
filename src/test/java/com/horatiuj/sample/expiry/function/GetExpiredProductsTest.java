package com.horatiuj.sample.expiry.function;

import com.horatiuj.sample.expiry.model.ImmutableProduct;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

class GetExpiredProductsTest {

    private GetExpiredProducts f;
    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        f = new GetExpiredProducts();
    }

    @Test
    void expiredTest() throws ParseException {
        GetExpiredProducts.Output output = f.apply(ImmutableInput.builder()
                .current(DATE_FORMAT.parse("2018-01-05"))
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-04"))
                        .build())
                .build());

        Assert.assertEquals(1, output.expired().size());
        Assert.assertTrue(output.urgent().isEmpty());
        Assert.assertTrue(output.soon().isEmpty());
    }

    @Test
    void urgentTest() throws ParseException {
        GetExpiredProducts.Output output = f.apply(ImmutableInput.builder()
                .current(DATE_FORMAT.parse("2018-01-05"))
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-05"))
                        .build())
                .build());

        Assert.assertEquals(1, output.urgent().size());
        Assert.assertTrue(output.expired().isEmpty());
        Assert.assertTrue(output.soon().isEmpty());
    }

    @Test
    void soonTest() throws ParseException {
        GetExpiredProducts.Output output = f.apply(ImmutableInput.builder()
                .current(DATE_FORMAT.parse("2018-01-05"))
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-08"))
                        .build())
                .build());

        Assert.assertEquals(1, output.soon().size());
        Assert.assertTrue(output.urgent().isEmpty());
        Assert.assertTrue(output.expired().isEmpty());
    }

    @Test
    void doesNotApplyTest() throws ParseException {
        GetExpiredProducts.Output output = f.apply(ImmutableInput.builder()
                .current(DATE_FORMAT.parse("2018-01-05"))
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-09"))
                        .build())
                .build());

        Assert.assertTrue(output.expired().isEmpty());
        Assert.assertTrue(output.urgent().isEmpty());
        Assert.assertTrue(output.soon().isEmpty());
    }

    @Test
    void multipleTest() throws ParseException {
        GetExpiredProducts.Output output = f.apply(ImmutableInput.builder()
                .current(DATE_FORMAT.parse("2018-01-05"))
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-09"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-08"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-07"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-06"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-05"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-05"))
                        .build())
                .addProducts(ImmutableProduct.builder()
                        .id(UUID.randomUUID().toString())
                        .expiryDate(DATE_FORMAT.parse("2018-01-04"))
                        .build())
                .build());

        Assert.assertEquals(3, output.soon().size());
        Assert.assertEquals(2, output.urgent().size());
        Assert.assertEquals(1, output.expired().size());
    }
}