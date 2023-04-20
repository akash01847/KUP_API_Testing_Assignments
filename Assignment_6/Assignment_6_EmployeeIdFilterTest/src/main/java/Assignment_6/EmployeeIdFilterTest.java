package Assignment_6;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class EmployeeIdFilterTest {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 10;

    @Test
    public void testFindEmployeeIdsBetweenRange() {

        RestAssured.baseURI = BASE_URL;

        Response response = given().when().get("/users");

        String responseBody = response.getBody().asString();

        List<Integer> employeeIdsInRange = from(responseBody)
                .getList("data.findAll { it.id >= " + MIN_ID + " && it.id <= " + MAX_ID + " }.id");

        System.out.println("List of employee IDs between " + MIN_ID + " and " + MAX_ID + ":");
        System.out.println(employeeIdsInRange);
    }
}


