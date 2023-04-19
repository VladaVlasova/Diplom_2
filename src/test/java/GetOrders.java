import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.burgers.api.order.CreateOrder;
import ru.praktikum.burgers.api.order.GetUserOrders;
import ru.praktikum.burgers.api.order.Ingredients;
import ru.praktikum.burgers.api.user.CreateUser;
import ru.praktikum.burgers.api.user.DeleteUser;
import ru.praktikum.burgers.api.user.LogInUser;
import ru.praktikum.burgers.api.util.TestFields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetOrders {
    GetUserOrders getUserOrders = new GetUserOrders();
    CreateUser createUser = new CreateUser();
    LogInUser logInUser = new LogInUser();
    TestFields testFields = new TestFields();
    CreateOrder createOrder = new CreateOrder();
    Ingredients ingredients = new Ingredients();
    DeleteUser deleteUser = new DeleteUser();
    @Test
    @DisplayName("Запрос списка заказов под авторизованным пользователем")
    @Description("Запрос списка заказов под авторизованным пользователем возвращает 200")
    public void getOrdersLoginUserReturnStatusCode200() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        logInUser.loginUser(testFields.email, testFields.password);
        TestFields.accessTokenAfterLogin = TestFields.response.path("accessToken");
        ingredients.getIngredients();
        TestFields.ingredient1 = TestFields.response.path("data[0]._id");
        TestFields.ingredient2 = TestFields.response.path("data[1]._id");
        createOrder.makeOrderWithIngredients();
        getUserOrders.getOrders();
        assertEquals(200, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(true));
        //deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Запрос списка заказов под неавторизованным пользователем")
    @Description("Запрос списка заказов под неавторизованным пользователем возвращает 401")
    public void getOrdersWithoutLoginUserReturnStatusCode401() {
        getUserOrders.getOrdersWithoutLogin();
        assertEquals(401, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(false));
    }
    @After
    public void deleteUser() {
        deleteUser.deleteUser();
    }
}
