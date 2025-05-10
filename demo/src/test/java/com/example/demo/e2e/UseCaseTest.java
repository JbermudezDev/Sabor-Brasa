package com.example.demo.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    driver.get("http://localhost:4200/");
  }
}
