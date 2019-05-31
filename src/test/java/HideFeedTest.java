import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HideFeedTest {

    private RecommendationsPage recommendationsPage;
    private WebDriver driver;
 //перенести
    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
        recommendationsPage = userMainPage.toRecommendationsPage();
    }

    @Test
    public void hideFeedTest() {
        String startTitle = recommendationsPage.getTitle();
        final WebElement elementX = driver.findElement(recommendationsPage.X);
        recommendationsPage.clickOnInvisibleElement(elementX);
        //recommendationsPage.clickX();
        //in TestBase
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        recommendationsPage.clickHide();
        recommendationsPage.accept();
        recommendationsPage.clickHere();
        //new page
        String finalTitle = recommendationsPage.getTitle();
        Assert.assertEquals("Такой объект не найден", startTitle, finalTitle);
    }

    @After
    public void close() {
        driver.close();
    }
}
