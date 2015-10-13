package selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UpdateDiscountTest {

    private HtmlUnitDriver driver;
    private static final String discountUpdatePage = WebUtil.baseUrl + "/pages/partner/updateDiscount.faces";
    private static final String discountId = "airline-discount";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
        driver.get(discountUpdatePage);
        LoginUtil.partnerLogin(driver);
        assertTrue(driver.getTitle().equals("Update discount"));
    }

    @Test
    public void testIfDiscountFilled() {
        WebElement discountField = driver.findElement(By.id(discountId));
        assertEquals("0.1", discountField.getAttribute("value"));
    }

    @Test
    public void testIfDiscountUpdateWentThrough() {
        WebElement discountField = driver.findElement(By.id(discountId));
        discountField.sendKeys("5");
        discountField.submit();
        assertEquals("RDTravel", driver.getTitle());
        driver.get(discountUpdatePage);
        discountField = driver.findElement(By.id(discountId));
        assertEquals("0.15", discountField.getAttribute("value"));
    }
}
