package com.liverpool.automation.tests;

import com.liverpool.automation.pages.LoginPage;
import com.liverpool.automation.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests para la funcionalidad de Login
 */
public class LoginTest extends BaseTest {

    /**
     * TC001: Login exitoso con credenciales válidas
     */
    @Test(description = "Realizar login exitoso con credenciales válidas", priority = 1)
    public void testSuccessfulLogin() {
        WaitUtils waitUtils = new WaitUtils(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();

        loginPage.login(testEmail, testPassword);
        waitUtils.waitForUrlContains("/home");

        // Verificar que se completó el login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"), 
            "El login no fue exitoso, aún en página de login");
    }

    /**
     * TC002: Login con credenciales inválidas
     */
    @Test(description = "Verificar error con credenciales inválidas", priority = 2)
    public void testLoginWithInvalidCredentials() {
        WaitUtils waitUtils = new WaitUtils(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();

        loginPage.login("usuario_invalido@test.com", "password_invalida");
        waitUtils.waitSeconds(2); // Esperar a que aparezca el error

        // Verificar que aparece error
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Debe mostrar mensaje de error con credenciales inválidas");
    }

    /**
     * TC003: Intentar login con email vacío
     */
    @Test(description = "Validar error cuando email está vacío", priority = 3)
    public void testLoginWithEmptyEmail() {
        WaitUtils waitUtils = new WaitUtils(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();

        loginPage.enterPassword(testPassword);
        loginPage.clickLoginButton();
        waitUtils.waitSeconds(1);

        // Aún debe estar en página de login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), 
            "Debe permanecer en login cuando email está vacío");
    }
}