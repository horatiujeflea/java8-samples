package com.horatiuj.sample;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class User {
    private String id;
    private String name;
    private String nickname;
    private Integer age;

    public static User.UserBuilder getBuilder(User user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .age(user.getAge());
    }
}
