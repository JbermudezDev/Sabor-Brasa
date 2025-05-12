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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UseCaseTest {

  private final String BASE_URL = "http://localhost:4200";

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
  public void init() {
    WebDriverManager.chromedriver().setup();

    ChromeOptions chromeOptions = new ChromeOptions();

    chromeOptions.addArguments("--disable-notifications");
    chromeOptions.addArguments("--disable-extensions");
    //chromeOptions.addArguments("--headless");

    this.driver = new ChromeDriver(chromeOptions);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  @Test
  public void UseCaseOne() {
    driver.get(BASE_URL);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnLogin")));
    WebElement btnElement = driver.findElement(By.id("btnLogin"));
    btnElement.click();

    WebElement emailInput = wait.until(
      ExpectedConditions.elementToBeClickable(By.id("email"))
    );
    WebElement passwordInput = driver.findElement(By.id("password"));

    emailInput.sendKeys("7Ig6a@example.com");
    passwordInput.sendKeys("1234");

    WebElement btnIniciar = driver.findElement(By.id("btnIniciar"));
    btnIniciar.click();

    emailInput.clear();
    passwordInput.clear();

    emailInput.sendKeys("thejuanjo1128@gmail.com");
    passwordInput.sendKeys("123456");

    WebElement btnIniciar2 = driver.findElement(By.id("btnIniciar"));
    btnIniciar2.click();
  }

  @AfterEach
  public void tearDown() {
    //driver.quit();
  }
}
