import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class RecommendationsPage extends Page {

    public static final By ICON_LOCATOR = By.xpath(".//a[contains(text(), 'Рекомендации')]");
    public static final By LIKE_COUNT = By.xpath(".//span[contains(@class, 'widget_count js-count')]");
    public static final By POST = By.xpath(".//div[@class='feed-w']");
    public static final By LIKE = By.xpath(".//div[contains(@class, '__wide-count')]");


    public RecommendationsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //wait.until(ExpectedConditions.attributeContains(ICON_LOCATOR, "class", "__active"));

        for (int i = 0; i < 20; i++) {
            By by = Post.getXpathByIndex(i);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            //System.out.println(by.toString());
        }
    }

    public int getLikeCount() {
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void clickLike() {
        new Actions(driver).moveToElement(driver.findElement(POST)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Класс\"", isElementPresent(LIKE));
        click(LIKE);
    }
}
