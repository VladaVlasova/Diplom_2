package ru.praktikum.burgers.api.order;

import io.qameta.allure.Step;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class Ingredients {
    @Step("Получить ингридиенты")
    public void getIngredients() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_INGREDIENTS_URL);
    }
}
