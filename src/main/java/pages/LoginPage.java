package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {

    private static final String EMAIL = "kostyaparahin@gmail.com";
    private static final String PASSWORD = "upibaz22";
    private static final By XPATH_USERNAME = By.xpath(".//input[@name = 'st.email']");
    private static final By XPATH_PASSWORD = By.xpath(".//input[@name = 'st.password']");
    private static final By XPATH_LOGIN = By.xpath(".//input[@data-l = 't,sign_in']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        driver.findElement(XPATH_USERNAME).sendKeys(EMAIL);
        driver.findElement(XPATH_PASSWORD).sendKeys(PASSWORD);

        WebElement login = driver.findElement(XPATH_LOGIN);
        login.click();
    }

    @Override
    public void check() {

    }
}
