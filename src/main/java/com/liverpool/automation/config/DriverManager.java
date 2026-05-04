package com.liverpool.automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Gestiona la instancia del WebDriver
 * Responsable de inicializar y cerrar el navegador
 */
public class DriverManager {
    private static WebDriver driver;

    /**
     * Inicializa el WebDriver según el navegador especificado
     * @param browser Nombre del navegador (chrome, firefox)
     * @return Instancia del WebDriver
     */
    public static WebDriver initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Navegador no soportado: " + browser);
        }

        return driver;
    }

    /**
     * Obtiene la instancia actual del WebDriver
     * @return Instancia del WebDriver
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("WebDriver no ha sido inicializado");
        }
        return driver;
    }

    /**
     * Cierra el WebDriver y libera recursos
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}