package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página principal (Home)
 */
public class HomePage extends BasePage {

    // Localizadores
    private By loginButton = By.xpath("//div[@data-testid='blt26617d4f2e17657d-header-menu-dropdown']");
    private By categoriesMenuButton = By.xpath("//button[@data-testid='blt26617d4f2e17657d-header-button-category']");
    private By cartButton = By.xpath("//button[contains(@class, 'a-header__bag LP')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navega a la página principal
     */
    public void navigateToHome(String baseUrl) {
        navigateTo(baseUrl);
    }

    /**
     * Hace click en botón "Iniciar Sesión"
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Abre el menú de categorías
     */
    public void clickCategoriesMenu() {
        click(categoriesMenuButton);
    }

    /**
     * Navega al carrito
     */
    public void clickCartButton() {
        click(cartButton);
    }

    /**
     * Obtiene el título de la página
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}