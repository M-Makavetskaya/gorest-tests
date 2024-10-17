package com.gorest.apitests;

import com.gorest.model.User;
import com.gorest.model.UserFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUserTest extends BaseApiTest {

    private static User testUser = UserFactory.getTestUserA();
    private static User createdUser;

    @Test
    public void postUser() {
        createdUser = goRestClient.addUser(testUser);
        User actualUser  = goRestClient.getUser(createdUser.getId()).as(User.class);;
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUser.getName(), testUser.getName(), "User name should be correct");
        softAssert.assertEquals(actualUser.getEmail(), testUser.getEmail(), "Email should be correct");
        softAssert.assertAll();
    }

    @AfterClass
    public void cleanUpData() {
        goRestClient.deleteUser(createdUser.getId());
    }
}
