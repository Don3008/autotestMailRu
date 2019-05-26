import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
    }

    @Test
    public void testUserMainPage() {
        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
    }

}
