package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de Login
 */
public class LoginPage extends BasePage {

    // Localizadores
    private By emailInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ingresa el email
     */
    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    /**
     * Ingresa la contraseña
     */
    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    /**
     * Hace click en botón "Iniciar sesión"
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
}