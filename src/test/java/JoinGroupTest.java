import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JoinGroupTest {

    //private RecommendationsPage recommendationsPage;
    private WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
    }

    @Test
    public void subscribeGroup() {
        RecommendationsPage recommendationsPage = new RecommendationsPage(driver);
        recommendationsPage.groups();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int groupsBefore = recommendationsPage.getGroupCount();
        final WebElement groupElement = driver.findElement(RecommendationsPage.GROUP_COUNT);
        //методы наз по действиям
        recommendationsPage.goToMainPage();
        UserMainPage userMainPage = new UserMainPage(driver);
        recommendationsPage = userMainPage.toRecommendationsPage();
        //заменяем в чеке
        recommendationsPage.waitStalenessOfElement(groupElement);
        recommendationsPage.subscribe();
        recommendationsPage.groups();
        //новая страница
        int groupsAfter = recommendationsPage.getGroupCount();
        Assert.assertEquals("Количество групп не совпадает", groupsAfter, groupsBefore + 1);
    }
}
