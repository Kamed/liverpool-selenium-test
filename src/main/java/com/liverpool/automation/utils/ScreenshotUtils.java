package com.liverpool.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidades para captura de pantallas
 */
public class ScreenshotUtils {
    private static final String SCREENSHOT_PATH = "reports/screenshots/";

    /**
     * Captura pantalla y la guarda
     */
    public static String takeScreenshot(WebDriver driver, String testName) throws IOException {
        // Crear carpeta si no existe
        Files.createDirectories(Paths.get(SCREENSHOT_PATH));

        // Generar nombre con timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_PATH + fileName;

        // Capturar pantalla
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshotFile.toPath(), Paths.get(filePath));

        return filePath;
    }
}