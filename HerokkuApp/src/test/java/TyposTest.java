import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TyposTest {

    @Test
    public void checkTypos() {
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
        driver.get("https://the-internet.herokuapp.com/typos");

        // находим второй параграф
        String text = driver.findElement(By.xpath("(//p)[2]")).getText();
        Assert.assertEquals(text,"Sometimes you'll see a typo, other times you won't.");
        System.out.println(text);

        driver.quit();
        // закрывает браузер
    }
}
