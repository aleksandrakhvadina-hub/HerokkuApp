import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControlsTest {

    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void checkDynamicControls() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // находим кнопку "Remove" и кликаем
        driver.findElement(By.xpath("//*[text() = 'Remove']")).click();
        // явное ожидание - ждём пока элемент с id="message" станет видимым
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        // временно отклюючаем неявное ожидание чтобы проверить отсутствие элемента
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        // ищем все элементы с id="checkbox" и считаем их количество
        int numberOfElements = driver.findElements(By.id("checkbox")).size();
        // проверяем что чекбокс исчез
        softAssert.assertEquals(numberOfElements, 0);
        // возвращаем неявное ожидание обратно
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // явное ожидание невидимости чекбокса
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        // находим инпут
        WebElement input = driver.findElement(By.cssSelector("input[type='text']"));
        // проверяем что инпут disabled
        softAssert.assertFalse(input.isEnabled(), "Input should be disabled initially");
        // находим кнопку "Enable" и кликаем
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        // явное ожидание - ждём пока в элементе с id="message" появится текст "It's enabled!"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's enabled!"));
        // проверяем что инпут enabled
        softAssert.assertTrue(input.isEnabled(), "Input should be enabled after clicking Enable");
        // закрывает браузер
        driver.quit();

        softAssert.assertAll();
    }
}
