package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by TGIAX39 on 6/10/2015.
 */
public class LoginUtil {

    public static void testLogin(String userName, WebDriver driver) {
        WebElement searchField = driver.findElement(By.id("username"));
        searchField.sendKeys(userName);
        searchField = driver.findElement(By.id("password"));
        searchField.sendKeys("test");
        searchField.submit();
    }

    public static void partnerLogin(HtmlUnitDriver driver) {
        WebElement username = driver.findElement(By.id("j_username"));
        username.sendKeys("partner");
        WebElement password = driver.findElement(By.id("j_password"));
        password.sendKeys("test");
        WebElement form = driver.findElement(By.id("login"));
        form.submit();
    }
}
