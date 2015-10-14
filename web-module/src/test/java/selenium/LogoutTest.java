package selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoutTest {
    private WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(WebUtil.baseUrl);
    }

    @Test
    public void testIfUserCanLogOut() {
        LoginUtil.testLogin("customer", driver);
        driver.findElement(By.id("logout-button")).click();
        assertTrue(driver.findElement(By.id("username")).isDisplayed());
        driver.get(RoleTest.customerPage);
        assertEquals("Login", driver.getTitle());
    }
}
