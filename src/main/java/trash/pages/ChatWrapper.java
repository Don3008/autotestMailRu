package trash.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class ChatWrapper {

    private static final By NAME_LOCATOR = By.className("chats_i_h");

    private String mName;
    private WebElement mContext;

    ChatWrapper(WebElement webElement) {
        this.mContext = webElement;
        mName = mContext.findElement(NAME_LOCATOR).getText();
    }

    WebElement getContext() {
        return mContext;
    }

}
