package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;

public class ChromeTest {

    private static final String CHROME_DRIVER_FILE_NAME = "chromedriver";

    private ChromeDriver chromeDriver;

    @BeforeEach
    void setUp() {
        ChromeDriverService service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File(getChromeDriverFileName())).build();
        ChromeOptions  chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximised");

        chromeDriver = new ChromeDriver(service, chromeOptions);
    }

    @AfterEach
    void tearDown() {
        chromeDriver.quit();
    }

    @Test
    void testLogin() {
        chromeDriver.get("http://localhost:8080/users/login");

        WebElement userNameInput = chromeDriver.findElement(By.name("username"));
        WebElement passwordInput = chromeDriver.findElement(By.name("password"));

        userNameInput.sendKeys("ivo.ali@gmail.com");
        passwordInput.sendKeys("ivoalich");
        passwordInput.submit();

        WebElement h5greeting = chromeDriver.findElement(By.tagName("h5"));
        String h5Text = h5greeting.getText();

        Assertions.assertTrue(h5Text.contains("Welcome"));
        Assertions.assertTrue(h5Text.contains("Ivailo Alichk"));
    }

    private String getChromeDriverFileName() {
        ClassLoader classLoader = ChromeTest.class.getClassLoader();
        URL driverUrl = classLoader.getResource(CHROME_DRIVER_FILE_NAME);
        assert driverUrl != null;
        return driverUrl.getFile();
    }
}
