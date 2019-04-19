package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChatWrapper {

    private static final By NAME_LOCATOR = By.xpath("//*[@class ='chats_i_h ellip']");

    private String mName;
    private WebElement mContext;

    public WebElement getmContext() {
        return mContext;
    }

    public ChatWrapper(WebElement webElement) {
        this.mContext = webElement;
        mName = webElement.findElement(NAME_LOCATOR).getText();
    }

}
