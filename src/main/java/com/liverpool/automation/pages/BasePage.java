package com.liverpool.automation.pages;

import com.liverpool.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Clase base para todos los Page Objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Hace click en un elemento
     */
    public void click(By locator) {
        WebElement element = waitUtils.waitForElementClickable(locator);
        element.click();
    }

    /**
     * Ingresa texto en un campo
     */
    public void sendKeys(By locator, String text) {
        WebElement element = waitUtils.waitForElementVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Obtiene el texto de un elemento
     */
    public String getText(By locator) {
        return waitUtils.waitForElementVisibility(locator).getText();
    }

    /**
     * Verifica si un elemento es visible
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return waitUtils.waitForElementVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene el atributo de un elemento
     */
    public String getAttribute(By locator, String attributeName) {
        return waitUtils.waitForElementVisibility(locator).getAttribute(attributeName);
    }

    /**
     * Ejecuta JavaScript
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    /**
     * Navega a una URL
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Obtiene el título de la página
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Obtiene la URL actual
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Espera a que la página cargue
     */
    public void waitForPageLoad() {
        waitUtils.waitForUrlContains("");
    }
}