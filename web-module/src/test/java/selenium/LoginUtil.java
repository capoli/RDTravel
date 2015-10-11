package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginUtil {

    private static final String defaultPassword = "test";

    public static void testLogin(String userName, WebDriver driver) {
        loginOnTitleBar(userName, "test", driver);
    }

    public static void loginOnTitleBar(String userName, String password, WebDriver driver) {
        WebElement searchField = driver.findElement(By.id("username"));
        searchField.sendKeys(userName);
        searchField = driver.findElement(By.id("password"));
        searchField.sendKeys(password);
        searchField.submit();
    }

    public static void loginOnAuthTrigger(String userName, String password, WebDriver driver) {
        WebElement userNameField = driver.findElement(By.id("j_username"));
        userNameField.sendKeys(userName);
        WebElement passwordField = driver.findElement(By.id("j_password"));
        passwordField.sendKeys(password);
        WebElement form = driver.findElement(By.id("login"));
        form.submit();
    }

    public static void partnerLogin(HtmlUnitDriver driver) {
        loginOnAuthTrigger("partner", defaultPassword, driver);
    }

    public static void employeeLogin(WebDriver driver) {
        loginOnAuthTrigger("employee", defaultPassword, driver);
    }
}
