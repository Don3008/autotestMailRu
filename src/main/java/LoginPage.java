import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    public static final String LOGIN_STR = "pavlovdmitriy95@gmail.com";
    //public static final String LOGIN_STR = "kostyaparahin@gmail.com";
    private static final By LOGIN = By.id("field_email");
    private static final By PASSWORD = By.id("field_password");
    private static final By LOGIN_BUTTON = By.xpath("//*[@value='Войти']");
    //private static final String PASSWORD_STR = "upibaz22";
    private static final String PASSWORD_STR = "tFwwgoinw30";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public UserMainPage login() {
        WebElement login = driver.findElement(LOGIN);
        login.sendKeys(LOGIN_STR);

        WebElement password = driver.findElement(PASSWORD);
        password.sendKeys(PASSWORD_STR);

        WebElement loginButton = driver.findElement(LOGIN_BUTTON);
        loginButton.click();

        return new UserMainPage(driver);
    }

    @Override
    public void check() {
        WebDriverWait waiter = new WebDriverWait(driver, 10);

        waiter.until(ExpectedConditions.visibilityOfElementLocated(LOGIN));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }
}
