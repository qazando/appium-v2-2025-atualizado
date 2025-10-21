package tests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {

    private AndroidDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp("C:\\Users\\Usuário\\Downloads\\qafood.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testeSimples() {
        loginPage.emailField.sendKeys("teste@teste.com");
        loginPage.passwordField.sendKeys("123456");
        loginPage.loginButton.click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Page Object Interno
    public static class LoginPage {

        @AndroidFindBy(accessibility = "email")
        public WebElement emailField;

        @AndroidFindBy(accessibility = "password")
        public WebElement passwordField;

        @AndroidFindBy(accessibility = "login-button")
        public WebElement loginButton;

        public LoginPage(AndroidDriver driver) {
            PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        }
    }
}