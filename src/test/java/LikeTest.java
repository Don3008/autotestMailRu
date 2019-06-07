import org.junit.Assert;
import org.junit.Test;

public class LikeTest extends TestBase {

    @Test
    public void likeTest() {
        int likeBefore = recommendationsPage.getLikeCount();
        webElement = recommendationsPage.toSearchElement(RecommendationsPage.LIKE_COUNT);
        recommendationsPage.clickLike();
        recommendationsPage.waitStalenessOfElement(webElement);
        int likeAfter = recommendationsPage.getLikeCount();
        Assert.assertEquals("Количество классов не совпадает", likeAfter, likeBefore + 1);
    }
}
