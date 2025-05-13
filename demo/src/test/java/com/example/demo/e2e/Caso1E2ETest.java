package com.example.demo.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
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

@Test
public void flujoCompletoClienteOperadorDesdeLanding() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // === 1. Cliente entra a la landing page e inicia sesión ===
    driver.get("http://localhost:4200/login-cliente");
    driver.findElement(By.id("email")).sendKeys("joseber63@hotmail.com");
    driver.findElement(By.id("password")).sendKeys("123456");
    driver.findElement(By.cssSelector("button[type='submit']")).click();

    // === 2. Esperar y navegar al menú ===
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[routerlink='/menu']"))).click();

    // === 3. Ir a /info-plato/5 y seleccionar adicionales 4, 5, 6 ===
    driver.get("http://localhost:4200/info-plato/5");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='checkbox']")));

    for (WebElement checkbox : driver.findElements(By.cssSelector("input[type='checkbox']"))) {
        WebElement label = checkbox.findElement(By.xpath("following-sibling::span[@class='checkbox-label']"));
        String texto = label.getText();
        if (texto.contains("4") || texto.contains("5") || texto.contains("6")) {
            checkbox.click();
        }
    }

    driver.findElement(By.className("buttonadd")).click();
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // === 4. Ir a /info-plato/8 y seleccionar adicionales 4, 5, 6 ===
    driver.get("http://localhost:4200/info-plato/8");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='checkbox']")));

    for (WebElement checkbox : driver.findElements(By.cssSelector("input[type='checkbox']"))) {
        WebElement label = checkbox.findElement(By.xpath("following-sibling::span[@class='checkbox-label']"));
        String texto = label.getText();
        if (texto.contains("4") || texto.contains("5") || texto.contains("6")) {
            checkbox.click();
        }
    }

    driver.findElement(By.className("buttonadd")).click();
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // === 5. Ver carrito desde ícono ===
    WebElement iconoCarrito = driver.findElement(By.tagName("app-carrito-icon"));
    iconoCarrito.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("carrito-container")));

    List<WebElement> items = driver.findElements(By.className("item"));
    assertEquals(2, items.size(), "Debe haber 2 productos en el carrito.");

    // === 6. Confirmar pedido ===
    driver.findElement(By.className("btn-confirmar")).click();
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // === 7. Operador inicia sesión en nueva pestaña ===
    ((JavascriptExecutor) driver).executeScript("window.open()");
    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    driver.get("http://localhost:4200/login-operador");

    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usuario"))).sendKeys("carlos123");
    driver.findElement(By.id("contrasena")).sendKeys("password123");
    driver.findElement(By.cssSelector("button[type='submit']")).click();

    // === 8. Cambiar estado a "cocinando" y asignar domiciliario ===
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.tabla-pedidos tbody tr")));
    WebElement filaPedido = driver.findElement(By.cssSelector("table.tabla-pedidos tbody tr"));

    Select selectEstado = new Select(filaPedido.findElement(By.cssSelector("select.select-estado")));
    selectEstado.selectByVisibleText("cocinando");

    Select selectDomiciliario = new Select(filaPedido.findElement(By.cssSelector("select.select-domiciliario")));
    selectDomiciliario.selectByIndex(1);

    filaPedido.findElement(By.className("btn-actualizar")).click();
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // === 9. Cliente revisa pedidos ===
    driver.switchTo().window(tabs.get(0));
    driver.findElement(By.cssSelector("a[routerlink='/clientes/pedidos']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.tabla-pedidos tbody tr")));
    WebElement filaCliente = driver.findElement(By.cssSelector("table.tabla-pedidos tbody tr"));
    String estadoPedido = filaCliente.findElement(By.xpath("td[2]")).getText();
    assertEquals("RECIBIDO", estadoPedido);

    // === 10. Operador cambia estado a "completado" ===
    driver.switchTo().window(tabs.get(1));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.tabla-pedidos tbody tr")));
    filaPedido = driver.findElement(By.cssSelector("table.tabla-pedidos tbody tr"));

    Select selectEstadoFinal = new Select(filaPedido.findElement(By.cssSelector("select.select-estado")));
    selectEstadoFinal.selectByVisibleText("entregado");

    filaPedido.findElement(By.className("btn-actualizar")).click();
    wait.until(ExpectedConditions.alertIsPresent()).accept();

    // === 11. Cliente revisa pedidos nuevamente ===
    driver.switchTo().window(tabs.get(0));
    driver.findElement(By.cssSelector("a[routerlink='/clientes/pedidos']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.tabla-pedidos tbody tr")));
    WebElement filaClienteFinal = driver.findElement(By.cssSelector("table.tabla-pedidos tbody tr"));
    String estadoFinal = filaClienteFinal.findElement(By.xpath("td[2]")).getText();
    assertEquals("RECIBIDO", estadoFinal);

    // === 12. Verificar total mayor a cero ===
    String totalTexto = filaClienteFinal.findElement(By.xpath("td[4]")).getText()
        .replace("$", "").replace(".", "").replace(",", "").trim();

    double total = Double.parseDouble(totalTexto);
    assertTrue(total > 0, "El total pagado debe ser mayor que cero.");
}


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
