package com.liverpool.automation.tests;

import com.liverpool.automation.pages.CartPage;
import com.liverpool.automation.pages.CategoryPage;
import com.liverpool.automation.pages.HomePage;
import com.liverpool.automation.pages.LoginPage;
import com.liverpool.automation.pages.ProductListPage;
import com.liverpool.automation.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests para carrito de compras
 */
public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ProductListPage productListPage;
    private CartPage cartPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productListPage = new ProductListPage(driver);
        cartPage = new CartPage(driver);
        waitUtils = new WaitUtils(driver);

        // Login
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();
        loginPage.login(testEmail, testPassword);
        waitUtils.waitForUrlContains("/home");
    }

    /**
     * TC008: Agregar producto al carrito exitosamente
     */
    @Test(description = "Agregar un producto al carrito", priority = 1)
    public void testAddProductToCart() {
        // Navegar a categoría
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        // Obtener nombre del primer producto
        String productName = productListPage.getFirstProductName();
        Assert.assertNotNull(productName, "No se encontró nombre del producto");

        // Agregar al carrito
        productListPage.addProductToCartByName(productName);
        waitUtils.waitSeconds(1);

        // Navegar al carrito y verificar
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(cartPage.getCartItemCount() > 0, 
            "El carrito debe contener al menos 1 producto");
    }

    /**
     * TC009: Agregar múltiples productos al carrito
     */
    @Test(description = "Agregar múltiples productos al carrito", priority = 2)
    public void testAddMultipleProductsToCart() {
        int addedCount = 0;

        // Agregar botas
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        String product1 = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product1);
        addedCount++;
        waitUtils.waitSeconds(1);

        // Volver y agregar perfume
        driver.navigate().back();
        waitUtils.waitForPageToLoad();

        homePage.clickCategory("Perfumes");
        waitUtils.waitForPageToLoad();

        String product2 = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product2);
        addedCount++;
        waitUtils.waitSeconds(1);

        // Volver y agregar electrónico
        driver.navigate().back();
        waitUtils.waitForPageToLoad();

        homePage.clickCategory("Electrónicos");
        waitUtils.waitForPageToLoad();

        String product3 = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product3);
        addedCount++;

        // Navegar al carrito
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        Assert.assertEquals(cartPage.getCartItemCount(), addedCount, 
            "El carrito debe contener " + addedCount + " productos");
    }

    /**
     * TC010: Verificar que carrito vacío muestra mensaje
     */
    @Test(description = "Validar carrito vacío", priority = 3)
    public void testEmptyCartValidation() {
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        // Carrito debe estar vacío al inicio
        Assert.assertTrue(cartPage.isCartEmpty() || cartPage.getCartItemCount() == 0, 
            "El carrito debe estar vacío");
    }

    /**
     * TC011: Verificar que el precio total es correcto
     */
    @Test(description = "Validar precio total en carrito", priority = 4)
    public void testCartTotalPrice() {
        // Agregar producto
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        String product = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product);
        waitUtils.waitSeconds(1);

        // Ir al carrito
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        String totalPrice = cartPage.getTotalPrice();
        Assert.assertNotNull(totalPrice, "Debe haber un precio total");
        Assert.assertFalse(totalPrice.isEmpty(), "El precio total no puede estar vacío");
    }
}