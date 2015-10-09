package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginUtil {

    public static void testLogin(String userName, WebDriver driver) {
        testLoginWithPassword(userName, "test", driver);
    }

    public static void testLoginWithPassword(String userName, String password, WebDriver driver) {
        WebElement searchField = driver.findElement(By.id("username"));
        searchField.sendKeys(userName);
        searchField = driver.findElement(By.id("password"));
        searchField.sendKeys(password);
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
