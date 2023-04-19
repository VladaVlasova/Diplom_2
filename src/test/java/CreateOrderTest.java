import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.burgers.api.order.CreateOrder;
import ru.praktikum.burgers.api.order.Ingredients;
import ru.praktikum.burgers.api.user.CreateUser;
import ru.praktikum.burgers.api.user.DeleteUser;
import ru.praktikum.burgers.api.user.LogInUser;
import ru.praktikum.burgers.api.util.TestFields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateOrderTest {
    CreateUser createUser = new CreateUser();
    TestFields testFields = new TestFields();
    DeleteUser deleteUser = new DeleteUser();
    LogInUser loginUser = new LogInUser();
    Ingredients ingredients = new Ingredients();
    CreateOrder createOrder = new CreateOrder();
    @Test
    @DisplayName("Заказ с авторизацией и ингредиентами")
    @Description("Заказ с авторизацией и ингредиентами возвращает 200")
    public void makeOrderWithLoginAndIngredients() {

        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        loginUser.loginUser(testFields.email, testFields.password);
        ingredients.getIngredients();
        TestFields.ingredient1 = TestFields.response.path("data[0]._id");
        TestFields.ingredient2 = TestFields.response.path("data[1]._id");
        createOrder.makeOrderWithIngredients();
        assertEquals(200, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(true));
       // deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Заказ с авторизацией без ингредиентов")
    @Description("Заказ с авторизацией без ингредиентов возвращает 400")
    public void makeOrderWithLoginAndWithoutIngredients() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        loginUser.loginUser(testFields.email, testFields.password);
        createOrder.makeOrderWithoutIngredients();
        assertEquals(400, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(false));
        //deleteUser.deleteUser();
    }
    @Test
    @DisplayName("Заказ без авторизации с ингредиентами")
    @Description("Заказ без авторизации с ингредиентами возвращает 200")
    public void makeOrderWithoutLoginAndWithIngredients() {
        ingredients.getIngredients();
        TestFields.ingredient1 = TestFields.response.path("data[0]._id");
        TestFields.ingredient2 = TestFields.response.path("data[1]._id");
        createOrder.makeOrderWithIngredients();
        assertEquals(200, TestFields.response.statusCode());
        assertTrue(TestFields.response.path("success").equals(true));
    }
    @Test
    @DisplayName("Заказ с авторизацией и с неверным хешем ингредиентов")
    @Description("Заказ с авторизацией с неверным хешем ингредиентов возвращает 500")
    public void makeOrderWithLoginAndWithInvalidIngredients() {
        createUser.createUser(testFields.email, testFields.password, testFields.name);
        TestFields.accessTokenAfterRegister = TestFields.response.path("accessToken");
        loginUser.loginUser(testFields.email, testFields.password);
        createOrder.makeOrderWithInvalidIngredients();
        assertEquals(500, TestFields.response.statusCode());
        //deleteUser.deleteUser();
    }
    @After
    public void deleteUser() {
        deleteUser.deleteUser();
    }
}
