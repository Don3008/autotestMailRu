package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ChatPage extends Page {

    private static final By CHAT_USER_ELEMENTS = By.xpath(".//div/*[contains(@data-module, 'messages/ConversationsListItem')]");

    public ChatPage(WebDriver driver) {
        super(driver);
    }

    public void makeChatPageOperations() {
        List<WebElement> elements;

        check();

        elements = driver.findElements(CHAT_USER_ELEMENTS);

        List<ChatWrapper> wrapElements = Transformer.wrap(elements);

        System.out.println("Number of all chats: " + Transformer.countUsers(wrapElements));

        System.out.println("Number of online users: " + Transformer.countUsersOnline(wrapElements));

    }

    @Override
    public void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(CHAT_USER_ELEMENTS)));
    }

}
