import Assignment_7.ValidateApiResponseTest;
import org.testng.annotations.Test;

public class TestRunner {
    ValidateApiResponseTest apiTests = new ValidateApiResponseTest();

    @Test
    public void validateResponse() {
        apiTests.verifyGetUserDetails();
        apiTests.verifyCreateUser();
        apiTests.verifyUpdateUser();
        apiTests.verifyPatchUser();
        apiTests.verifyDeleteUser();
    }
}
