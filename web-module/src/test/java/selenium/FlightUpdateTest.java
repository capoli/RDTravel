package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static selenium.FlightCreationTest.*;

public class FlightUpdateTest {

    private HtmlUnitDriver driver;
    private static final String flightUpdatePage = WebUtil.baseUrl + "/pages/partner/updateFlight.faces?flightId=1000";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(flightUpdatePage);
        LoginUtil.partnerLogin(driver);
        assertTrue(driver.getTitle().equals("Update flight"));
    }

    @Test
    public void testIfDepartureLocationFilledCorrectly() {
        String selectedOption = new Select(driver.findElement(By.id(departureLocationId + "_input"))).getFirstSelectedOption().getText();
        assertEquals("United Arab Emirates", selectedOption);
    }

    @Test
    public void testIfArrivalLocationFilled() {
        String selectedOption = new Select(driver.findElement(By.id(arrivalLocationId + "_input"))).getFirstSelectedOption().getText();
        assertEquals("Aruba", selectedOption);
    }


    @Test
    public void checkIfDepartureDateFilled() {
        WebElement departureDate = driver.findElement(By.id(departureDateId));
        WebElement inputField = departureDate.findElement(By.tagName("input"));
        assertEquals("06/06/2015 02:30:50", inputField.getAttribute("value"));
    }

    @Test
    public void checkIfArrivalDateFilled() {
        WebElement arrivalDate = driver.findElement(By.id(arrivalDateId));
        WebElement inputField = arrivalDate.findElement(By.tagName("input"));
        assertEquals("06/06/2015 03:30:50", inputField.getAttribute("value"));
    }

    @Test
    public void checkIfPriceFilled() {
        WebElement price = driver.findElement(By.id(flightPriceId));
        assertEquals("75.00", price.getAttribute("value"));
    }

    @Test
    public void checkIfSeatsFilled() {
        WebElement seats = driver.findElement(By.id(flightAvailableSeatsId));
        assertEquals("200", seats.getAttribute("value"));
    }

    @Test
    public void checkIfSeatsThresholdFilled() {
        WebElement seatsThreshold = driver.findElement(By.id(seatsThresholdId));
        assertEquals("120", seatsThreshold.getAttribute("value"));
    }

    @Test
    public void checkIfUpdateSuccessFullOnZeroChange() {
        WebElement form = driver.findElement(By.id(flightFormId));
        form.submit();
        assertTrue(driver.getTitle().equals("RDTravel"));
    }

}
