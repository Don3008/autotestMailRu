import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LikeTest{

    private RecommendationsPage recommendationsPage;
    private WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        UserMainPage userMainPage = loginPage.login();
        recommendationsPage = userMainPage.toRecommendationsPage();
    }

    @Test
    public void likeTest() {
        int likeBefore = recommendationsPage.getLikeCount();
        final WebElement likeElement = driver.findElement(recommendationsPage.LIKE_COUNT);
        recommendationsPage.clickLike();
        recommendationsPage.waitStalenessOfElement(likeElement);
        int likeAfter = recommendationsPage.getLikeCount();
        Assert.assertEquals("Количество классов не совпадает", likeAfter, likeBefore + 1);
    }
}
