package posts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostWithWidgetList extends Post {

    private WebElement likeElement;
    private WebElement repostElement;

    private WidgetListWrapper widgets;

    PostWithWidgetList(WebDriver driver, WebElement element) {
        super(driver, element);

        checkWidgetListElements();

        widgets = new WidgetListWrapper(rootElement, driver);
    }

    public void share() {
        widgets.repost();
        widgets.repostNow();
    }

    public WidgetListWrapper getWidgets() {
        return widgets;
    }

    private void checkWidgetListElements() {
        checkElement(WidgetListWrapper.REPOST);
        checkElement(WidgetListWrapper.LIKE);
    }
}
