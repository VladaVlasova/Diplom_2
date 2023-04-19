package ru.praktikum.burgers.api.user;

import io.qameta.allure.Step;
import org.json.JSONObject;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class CreateUser {
    @Step("Создать юзера")
    public void createUser(String email, String password, String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_USER_URL);
    }


}
