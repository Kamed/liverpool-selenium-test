package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página principal
 */
public class HomePage extends BasePage {

    // Localizadores
    private By categoryMenu = By.xpath("//a[contains(text(), '%s')]");
    private By welcomeMessage = By.xpath("//h1[contains(text(), 'Bienvenido')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Hace click en una categoría del menú
     */
    public void clickCategory(String categoryName) {
        By categoryLocator = By.xpath(String.format("//a[contains(text(), '%s')]", categoryName));
        click(categoryLocator);
    }

    /**
     * Verifica si el usuario está logueado
     */
    public boolean isUserLoggedIn() {
        return isElementDisplayed(welcomeMessage);
    }

    /**
     * Obtiene el título de la página
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}