package ru.praktikum.burgers.api.order;

import io.qameta.allure.Step;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class GetUserOrders {
    @Step("Получить заказ юзера с логином")
    public void getOrders() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterLogin)
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }
    @Step("Получить заказ юзера без логина")
    public void getOrdersWithoutLogin() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }
}
