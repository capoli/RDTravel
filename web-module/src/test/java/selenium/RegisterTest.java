package selenium;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;


public class RegisterTest {
    private HtmlUnitDriver driver;
    private static final String registerPage = WebUtil.baseUrl + "register.faces";
    private static final String userNameErrorId = "username-error";
    private static final String passwordErrorid = "password-error";
    private static final String emailErrorId = "email-error";

    @Before
    public void openBrowser() {
        driver = new HtmlUnitDriver();
    }

    @Test
    public void testIfRegisterPageExists() {
        driver.get(registerPage);
        assertEquals("Register", driver.getTitle());
    }

    @Test
    public void testIfNameFieldPresent() {
        driver.get(registerPage);
        assertTrue(driver.findElement(By.id("username")).isDisplayed());
    }

    @Test
    public void testIfPasswordFieldPresent() {
        driver.get(registerPage);
        assertTrue(driver.findElement(By.id("password")).isDisplayed());
    }

    @Test
    public void testIfEmailFieldPresent() {
        driver.get(registerPage);
        assertTrue(driver.findElement(By.id("email")).isDisplayed());
    }

    @Test
    public void testIfRegisterFormPresent() {
        driver.get(registerPage);
        assertTrue(driver.findElement(By.id("registerForm")).isDisplayed());
    }

    @Test
    public void testIfRegisterSucceedsOnCorrectData() {
        register("registerCustomer", "test123", "registerCustomer@hotmail.com");
        LoginUtil.loginOnTitleBar("registerCustomer", "test123", driver);
        assertTrue(driver.getTitle().equals("RDTravel"));
    }

    @Test
    public void testIfFailOnWrongNameLength() {
        register("nope", "test", "nope@hotmail.com");
        checkIfErrorTriggered(userNameErrorId);
        register("WAAAAAAAYYYYYYYYYYTOOOOOOOOOOOOOOOOOOOOOLOOOOOOOOOOOOOOOOOOOONG", "test", "tooLong@hotmail.com");
        checkIfErrorTriggered(userNameErrorId);
    }

    @Test
    public void testIfFailOnWrongNameCharacter() {
        register("thiswontwork@", "test", "wontwork@hotmail.com");
        checkIfErrorTriggered(userNameErrorId);
    }

    @Test
    public void testIfFailOnAlreadyExistentName() {
        register("customer", "test", "customer@hotmail.com");
        checkIfErrorTriggered(userNameErrorId);
    }

    @Test
    public void testIfFailOnPasswordWrongLength() {
        register("passwordCheck1", "pas1", "passwordCheck1@hotmail.com");
        checkIfErrorTriggered(passwordErrorid);
    }

    @Test
    public void testIfFailOnPasswordWrongFormat() {
        register("passwordCheck2", "nonumbers", "passwordCheck2@hotmail.com");
        checkIfErrorTriggered(passwordErrorid);
        register("passwordCheck3", "123123123", "passwordCheck3@hotmail.com");
        checkIfErrorTriggered(passwordErrorid);
    }

    @Test
    public void testIfFailOnEmailUsedByOtherAccount() {
        register("emailCheck1", "test123", "customer@hotmail.com");
        checkIfErrorTriggered(emailErrorId);
    }

    @Test
    public void testIfCustomerCanViewRestrictedPageAfterRegister() {
        register("accessCheck", "test123", "access@hotmail.com");
        LoginUtil.loginOnTitleBar("accessCheck", "test123", driver);
        driver.get(RoleTest.customerPage);
        assertEquals("Customer", driver.getTitle());
    }

    private void checkIfErrorTriggered(String errorMessageId) {
        assertFalse(driver.findElement(By.id(errorMessageId)).getText().equals(""));
    }


    private void register(String userName, String password, String email) {
        driver.get(registerPage);
        WebElement userNameField = driver.findElement(By.id("register-username"));
        userNameField.sendKeys(userName);
        WebElement passwordField = driver.findElement(By.id("register-password"));
        passwordField.sendKeys(password);
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(email);
        WebElement registerForm = driver.findElement(By.id("registerForm"));
        registerForm.submit();
    }
}
