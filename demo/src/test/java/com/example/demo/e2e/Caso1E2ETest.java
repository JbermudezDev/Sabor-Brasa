package com.example.demo.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Caso1E2ETest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void ejecutarCaso1Completo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Desde landing page ir a login
        driver.get("http://localhost:4200");
        driver.get("http://localhost:4200/login");

        // 2. Login fallido
        driver.findElement(By.id("email")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("malclave");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 3. Login exitoso
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("thejuanjo1128@gmail.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 4. Crear nuevo producto con 2 adicionales
        driver.get("http://localhost:4200/productos");
        driver.findElement(By.cssSelector("button.btn-submit")).click();

        driver.findElement(By.id("nombre")).sendKeys("Costilla BBQ Test");
        driver.findElement(By.id("precio")).sendKeys("36000");
        driver.findElement(By.id("descripcion")).sendKeys("Automated test description");
        driver.findElement(By.id("imagen")).sendKeys("/images/pf4.png");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        checkboxes.get(0).click();
        checkboxes.get(1).click();

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 5. Login como cliente
        driver.get("http://localhost:4200/login-cliente");
        driver.findElement(By.id("email")).sendKeys("joseber63@hotmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 6. Verificar en info-plato los 2 adicionales
        driver.get("http://localhost:4200/info-plato/32"); // ⚠️ Cambia 32 por el ID real si varía
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkbox-label")));

        Assertions.assertTrue(driver.getPageSource().contains("Seleccionar Adicionales"));
        Assertions.assertTrue(driver.getPageSource().contains("Papa"));
        Assertions.assertTrue(driver.getPageSource().contains("Papa"));

        // 7. Regresar a administrador y agregar un tercer adicional
        driver.get("http://localhost:4200/logout");
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("email")).sendKeys("thejuanjo1128@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.get("http://localhost:4200/productos");
        WebElement fila = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//tr[td[contains(text(),'Costilla BBQ Test')]]")
        ));
        WebElement botonEditar = fila.findElement(By.xpath(".//button[contains(@class,'green')]"));
        botonEditar.click();

        checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        if (checkboxes.size() > 2 && !checkboxes.get(2).isSelected()) {
            checkboxes.get(2).click();
        }

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 8. Verificar en info-plato los 3 adicionales
        driver.get("http://localhost:4200/logout");
        driver.get("http://localhost:4200/login-cliente");
        driver.findElement(By.id("email")).sendKeys("joseber63@hotmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.get("http://localhost:4200/info-plato/32"); // ⚠️ Cambia 32 por el ID real si varía
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkbox-label")));

        Assertions.assertTrue(driver.getPageSource().contains("Seleccionar Adicionales"));
        Assertions.assertTrue(driver.getPageSource().contains("Papa"));
        Assertions.assertTrue(driver.getPageSource().contains("Papa"));
        Assertions.assertTrue(driver.getPageSource().contains("Yuquitas"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
