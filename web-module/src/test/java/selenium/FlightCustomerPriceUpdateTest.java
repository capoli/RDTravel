package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.TestCase.assertTrue;


public class FlightCustomerPriceUpdateTest {

    private WebDriver driver;
    private static final String flightUpdateCustomerPricePage = WebUtil.baseUrl + "/pages/employee/updateFlightCustomerPrice.faces?flightId=1000";
    private static final String customerPriceId = "customer-price";
    private static final String customerPriceErrorId = "customer-price-error";

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
}
