package com.liverpool.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Centraliza todas las estrategias de espera (Explicit Waits)
 * Elimina la necesidad de Thread.sleep()
 */
public class WaitUtils {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int DEFAULT_TIMEOUT = 15;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    // ============ ESPERAS A NIVEL DE ELEMENTO ============

    /**
     * Espera a que un elemento esté presente en el DOM
     */
    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Espera a que un elemento sea visible
     */
    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Espera a que un elemento sea clickeable
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Espera a que un elemento desaparezca del DOM
     */
    public boolean waitForElementInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Espera a que el texto de un elemento sea el esperado
     */
    public boolean waitForTextInElement(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // ============ ESPERAS A NIVEL DE PÁGINA ============

    /**
     * Espera a que la URL contenga un texto específico
     */
    public void waitForUrlContains(String urlPortion) {
        wait.until(ExpectedConditions.urlContains(urlPortion));
    }

    /**
     * Espera a que la URL sea exacta
     */
    public void waitForUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Espera a que la página cargue completamente (readyState = complete)
     */
    public void waitForPageToLoad() {
        wait.until(driver -> {
            Object result = ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return document.readyState");
            return result.equals("complete");
        });
    }

    /**
     * Espera genérica en segundos (usar solo cuando sea absolutamente necesario)
     * NOTA: Esto es último recurso, preferir esperas explícitas
     */
    public void waitSeconds(int seconds) {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Espera a que múltiples elementos estén presentes
     */
    public void waitForMultipleElementsPresence(By locator, int expectedCount) {
        wait.until(driver -> driver.findElements(locator).size() >= expectedCount);
    }

    /**
     * Espera a que el título de la página contenga un texto
     */
    public void waitForTitleContains(String titlePortion) {
        wait.until(ExpectedConditions.titleContains(titlePortion));
    }

    /**
     * Espera a que el título sea exacto
     */
    public void waitForTitleToBe(String title) {
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Espera a que haya al menos una alerta presente
     */
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Espera a que un elemento sea seleccionable
     */
    public void waitForElementSelectable(By locator) {
        wait.until(ExpectedConditions.elementSelectionStateToBe(locator, true));
    }
}