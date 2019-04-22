package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChatWrapper {

    private static final By NAME_LOCATOR = By.className("chats_i_h");

    private String mName;
    private WebElement mContext;

    public WebElement getContext() {
        return mContext;
    }

    public ChatWrapper(WebElement webElement) {
        this.mContext = webElement;
        mName = mContext.findElement(NAME_LOCATOR).getText();
    }

}
