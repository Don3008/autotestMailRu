package pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    WebDriver driver;

    Page(WebDriver driver) {
        this.driver = driver;
        check();
    }

    public abstract void check();

}
