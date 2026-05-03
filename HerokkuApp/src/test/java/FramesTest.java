import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.testng.Assert.*;

public class FramesTest {

    @Test
    public void checkFileUpload() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/iframe");

        // ждём появления фрейма и автоматически переключаемся на него
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        // находим параграф внутри переключенного контекста фрейма
        WebElement paragraph = driver.findElement(By.tagName("p"));
        // проверяем, что текст внутри параграфа совпадает с заданием
        assertEquals(paragraph.getText(), "Your content goes here.");

        driver.quit();
    }
}
