package ru.praktikum.burgers.api.user;

import io.qameta.allure.Step;
import ru.praktikum.burgers.api.util.Endpoints;
import ru.praktikum.burgers.api.util.TestFields;

import static io.restassured.RestAssured.given;

public class DeleteUser {
    @Step("Удалить юзера")
    public void deleteUser() {

        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterRegister)
                .when().log().all()
                .delete(Endpoints.DELETE_USER_URL);
    }
}
