package posts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostWithJoinButton extends Post {

    public static final By JOIN = By.xpath(".//button[@data-l = 't,join']");

    private WebElement joinElement;

    public PostWithJoinButton(WebDriver driver, WebElement element) {
        super(driver, element);

        checkJoinElement();
        joinElement = rootElement.findElement(JOIN);
    }

    public void join() {
        joinElement.click();
    }

    private void checkJoinElement() {
        checkElement(JOIN);
    }
}
