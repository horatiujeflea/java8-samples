package com.horatiuj.sample;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class User {
    private String id;
    private String name;
    private Integer age;

    public static User.UserBuilder getBuilder(User user) {
        return User.builder().id(
                user.getId()).name(user.getName()).age(user.getAge());
    }
}
