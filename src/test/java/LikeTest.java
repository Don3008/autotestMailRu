import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeTest extends TestBase{

    @Before
    public void preCondition() {
        init();
    }

    @Test
    public void likeTest() {
        UserMainPage mainPage = new LoginPage(driver).login();
        RecommendationsPage recommendationsPage = mainPage.toRecommendationsPage();
        int likeBefore = recommendationsPage.getLikeCount();
        final WebElement likeElement = driver.findElement(recommendationsPage.LIKE_COUNT);
        recommendationsPage.clickLike();
        recommendationsPage.waitStalenessOfElement(likeElement);
        int likeAfter = recommendationsPage.getLikeCount();
        Assert.assertEquals("Количество классов не совпадает", likeBefore + 1, likeAfter);

    }

    @After
    public void afterTest() {
        stop();
    }
}
