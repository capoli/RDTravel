package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static selenium.FlightCreationTest.flightFormId;

public class FlightCustomerPriceUpdateTest {

    private WebDriver driver;
    private static final String flightUpdateCustomerPricePage = WebUtil.baseUrl + "/pages/employee/updateFlightCustomerPrice.faces?flightId=1000";
    private static final String customerPriceId = "customer-price";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(flightUpdateCustomerPricePage);
        LoginUtil.employeeLogin(driver);
        assertTrue(driver.getTitle().equals("Update flight price"));
    }

    @Test
    public void testIfCustomerPriceFieldPresent() {
        assertTrue(driver.findElement(By.id(customerPriceId)).isDisplayed());
    }

    @Test
    public void testIfCustomerPriceFilled() {
        assertEquals("78.75", driver.findElement(By.id(customerPriceId)).getAttribute("value"));
    }

    @Test
    public void testIfPriceCanBeUpdatedWithZeroChange() {
        WebElement form = driver.findElement(By.id(flightFormId));
        form.submit();
        assertEquals("RDTravel", driver.getTitle());
    }
}
