package com.example.demo.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCaseTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  public void setup() {
    WebDriverManager.chromedriver().driverVersion("136").setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @Test
  public void UseCaseOne() throws InterruptedException {
    driver.get("http://localhost:4200/login-cliente");

    driver.findElement(By.id("email")).sendKeys("molokojj09@gmail.com");
    driver.findElement(By.id("password")).sendKeys("bermu123");

    WebElement btnIniciar = driver.findElement(By.id("btnLogin"));
    btnIniciar.click();
    // // try {
    // //   wait.until(ExpectedConditions.alertIsPresent());
    // //   driver.switchTo().alert().accept();
    // //   System.out.println(
    // //     "El login falló (alerta detectada), cancelando prueba."
    // //   );
    // //   return; // detener prueba
    // // } catch (Exception e) {
    // //   // No hubo alerta: continuamos
    // // }

    // driver.get("http://localhost:4200/menu");
    // driver.get("http://localhost:4200/info-plato/5");

    // List<WebElement> checkboxes = driver.findElements(By.id("check"));
    // checkboxes.get(0).click();
    // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("check")));
    // checkboxes.get(1).click();

    // WebElement btnAgregar = driver.findElement(By.className("buttonadd"));
    // btnAgregar.click();

    // try {
    //   wait.until(ExpectedConditions.alertIsPresent());
    //   driver.switchTo().alert().accept();
    // } catch (Exception e) {
    //   System.out.println("No apareció ninguna alerta");
    // }

    // driver.get("http://localhost:4200/info-plato/8");
    // List<WebElement> checkboxes1 = driver.findElements(By.id("check"));
    // checkboxes1.get(0).click();
    // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("check")));
    // checkboxes1.get(1).click();

    // WebElement btnAgregar1 = driver.findElement(By.className("buttonadd"));
    // btnAgregar1.click();

    // try {
    //   wait.until(ExpectedConditions.alertIsPresent());
    //   driver.switchTo().alert().accept();
    // } catch (Exception e) {
    //   System.out.println("No apareció ninguna alerta");
    // }

    // driver.get("http://localhost:4200/carrito-icon");

    // wait.until(
    //   ExpectedConditions.presenceOfElementLocated(
    //     By.className("carrito-container")
    //   )
    // );

    // List<WebElement> itemsEnCarrito = driver.findElements(By.className("item"));
    // assert (
    //   itemsEnCarrito.size() == 2
    // ) : "Se esperaban 2 productos en el carrito, pero se encontraron " +
    // itemsEnCarrito.size();

    // for (WebElement item : itemsEnCarrito) {
    //   List<WebElement> adicionales = item.findElements(By.tagName("li"));
    //   assert (
    //     !adicionales.isEmpty()
    //   ) : "El producto no tiene adicionales asociados.";
    // }

    // WebElement btnconfirmar = driver.findElement(By.className("btn-confirmar"));
    // btnconfirmar.click();

  }

  @Test
  public void UseCaseTwo() throws InterruptedException {
    driver.get("http://localhost:4200/login-operador");

    driver.findElement(By.id("usuario")).sendKeys("ana456");
    driver.findElement(By.id("contrasena")).sendKeys("password456");

    WebElement btnIniciarOp = driver.findElement(By.id("btnLoginOp"));
    btnIniciarOp.click();
  }

  @AfterEach
  public void tearDown() {
    //driver.quit();
  }
}
