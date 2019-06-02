package posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostWithJoinButton extends Post {

    static final By JOIN = By.xpath(".//button[@data-l = 't,join']");
    private static final By JOIN_RESULT = By.xpath(".//*[@class = 'tico c-green join-group-result']");

    private WebElement joinElement;

    PostWithJoinButton(WebDriver driver, WebElement element) {
        super(driver, element);

        checkJoinElement();
        joinElement = rootElement.findElement(JOIN);
    }

    public void join() {
        joinElement.click();
    }

    public boolean hasJoinResult() {
        try {
            waitElement(JOIN_RESULT);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void checkJoinElement() {
        waitElement(JOIN);
    }
}
