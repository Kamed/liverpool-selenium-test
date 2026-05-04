package com.liverpool.automation.tests;

import com.liverpool.automation.config.DriverManager;
import com.liverpool.automation.utils.DataUtils;
import com.liverpool.automation.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * Clase base para todos los tests
 */
public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;
    protected String testEmail;
    protected String testPassword;

    @BeforeClass
    public void setUp() throws IOException {
        // Leer datos de prueba
        testEmail = DataUtils.getEmail();
        testPassword = DataUtils.getPassword();
        baseUrl = "https://www.liverpool.com.mx";

        // Inicializar driver
        driver = DriverManager.initializeDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /**
     * Se ejecuta después de cada método de test para capturar screenshot si falla
     */
    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            String testName = result.getMethod().getMethodName();
            ScreenshotUtils.takeScreenshot(driver, testName);
            System.out.println("Screenshot tomado para test fallido: " + testName);
        }
    }
}