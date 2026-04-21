import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationTest {

    @Test
    public void testNotification() {
        // задаём опции для драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        options.addArguments("--headless");

        // определяем браузер, с которым хотим работать
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // открывает страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        // кликаем по ссылке
        driver.findElement(By.linkText("Click here")).click();

        // получаем текст уведомления
        String text = driver.findElement(By.id("flash")).getText();

        // проверяем, что текст не пустой
        Assert.assertFalse(text.isEmpty());

        driver.quit();
    }
}