import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckboxesTest {

    @Test
    public void checkCheckboxes() {
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
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // проверяем что первый чекбокс не отмечен
        boolean isCheck1 = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        Assert.assertFalse(isCheck1);

        // отмечаем первый чекбокс
        driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
        boolean isCheck1AfterClick = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        Assert.assertTrue(isCheck1AfterClick);

        // проверяем что второй чекбокс отмечен
        boolean isCheck2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        Assert.assertTrue(isCheck2);

        // убираем галочку со второго чекбокса
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        boolean isCheck2AfterClick = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        Assert.assertFalse(isCheck2AfterClick);

        driver.quit();
        // закрывает браузер
    }
}
