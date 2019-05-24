package trash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import trash.pages.ChatPage;
import trash.pages.LoginPage;
import trash.pages.Page;
import trash.pages.UserMainPage;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {// TODO: Сделать всё это в Unit тестах
        WebDriver driver = new ChromeDriver();

        List<Page> pages = new LinkedList<>();

        driver.get("https://ok.ru");


        LoginPage lp = new LoginPage(driver);
        lp.login();

        UserMainPage ump = new UserMainPage(driver);
        ump.clickMessages();

        ChatPage cp = new ChatPage(driver);
        cp.makeChatPageOperations();

        Thread.sleep(10000);

        driver.quit();
    }

}
