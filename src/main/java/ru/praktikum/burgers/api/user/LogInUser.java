package ru.praktikum.burgers.api.user;

import io.qameta.allure.Step;
import org.json.JSONObject;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class LogInUser {
    @Step("Авторизоваться")
    public void loginUser(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.LOGIN_USER_URL);
    }
}
