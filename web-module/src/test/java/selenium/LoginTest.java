package selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(WebUtil.baseUrl);
    }


    @Test
    public void homePageTitleIsRDTravel() {
        assertEquals("RDTravel", driver.getTitle());
    }

    @Test
    public void customerCanLogin() throws IOException {
        testLoginSuccessful("customer");
    }

    @Test
    public void employeeCanLogin() {
        testLoginSuccessful("employee");
    }

    @Test
    public void partnerCanLogin() {
        testLoginSuccessful("partner");
    }

    @Test
    public void wrongLoginResultsInErrorMessage() {
        testLoginFailed("non-existing user");
    }


    private void testLoginSuccessful(String userName) {
        LoginUtil.testLogin(userName, driver);
        assertTrue(driver.findElement(By.id("screen-name")).isDisplayed());
    }

    private void testLoginFailed(String userName) {
        LoginUtil.testLogin(userName, driver);
        assertTrue(driver.findElement(By.id("error-message")).isDisplayed());
    }
}
