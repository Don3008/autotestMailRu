import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    RecommendationsPage recommendationsPage;
    WebDriver driver;
    UserMainPage userMainPage;

    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        userMainPage = loginPage.login();
        recommendationsPage = userMainPage.toRecommendationsPage();
    }

    @After
    public void close() {
        driver.close();
    }
}
