package ru.praktikum.burgers.api.order;

import io.qameta.allure.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    @Step("Создать заказ с ингридиентами")
    public void makeOrderWithIngredients() {
        JSONArray ingredients = new JSONArray();
        ingredients.put(TestFields.ingredient1);
        ingredients.put(TestFields.ingredient2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", ingredients);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }
    @Step("Создать заказ без ингридиентов")
    public void makeOrderWithoutIngredients() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", "");
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }
    @Step("Создать заказ с невалидными ингридиентами")
    public void makeOrderWithInvalidIngredients() {
        JSONArray ingredients = new JSONArray();
        ingredients.put("85465jkdgf9854fdg");
        ingredients.put("85465jk67dgf9854fdg");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", ingredients);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }
}
