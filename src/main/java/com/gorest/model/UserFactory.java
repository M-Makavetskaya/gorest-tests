package com.gorest.model;

public class UserFactory {

    public static User getTestUserA() {
        return new User("John Smith", "male", "hello@16ab.com", "active");
    }

    public static User getTestUserB() {
        return new User("Mika Forest", "female", "mika@17cd.com", "active");
    }
}
