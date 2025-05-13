package com.example.demo.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCaseTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  public void setup() {
    WebDriverManager
      .chromedriver()
      .driverVersion("136") // Fuerza descarga de ChromeDriver para Chrome 136
      .setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @Test
  public void UseCaseOne() throws InterruptedException {
    driver.get("http://localhost:4200/login-cliente");

    driver.findElement(By.id("email")).sendKeys("joseber@hotmail.com");
    driver.findElement(By.id("password")).sendKeys("123456");

    WebElement btnIniciar = driver.findElement(By.id("btnLogin"));
    btnIniciar.click();

    // try {
    //   wait.until(ExpectedConditions.alertIsPresent());
    //   driver.switchTo().alert().accept();
    //   System.out.println(
    //     "⚠️ El login falló (alerta detectada), cancelando prueba."
    //   );
    //   return; // detener prueba
    // } catch (Exception e) {
    //   // No hubo alerta: continuamos
    // }

    // Esperar que la URL cambie a "/menu" o que un elemento post-login esté presente
    wait.until(ExpectedConditions.urlContains("/menu"));
    driver.get("http://localhost:4200/info-plato/5");

    List<WebElement> checkboxes = driver.findElements(By.id("check"));
    checkboxes.get(0).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("check")));
    checkboxes.get(1).click();

    WebElement btnAgregar = driver.findElement(By.className("buttonadd"));
    btnAgregar.click();

    try {
      wait.until(ExpectedConditions.alertIsPresent());
      driver.switchTo().alert().accept();
    } catch (Exception e) {
      System.out.println("No apareció ninguna alerta");
    }

    driver.get("http://localhost:4200/info-plato/8");
    List<WebElement> checkboxes1 = driver.findElements(By.id("check"));
    checkboxes1.get(0).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("check")));
    checkboxes1.get(1).click();

    WebElement btnAgregar1 = driver.findElement(By.className("buttonadd"));
    btnAgregar1.click();

    try {
      wait.until(ExpectedConditions.alertIsPresent());
      driver.switchTo().alert().accept();
    } catch (Exception e) {
      System.out.println("No apareció ninguna alerta");
    }

    driver.get("http://localhost:4200/carrito-icon");

    wait.until(
      ExpectedConditions.presenceOfElementLocated(
        By.className("carrito-container")
      )
    );

    List<WebElement> itemsEnCarrito = driver.findElements(By.className("item"));
    assert (
      itemsEnCarrito.size() == 2
    ) : "Se esperaban 2 productos en el carrito, pero se encontraron " +
    itemsEnCarrito.size();

    for (WebElement item : itemsEnCarrito) {
      List<WebElement> adicionales = item.findElements(By.tagName("li"));
      assert (
        !adicionales.isEmpty()
      ) : "El producto no tiene adicionales asociados.";
    }

    WebElement btnconfirmar = driver.findElement(By.className("btn-confirmar"));
    btnconfirmar.click();
  }

  @AfterEach
  public void tearDown() {
    //driver.quit();
  }
}
