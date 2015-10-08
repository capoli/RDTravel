package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightCreationTest {
    private HtmlUnitDriver driver;
    private static final String flightCreationPage = WebUtil.baseUrl + "pages/partner/createFlight.faces";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(flightCreationPage);
        login();
    }

    @Test
    public void testIfPageTitleIsCorrect() {
        assertEquals("Create flight", driver.getTitle());
    }

    @Test
    public void testIfCreationFormExists() {
        assertTrue(driver.findElement(By.id("flight-creation-form")).isDisplayed());
    }

    @Test
    public void testIfDepartureLocationDropdownExists() {
        assertTrue(driver.findElement(By.id("departure-location")).isDisplayed());
    }

    @Test
    public void testIfDepartureLocationDropdownIsPopulated() {
        WebElement element = driver.findElement(By.id("departure-location"));
        checkIfMoreThan10Options(element);
    }

    @Test
    public void testIfArrivalLocationDropdownExists() {
        assertTrue(driver.findElement(By.id("arrival-location")).isDisplayed());
    }

    @Test
    public void testIfArrivalLocationDropdownIsPopulated() {
        WebElement element = driver.findElement(By.id("arrival-location"));
        checkIfMoreThan10Options(element);
    }

    private void checkIfMoreThan10Options(WebElement dropdown) {
        assertTrue(dropdown.findElements(By.className("ui-selectonemenu-list-item")).size() > 10);
    }


    private void login() {
        assertEquals("Login", driver.getTitle());
        WebElement username = driver.findElement(By.id("j_username"));
        username.sendKeys("partner");
        WebElement password = driver.findElement(By.id("j_password"));
        password.sendKeys("test");
        WebElement form = driver.findElement(By.id("login"));
        form.submit();
    }
}
