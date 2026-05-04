package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de Login
 */
public class LoginPage extends BasePage {

    // Localizadores
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//button[contains(text(), 'Iniciar sesión')]");
    private By errorMessage = By.className("error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navega a la página de login
     */
    public void navigateToLoginPage(String baseUrl) {
        navigateTo(baseUrl + "/u/login");
    }

    /**
     * Ingresa email
     */
    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    /**
     * Ingresa contraseña
     */
    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    /**
     * Hace click en botón de login
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Realiza login completo
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Verifica si hay mensaje de error
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Obtiene mensaje de error
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
}