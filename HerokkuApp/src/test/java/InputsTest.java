import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class InputsTest {

    @Test
    public void checkInputs() {
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
        driver.get("https://the-internet.herokuapp.com/inputs");

        // вводим числовое значение
        driver.findElement(By.tagName("input")).sendKeys("13");
        Assert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "13");

        // жмём ARROW_UP для увеличения значения на 1
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "14");

        // жмём ARROW_DOWN для уменьшения значения на 1
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "13");

        // теперь введём нечисловое значение, очистив строку
        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys("lalala");

        // ожидаем что нельзя ввести строку
        Assert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "");

        driver.quit();
        // закрывает браузер
    }
}
