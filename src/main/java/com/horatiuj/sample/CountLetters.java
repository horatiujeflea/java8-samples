package com.horatiuj.sample;

import java.util.function.Function;

public class CountLetters implements Function<String, Integer> {
    @Override
    public Integer apply(String text) {
        return text == null ? 0 : text.length();
    }

    public static void main(String[] args) {
        User john = User.builder().age(12).name("John").id("10").build();
        User john2 = User.getBuilder(john).age(13).build();
        System.out.println(john);
        System.out.println(john2);
    }
}
