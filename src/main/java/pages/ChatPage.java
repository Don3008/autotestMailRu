package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class ChatPage extends Page {

    private static final By CHAT_USER_ELEMENTS = By.xpath(".//*[contains(@class,'chats_i h-mod')]");

    public ChatPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        List<WebElement> elements;

        elements = driver.findElements(CHAT_USER_ELEMENTS);

        System.out.println(Transformer.countUsers(Transformer.wrap(elements)));


    }
}
