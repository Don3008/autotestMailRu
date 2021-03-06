import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HideFeedTest extends TestBase {

    @Test
    public void hideFeedTest() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
        String startTitle = recommendationsPage.getTitle();
        webElement = recommendationsPage.toSearchElement(RecommendationsPage.X);
        javaScript.clickOnInvisibleElement(webElement);
        recommendationsPage.clickHideAllEvents();
        recommendationsPage.accept();
        BlackListPage blackListPage = recommendationsPage.clickToAddBlackList();
        String finalTitle = blackListPage.getTitle();
        Assert.assertEquals("Такой объект не найден", startTitle, finalTitle);
    }
}
