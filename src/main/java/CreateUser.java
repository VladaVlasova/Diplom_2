import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateUser {
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
