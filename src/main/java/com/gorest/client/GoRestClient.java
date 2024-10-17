package com.gorest.client;

import com.google.gson.Gson;
import com.gorest.model.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class GoRestClient {

    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    private final static String TOKEN = "03be4920830e5e2d48183163842c73e0aab43597b810f723176b8889cb6ee8a6";

    public GoRestClient() {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/")
                .addHeader("Content-Type", "application/json")
                .build();
        this.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(allOf(greaterThanOrEqualTo(200), lessThan(300)))
                .build();
    }


    public Response getUser(String id) {
        return RestAssured
                .given().spec(requestSpecification).auth().oauth2(TOKEN).and()
                .when().log().all().get("public/v2/users/" + id);
    }

    public User addUser(User testUser) {
        String body = (new Gson()).toJson(testUser);
        return RestAssured
                .given().spec(requestSpecification).auth().oauth2(TOKEN).and().body(body)
                .when().log().all().post("public/v2/users")
                .then().spec(responseSpecification).extract().as(User.class);
    }

    public Response deleteUser(String id) {
        return RestAssured
                .given().spec(requestSpecification).auth().oauth2(TOKEN)
                .when().log().all().delete("public/v2/users/" + id);
    }
}
