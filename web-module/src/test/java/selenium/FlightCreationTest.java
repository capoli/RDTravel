package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;

public class FlightCreationTest {
    private HtmlUnitDriver driver;
    private static final String flightCreationPage = WebUtil.baseUrl + "pages/partner/createFlight.faces";
    private static final String departureDateErrorId = "departure-date-time-error";
    private static final String arrivalDateErrorId = "arrival-date-time-error";
    private static final String priceErrorId = "flight-price-error";
    private static final String seatsErrorId = "flight-available-seats-error";
    private static final String seatsThresholdErrorId = "seats-threshold-error";
    static final String departureLocationId = "departure-location";
    static final String arrivalLocationId = "arrival-location";
    static final String departureDateId = "departure-date-time";
    static final String arrivalDateId = "arrival-date-time";
    static final String flightPriceId = "flight-price";
    static final String flightAvailableSeatsId = "flight-available-seats";
    static final String flightFormId = "flight-form";
    static final String seatsThresholdId = "seats-threshold";

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
        assertTrue(driver.findElement(By.id(flightFormId)).isDisplayed());
    }

    @Test
    public void testIfDepartureLocationDropdownExists() {
        assertTrue(driver.findElement(By.id(departureLocationId)).isDisplayed());
    }

    @Test
    public void testIfDepartureLocationDropdownIsPopulated() {
        WebElement element = driver.findElement(By.id(departureLocationId));
        checkIfMoreThan10Options(element);
    }

    @Test
    public void testIfArrivalLocationDropdownExists() {
        assertTrue(driver.findElement(By.id(arrivalLocationId)).isDisplayed());
    }

    @Test
    public void testIfArrivalLocationDropdownIsPopulated() {
        WebElement element = driver.findElement(By.id(arrivalLocationId));
        checkIfMoreThan10Options(element);
    }

    @Test
    public void testIfDepartureTimePickerPresent() {
        assertTrue(driver.findElement(By.id(departureDateId)).isDisplayed());
    }

    @Test
    public void testIfArrivalTimePickerPresent() {
        assertTrue(driver.findElement(By.id(arrivalDateId)).isDisplayed());
    }

    @Test
    public void testIfPriceFieldExists() {
        assertTrue(driver.findElement(By.id(flightPriceId)).isDisplayed());
    }

    @Test
    public void testIFAvailableSeatsFieldPresent() {
        assertTrue(driver.findElement(By.id(flightAvailableSeatsId)).isDisplayed());
    }

    @Test
    public void testIfCreateButtonExists() {
        assertTrue(driver.findElement(By.id("action-flight-button")).isDisplayed());
    }

    @Test
    public void testIfErrorMessageOnMissingDateTimes() {
        assertTrue(driver.findElement(By.id(departureDateErrorId)).getText().equals(""));
        assertTrue(driver.findElement(By.id(arrivalDateErrorId)).getText().equals(""));
        fillForm("99.99", "200", "100");
        checkIfErrorTriggered(departureDateErrorId);
        checkIfErrorTriggered(arrivalDateErrorId);
    }

    @Test
    public void testIfErrorMessageOnEmptyPrice() {
        fillForm("", "200", "100");
        checkIfErrorTriggered(priceErrorId);
    }

    @Test
    public void testIfErrorMessageOnNegativePrice() {
        fillForm("-5", "200", "100");
        checkIfErrorTriggered(priceErrorId);
    }

    @Test
    public void testIfErrorMessageOnUnParsablePrice() {
        fillForm("5ff", "200", "100");
        checkIfErrorTriggered(priceErrorId);
    }

    @Test
    public void testIfErrorMessageOnEmptySeats() {
        fillForm("299.50", "", "100");
        checkIfErrorTriggered(seatsErrorId);
    }

    @Test
    public void testIfErrorMessageOnNegativeSeats() {
        fillForm("199.88", "-200", "100");
        checkIfErrorTriggered(seatsErrorId);
    }

    @Test
    public void testIfSeatThresholdExists() {
        assertNotNull(driver.findElement(By.id(seatsThresholdId)));
    }

    @Test
    public void testIfErrorOnSeatsThresholdEmpty() {
        fillForm("60", "20", "");
        checkIfErrorTriggered(seatsThresholdErrorId);
    }

    @Test
    public void testIfErrorOnSeatsThresholdNegative() {
        fillForm("60", "20", "-200");
        checkIfErrorTriggered(seatsThresholdErrorId);
    }

    private void checkIfErrorTriggered(String errorMessageId) {
        assertFalse(driver.findElement(By.id(errorMessageId)).getText().equals(""));
    }

    private void checkIfMoreThan10Options(WebElement dropdown) {
        assertTrue(dropdown.findElements(By.className("ui-selectonemenu-list-item")).size() > 10);
    }

    private void fillForm(String price, String seats, String seatsThreshold) {
        WebElement flightPrice = driver.findElement(By.id(flightPriceId));
        flightPrice.sendKeys(price);
        WebElement flightAvailableSeats = driver.findElement(By.id(flightAvailableSeatsId));
        flightAvailableSeats.sendKeys(seats);
        WebElement threshold = driver.findElement(By.id(seatsThresholdId));
        threshold.sendKeys(seatsThreshold);
        WebElement form = driver.findElement(By.id(flightFormId));
        form.submit();
    }

    private void login() {
        LoginUtil.partnerLogin(driver);
    }
}
