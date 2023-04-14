import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

public class CreateUserTest {
    CreateUser createUser = new CreateUser();
    TestFields testFields = new TestFields();
    DeleteUser deleteUser = new DeleteUser();
    @Test
    @DisplayName("Регистрация уникального юзера")
    @Description("При регистрации уникального юзера возвращается 200")
    public void registerNewUser() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.response.prettyPrint();
        assertEquals(200, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(true));
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Регистрация существующего юзера")
    @Description("При регистрации существующего юзера возвращается: \n" +
            "код 403, \n" +
            "User already exists")
    public void registerSameUser() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.response.prettyPrint();
        assertEquals(403, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("message").equals("User already exists"));
    }
    @Test
    @DisplayName("Регистрация юзера без email")
    @Description("При регистрации юзера без email возвращается: \n" +
            "код 403, \n" +
            "Email, password and name are required fields")
    public void registerNewUserWithoutEmail() {
        createUser.createUser("", testFields.password, testFields.name);
        TestFields.response.prettyPrint();
        assertEquals(403, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("message").equals("Email, password and name are required fields"));
    }

}
