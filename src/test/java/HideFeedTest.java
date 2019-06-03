import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HideFeedTest extends TestBase {

    @Test
    public void hideFeedTest() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        JavaScript javaScript = new JavaScript(driver);
        String startTitle = recommendationsPage.getTitle();
        final WebElement elementX = driver.findElement(recommendationsPage.X);
        javaScript.clickOnInvisibleElement(elementX);
        recommendationsPage.clickHideAllEvents();
        recommendationsPage.accept();
        BlackListPage blackListPage = recommendationsPage.clickToAddBlackList();
        String finalTitle = blackListPage.getTitle();
        Assert.assertEquals("Такой объект не найден", startTitle, finalTitle);
    }

}
