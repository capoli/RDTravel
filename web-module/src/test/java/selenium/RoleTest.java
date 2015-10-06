package selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class RoleTest {
    private WebDriver driver;
    private static final String baseUrl = "http://localhost:8080/web-module-1.5/";
    public static final String customerPage = baseUrl + "pages/customer/customer.faces";
    private static final String employeePage = baseUrl + "pages/employee/employee.faces";
    private static final String partnerPage = baseUrl + "pages/partner/partner.faces";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
    }

    @Test
    public void testCustomerLoginTrigger() {
        driver.get(customerPage);
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testEmployeeLoginTrigger() {
        driver.get(employeePage);
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testPartnerLoginTrigger() {
        driver.get(partnerPage);
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void customerHasPermissionAfterLogin() {
        testIfUserhasCorrectPermission("customer", customerPage, "Customer");
    }

    @Test
    public void employeeHasPermissionAfterLogin() {
        testIfUserhasCorrectPermission("employee", employeePage, "Employee");
    }

    @Test
    public void partnerHasPermissionAfterLogin() {
        testIfUserhasCorrectPermission("partner", partnerPage, "Partner");
    }

    private void testIfUserhasCorrectPermission(String userName, String pageWithRestriction, String expectedTitle) {
        driver.get(baseUrl);
        LoginUtil.testLogin(userName, driver);
        driver.get(pageWithRestriction);
        assertEquals(expectedTitle, driver.getTitle());
    }
}
