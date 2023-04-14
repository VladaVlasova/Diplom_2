import static io.restassured.RestAssured.given;

public class Ingredients {
    public void getIngredients() {
        TestFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_INGREDIENTS_URL);
    }
}
