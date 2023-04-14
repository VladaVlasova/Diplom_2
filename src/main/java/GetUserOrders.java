import static io.restassured.RestAssured.given;

public class GetUserOrders {
    public void getOrders() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", TestFields.accessTokenAfterLogin)
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }
    public void getOrdersWithoutLogin() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }
}
