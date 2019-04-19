package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Page {

    public static final By MESSAGES_LOCATOR = By.xpath(".//*[contains(text(), 'Сообщения') and @class = 'toolbar_nav_i_tx-w usel-off']");

    private static final By MESSAGE_FRAME_LOCATOR = By.xpath(".//*[@id = 'hook_Block_MessagesLayer']");

    public UserMainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        driver.findElement(MESSAGES_LOCATOR).click();
        check();
        System.out.println(driver.getCurrentUrl());
    }

    private void check() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_FRAME_LOCATOR));
    }

}
