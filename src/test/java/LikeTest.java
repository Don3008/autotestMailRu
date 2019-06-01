import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeTest extends TestBase {

    @Test
    public void likeTest() {
        int likeBefore = recommendationsPage.getLikeCount();
        final WebElement likeElement = driver.findElement(RecommendationsPage.LIKE_COUNT);
        recommendationsPage.clickLike();
        recommendationsPage.waitStalenessOfElement(likeElement);
        int likeAfter = recommendationsPage.getLikeCount();
        Assert.assertEquals("Количество классов не совпадает", likeAfter, likeBefore + 1);
    }

}
