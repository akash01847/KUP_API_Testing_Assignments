package Assignment_7;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Test
public class ValidateApiResponseTest {
    private static final String BASE_URL = "https://reqres.in/api";

    public boolean verifyGetUserDetails() {
        Response response = RestAssured.given().when().get(BASE_URL + "/users/2");
        try {
            response.then().statusCode(200).body("data.first_name", equalTo("Janet")).body("data.last_name", equalTo("Weaver"))
                    .body("data.email", equalTo("janet.weaver@reqres.in"));
            System.out.println("\n================== GET Request ==================");
            response.then().time(lessThan(1000L));
            System.out.println("Pass: Response Time is less than 1000 ms");
            response.then().header("Content-Type", equalTo("application/json; charset=utf-8"));
            response.then().header("Server", equalTo("cloudflare"));
            System.out.println("Pass: Response Headers are valid");
            System.out.println("Pass: Response Status Code is " + response.statusCode());
            System.out.println("Pass: User details are successfully Validated");
            System.out.println("Response Body:");
            response.getBody().prettyPrint();
            return true;
        } catch (AssertionError e) {
            System.out.println("\n================== GET Request ==================");
            System.out.println("Fail: User details validation failed");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean verifyCreateUser() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        Response response = null;
        try {
            response = RestAssured.given().contentType("application/json").body(requestBody).when().post(BASE_URL + "/users");
            response.then().statusCode(201).body("name", equalTo("morpheus")).body("job", equalTo("leader"));

            System.out.println("\n================== CREATE Request ==================");
            response.then().time(lessThan(1000L));
            System.out.println("Pass: Response Time is less than 1000 ms");
            response.then().header("Content-Type", equalTo("application/json; charset=utf-8"));
            response.then().header("Server", equalTo("cloudflare"));
            System.out.println("Pass: Response Headers are valid");
            System.out.println("Pass: Response Status Code is " + response.statusCode());
            System.out.println("Pass: User is successfully created");
            System.out.println("Response Body: ");
            response.getBody().prettyPrint();
            return true;
        } catch (AssertionError e) {
            System.out.println("\n================== CREATE Request ==================");
            System.out.println("Fail: User creation validation failed");
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (response != null) {
                response.getBody().prettyPrint();
            }
        }
    }

    public boolean verifyUpdateUser() {
        boolean isSuccess = false;
        try {
            String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
            Response response = RestAssured.given().contentType("application/json").body(requestBody).when().put(BASE_URL + "/users/2");
            response.then().body("name", equalTo("morpheus")).body("job", equalTo("zion resident"));
            System.out.println("\n================== PUT Request ==================");
            response.then().statusCode(200);
            System.out.println("Pass: Response Status Code is " + response.statusCode());
            response.then().time(lessThan(1000L));
            System.out.println("Pass: Response Time is less than 1000 ms");
            System.out.println("Response Body: ");
            response.getBody().prettyPrint();
            isSuccess = true;
        } catch (AssertionError e) {
            System.out.println("\n================== PUT Request ==================");
            System.out.println("Update User Failed");
            e.printStackTrace();
        }
        return isSuccess;
    }

    public void verifyPatchUser() {
        String requestBody = "{\"name\": \"Akash Singh\"}";
        Response response = RestAssured.given().contentType("application/json").body(requestBody).when().patch(BASE_URL + "/users/2");
        try {
            response.then().statusCode(200).body("name", equalTo("Akash Singh"));
            System.out.println("\n================== PATCH Request ==================");
            response.then().time(lessThan(1000L));
            System.out.println("Pass: Response Time is less than 1000 ms");
            System.out.println("Pass: Response Status Code is " + response.statusCode());
            System.out.println("Pass: User details are successfully updated");
            System.out.println("Response Body: ");
            response.getBody().prettyPrint();
        } catch (AssertionError e) {
            System.out.println("\n================== PATCH Request ==================");
            System.out.println("Fail: User details update failed");
            System.out.println(e.getMessage());
        }
    }

    public boolean verifyDeleteUser() {
        Response response = RestAssured.given().when().delete(BASE_URL + "/users/2");
        try {
            response.then().statusCode(204);
            response.then().time(lessThan(1000L));
            System.out.println("\n================== DELETE Request ==================");
            System.out.println("Pass: Response Status Code is " + response.statusCode());
            System.out.println("Pass: Response Time is less than 1000 ms");
            System.out.println("Pass: User successfully deleted");
            return true;
        } catch (AssertionError e) {
            System.out.println("\n================== DELETE Request ==================");
            System.out.println("Fail: Unable to delete user");
            System.out.println("Assertion Error: " + e.getMessage());
            return false;
        }
    }

}
