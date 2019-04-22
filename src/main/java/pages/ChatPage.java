package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Consumer;

public class ChatPage extends Page {

    private static final By CHAT_USER_ELEMENTS = By.xpath("//div/*[contains(@data-module, 'messages/ConversationsListItem')]");

    public ChatPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        List<WebElement> elements;

        check();

        elements = driver.findElements(CHAT_USER_ELEMENTS);

        //elements.forEach((webElement) -> System.out.println(webElement.getText()));

        List<ChatWrapper> wrapElements = Transformer.wrap(elements);

        System.out.println(Transformer.countUsers(wrapElements));

        System.out.println(Transformer.countUsersOnline(wrapElements));

    }

    public void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(CHAT_USER_ELEMENTS)));
    }

}
