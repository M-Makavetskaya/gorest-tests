package com.gorest.apitests;

import com.gorest.model.User;
import com.gorest.model.UserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteUserTest extends BaseApiTest {

    private static User testUser = UserFactory.getTestUserB();
    private static User createdUser;
    @BeforeClass
    public void setUp() {
        createdUser = goRestClient.addUser(testUser);
    }

    @Test
    public void deleteUser() {
        goRestClient.deleteUser(createdUser.getId());
        Assert.assertEquals(goRestClient.getUser(createdUser.getId()).getStatusCode(), 404, "User should be not found");
    }
}
