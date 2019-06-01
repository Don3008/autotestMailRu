import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HideFeedTest extends TestBase {

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

}
