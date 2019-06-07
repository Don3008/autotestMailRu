package posts;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WidgetListWrapper {

    static final By WIDGET_LIST = By.xpath(".//*[@class = 'feed_f']/ul[@class = 'widget-list h-mod']");

    static final By REPOST = By.xpath(".//button[@data-type = 'RESHARE']");
    static final By LIKE = By.xpath(".//*[@data-module = 'ReactComponent']");

    private static final By REPOST_COMMIT = By.xpath(".//*[@data-l = 't,now']/a");
    private static final By COUNTER = By.xpath(".//span[contains(@class, 'widget_count')]");

    private SearchContext context;

    private WebDriver driver;

    private WebElement likeElement;
    private WebElement repostElement;

    WidgetListWrapper(WebElement rootElement, WebDriver driver) {
        this.driver = driver;

        context = rootElement.findElement(WIDGET_LIST);

        likeElement = context.findElement(LIKE);
        repostElement = context.findElement(REPOST);
    }

    void repost() {
        repostElement.click();
    }

    void repostNow() {
        waitRepostNow();

        WebElement element = context.findElement(REPOST_COMMIT);
        element.click();

        waitButtonReposted(element);
        repostElement = context.findElement(REPOST);

    }

    private void waitRepostNow() {
        WebDriverWait wait = new WebDriverWait(driver, 7);

        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                if (context.findElement(REPOST_COMMIT).isDisplayed()) {
                    return Boolean.TRUE;
                }
                return null;
            }
        });
    }

    private void waitButtonReposted(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 7);

        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public int getRepostCount() {
        WebElement element = repostElement.findElement(COUNTER);
        return Integer.parseInt(element.getText());
    }

    public int getLikeCount() {
        WebElement element = likeElement.findElement(COUNTER);
        return Integer.parseInt(element.getText());
    }

}
