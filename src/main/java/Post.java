import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Post {
    private static final String XPATH_BY_INDEX = ".//*[@class = 'feed-w'][%s]";
    private static final String XPATH_BY_ID = ".//*[@data-feed-id = '%s']/..]";
    private static final By JOIN = By.xpath(".//button[text() = 'Присоединиться']");

    private static final By COMMENT = By.xpath(".//*[@class = 'widget ']//a[@data-module = 'CommentWidgets']");
    private static final By COUNTER = By.xpath(".//span[contains(@class, 'widget_count')]");
    private static final By REPOST = By.xpath(".//button[@data-type = 'RESHARE']");
    private static final By LIKE = By.xpath(".//*[@data-module = 'ReactComponent']");
    private static final By REPOST_COMMIT = By.xpath(".//*[contains(text(), 'Поделиться сейчас')]/..");
    private String id;

    private By ROOT_LOCATOR;
    private WebElement rootElement;
    private WebElement commentElement;
    private WebElement likeElement;
    private WebElement repostElement;
    private WebElement joinElement;
    private WebDriverWait wait;

    private boolean hasWidgets;
    private boolean hasJoinButton;

    public Post(WebDriver driver, int index) {

        ROOT_LOCATOR = getXpathByIndex(index);

        init(driver);
    }

    public Post(WebDriver driver, String id) {
        ROOT_LOCATOR = By.xpath(String.format(XPATH_BY_ID, id));

        init(driver);
    }

    private void init(WebDriver driver) {
        rootElement = driver.findElement(ROOT_LOCATOR);
        wait = new WebDriverWait(driver, 10);

        checkElements(driver);

        hasWidgets = true;
        hasJoinButton = true;

        WebElement innerDiv = rootElement.findElement(By.xpath("./div"));
        id = innerDiv.getAttribute("data-feed-id");

        try {
            commentElement = rootElement.findElement(COMMENT);
            repostElement = rootElement.findElement(REPOST);
            likeElement = rootElement.findElement(LIKE);
        } catch (NoSuchElementException e) {
            hasWidgets = false;
        }

        try {
            joinElement = rootElement.findElement(JOIN);
        } catch (NoSuchElementException e) {
            hasJoinButton = false;
        }
    }

    public void share(WebDriver driver) {
        repostElement.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement repost = driver.findElement(REPOST_COMMIT);

        repost.click();

        wait.until(visibilityOfPostElement(REPOST));

        repostElement = rootElement.findElement(REPOST);

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public void join() {
        joinElement.click();
    }

    public String getId() {
        return id;
    }

    public boolean isJoinExists() {
        return hasJoinButton;
    }

    public int getRepostCounter() {
        if (repostElement == null) {
            throw new NullPointerException("Repost element is null!!!");
        }

        wait.until(visibilityOfPostElement(COUNTER));

        WebElement counter = repostElement.findElement(COUNTER);

        return Integer.parseInt(counter.getText());
    }

    public boolean hasWidgets() {
        return hasWidgets;
    }

    public static By getXpathByIndex(int index) {
        return By.xpath(String.format(XPATH_BY_INDEX, String.valueOf(index + 1)));
    }

    private void checkElements(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //wait.until(visibilityOfPostElement(COMMENT));
        //wait.until(visibilityOfPostElement(REPOST));
        //wait.until(visibilityOfPostElement(LIKE));
    }

    private ExpectedCondition<WebElement> visibilityOfPostElement(By locator) {
        if (rootElement == null) {
            throw new NullPointerException("rootElement is null!");
        }

        return driver -> {
            try {
                WebElement element = rootElement.findElement(locator);
                return element.isDisplayed() ? element : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        };
    }
}
