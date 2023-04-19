import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.burgers.api.user.CreateUser;
import ru.praktikum.burgers.api.user.DeleteUser;
import ru.praktikum.burgers.api.user.LogInUser;
import ru.praktikum.burgers.api.user.UserData;
import ru.praktikum.burgers.api.util.TestFields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDataEditTest {
    LogInUser loginUser = new LogInUser();
    CreateUser createUser = new CreateUser();
    TestFields testFields = new TestFields();
    UserData userData = new UserData();
    DeleteUser deleteUser = new DeleteUser();

    @Test
    @DisplayName("Обновление name авторизованного юзера")
    @Description("При обновлении name  возвращается: \n" +
            "код 200, \n" +
            "новое имя")
    public void changeNameReturnStatusCode200() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        loginUser.loginUser(testFields.email, testFields.password);
        TestFields.accessTokenAfterLogin = TestFields.response.path("accessToken");
        userData.updateUserInfo(testFields.email, "NEW" + testFields.name);
        userData.getUserInfo();
        assertEquals(200, TestFields.response.statusCode());
        TestFields.newName = TestFields.response.path("user.name");
        assertEquals(TestFields.newName.toLowerCase(), ("NEW" + testFields.name).toLowerCase());
       // deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Обновление данных юзера без логина")
    @Description("При обновлении данных юзера без логина возвращается: \n" +
            "код 401, \n" +
            "You should be authorised")
    public void getUserInfoWithoutLoginReturnStatusCode401() {
        userData.updateUserInfoWithoutLogin("NEW" + testFields.email, "NEW" + testFields.name);
        assertEquals(401, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("message").equals("You should be authorised"));
    }
    @After
    public void deleteUser() {
        deleteUser.deleteUser();
    }

}
