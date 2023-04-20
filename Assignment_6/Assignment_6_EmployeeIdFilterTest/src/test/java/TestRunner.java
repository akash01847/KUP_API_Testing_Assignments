import Assignment_6.EmployeeIdFilterTest;
import org.testng.annotations.Test;

public class TestRunner {

    @Test
    public void testEmployeeIdFilter() {
        EmployeeIdFilterTest employeeTest = new EmployeeIdFilterTest();
        employeeTest.testFindEmployeeIdsBetweenRange();
    }
}
