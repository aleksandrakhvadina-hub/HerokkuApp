import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveElementTest {

    @Test
    public void checkAddRemoveElement() {
        // задаём опции для драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        options.addArguments("--headless");

        // определяем браузер, с которым хотим работать
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // открываем страницу по указанному урлу
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        // 2 раза жмём на кнопку add element
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();

        // получаем коллекцию элементов с текстом Delete, помещаем её в переменную инт
        int size = driver.findElements(By.xpath("//button[text()='Delete']")).size();

        // сравниваем полученное количество элементов с ожидаемым
        Assert.assertEquals(size, 2);

        // нажимаем на кнопку делит (удалили одну из кнопок тем самым)
        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        // получаем коллекцию элементов с текстом Delete, помещаем её в переменную инт
        int size1 = driver.findElements(By.xpath("//button[text()='Delete']")).size();

        // сравниваем полученное количество элементов с ожидаемым
        Assert.assertEquals(size1, 1);

        driver.quit();
        // закрывает браузер
    }
}
