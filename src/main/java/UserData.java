import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UserData {
    public void getUserInfo() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterLogin)
                .when().log().all()
                .get(Endpoints.UPDATE_USER_URL);
    }
    public void updateUserInfo(String newEmail, String newName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", newEmail);
        jsonObject.put("name", newName);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterLogin)
                .body(jsonObject.toMap())
                .when().log().all()
                .patch(Endpoints.UPDATE_USER_URL);
    }
    public void updateUserInfoWithoutLogin(String newEmail, String newName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", newEmail);
        jsonObject.put("name", newName);
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .patch(Endpoints.UPDATE_USER_URL);
    }
}
