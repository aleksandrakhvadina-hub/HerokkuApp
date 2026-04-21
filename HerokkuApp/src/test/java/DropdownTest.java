import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

        @Test
        public void testDropdown() {
            // задаём опции для драйвера
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("--disable-notification");
            options.addArguments("--headless");

            // определяем браузер, с которым хотим работать
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // открывает страницу по указанному урлу
            driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

            // находим дропдаун
            WebElement dropdownElement = driver.findElement(By.id("dropdown"));

            // создали объект селект
            Select dropdown = new Select(dropdownElement);

            // проверяем, что элементов больше 1
            Assert.assertTrue(dropdown.getOptions().size() > 1);

            // выбираем первый дропдаун и проверяем что он выбран
            dropdown.selectByIndex(1);
            Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");

            // выбираем второй дропдаун и проверяем что он выбран
            dropdown.selectByIndex(2);
            Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");

            driver.quit();
        }
}

