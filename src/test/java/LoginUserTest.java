import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.burgers.api.user.CreateUser;
import ru.praktikum.burgers.api.user.DeleteUser;
import ru.praktikum.burgers.api.user.LogInUser;
import ru.praktikum.burgers.api.util.TestFields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {
    LogInUser loginUser = new LogInUser();
    CreateUser createUser = new CreateUser();
    TestFields testFields = new TestFields();
    DeleteUser deleteUser = new DeleteUser();

    @Test
    @DisplayName("Авторизация под существующим юзером")
    @Description("При авторизации под существующим юзером код 200")
    public void loginUserValidCredsReturnStatusCode200() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        loginUser.loginUser(testFields.email, testFields.password);
        assertEquals(200, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(true));
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        //deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Авторизация под несуществующим юзером")
    @Description("При авторизации под несуществующим юзером возвращается:\n " +
            "код 401,\n " +
            "success == false, \n" +
            "email or password are incorrect")
    public void loginUserInvalidCredsReturnStatusCode401() {
        loginUser.loginUser(testFields.email, testFields.password);
        assertEquals(401, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(false));
        assertTrue(TestFields.response.path("message").equals("email or password are incorrect"));
    }
    @After
    public void deleteUser() {
        deleteUser.deleteUser();
    }
}
