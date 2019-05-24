package trash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    private static final By ONLINE_LOCATOR = By.className("ic-online");

    private Transformer() {}

    public static List<ChatWrapper> wrap(List<WebElement> webElementList){

        List<ChatWrapper> res = new ArrayList<ChatWrapper>();

        for (WebElement webElement : webElementList) {
            res.add(new ChatWrapper(webElement));
        }

        return res;

    }

    public static int countUsers (List<ChatWrapper> chats) {
        return chats.size();
    }

    public static int countUsersOnline(List<ChatWrapper> chats) {

        int res = 0;

        for (ChatWrapper chat : chats) {

            try {
                chat.getContext().findElement(ONLINE_LOCATOR);
            } catch (NoSuchElementException e) {
                continue;
            }
            res++;

        }

        return res;

    }


}
