package selenium;

import org.junit.Before;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.TestCase.assertTrue;

public class FlightSearchTest {

    private HtmlUnitDriver driver;
    private static final String flightSearchPage = WebUtil.baseUrl + "flightSearch";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(flightSearchPage);
        LoginUtil.partnerLogin(driver);
        assertTrue(driver.getTitle().equals("Update flight"));
    }
}
