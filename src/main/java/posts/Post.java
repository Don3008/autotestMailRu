package posts;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Post {
    public static final By POST_LOCATOR = By.xpath(".//*[@class = 'feed-w']");

    private String id;

    WebElement rootElement;

    private WebDriver driver;

    //WebElement Wrapper

    Post(WebDriver driver, WebElement element) {
        rootElement = element;
        this.driver = driver;

        WebElement innerDiv = rootElement.findElement(By.xpath("./div"));
        id = innerDiv.getAttribute("data-feed-id");
    }

    public final PostWithWidgetList transformToPostWithWidgetList() {
        return new PostWithWidgetList(driver, rootElement);
    }

    public final PostWithJoinButton transformToPostWithJoinButton() {
        return new PostWithJoinButton(driver, rootElement);
    }

    public final boolean hasWidgetList() {
        try {
            rootElement.findElement(WidgetListWrapper.WIDGET_LIST);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public final boolean hasJoinButton() {
        try {
            rootElement.findElement(PostWithJoinButton.JOIN);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    void checkElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(visibilityOfPostElement(locator));
    }

    public String getId() {
        return id;
    }

    private ExpectedCondition<WebElement> visibilityOfPostElement(By locator) {
        checkRootElement();

        return driver -> {
            try {
                WebElement element = rootElement.findElement(locator);
                return element.isDisplayed() ? element : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        };
    }

    private void checkRootElement() {
        Assert.assertNotNull(getClass().getName() + ": Root element is not found!", rootElement);
    }

}
