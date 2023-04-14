import static io.restassured.RestAssured.given;

public class DeleteUser {
    public void deleteUser() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterRegister)
                .when().log().all()
                .delete(Endpoints.DELETE_USER_URL);
    }
}
