package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Page {

    private static final By MESSAGES_LOCATOR = By.xpath(".//*[contains(text(), 'Сообщения') and @class = 'toolbar_nav_i_tx-w usel-off']");

    private static final By MESSAGE_FRAME_LOCATOR = By.xpath(".//*[@id = 'hook_Block_MessagesLayer']");

    public UserMainPage(WebDriver driver) {
        super(driver);
        // TODO: сделать, чтобы check проверял загрузку текущей старницы
    }

    public void clickMessages() {
        driver.findElement(MESSAGES_LOCATOR).click();
        check();
        //System.out.println(driver.getCurrentUrl());
    }


    @Override
    public void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_FRAME_LOCATOR));
        // TODO: переписать метод, чтобы он проверял корректность загрузки страницы
    }
}
