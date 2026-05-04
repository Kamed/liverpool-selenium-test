package com.liverpool.automation.tests;

import com.liverpool.automation.pages.CartPage;
import com.liverpool.automation.pages.CategoryPage;
import com.liverpool.automation.pages.CheckoutPage;
import com.liverpool.automation.pages.HomePage;
import com.liverpool.automation.pages.LoginPage;
import com.liverpool.automation.pages.ProductListPage;
import com.liverpool.automation.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests para checkout
 */
public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ProductListPage productListPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productListPage = new ProductListPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        waitUtils = new WaitUtils(driver);

        // Login
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();
        loginPage.login(testEmail, testPassword);
        waitUtils.waitForUrlContains("/home");
    }

    /**
     * TC012: Navegar a checkout desde carrito
     */
    @Test(description = "Navegar a página de checkout", priority = 1)
    public void testNavigateToCheckout() {
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

        // Verificar que botón de checkout está disponible
        Assert.assertTrue(cartPage.isCheckoutButtonAvailable(), 
            "Debe haber botón de checkout disponible");

        // Hacer click en checkout
        cartPage.clickCheckoutButton();
        waitUtils.waitForPageToLoad();

        // Verificar que se navigó a checkout
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), 
            "Debe estar en página de checkout");
    }

    /**
     * TC013: Validar información en página de checkout
     */
    @Test(description = "Validar información en checkout", priority = 2)
    public void testCheckoutPageInformation() {
        // Agregar 2 productos
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        String product1 = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product1);
        waitUtils.waitSeconds(1);

        driver.navigate().back();
        waitUtils.waitForPageToLoad();

        homePage.clickCategory("Perfumes");
        waitUtils.waitForPageToLoad();

        String product2 = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product2);
        waitUtils.waitSeconds(1);

        // Ir a checkout
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        cartPage.clickCheckoutButton();
        waitUtils.waitForPageToLoad();

        // Validar información
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), 
            "Debe estar en página de checkout");
        Assert.assertTrue(checkoutPage.isShippingAddressDisplayed(), 
            "Debe mostrar dirección de envío");
        Assert.assertTrue(checkoutPage.isPaymentMethodDisplayed(), 
            "Debe mostrar método de pago");

        String orderTotal = checkoutPage.getOrderTotal();
        Assert.assertNotNull(orderTotal, "Debe mostrar total de la orden");
        Assert.assertFalse(orderTotal.isEmpty(), "Total de orden no puede estar vacío");
    }

    /**
     * TC014: Validar que no se puede ir a checkout con carrito vacío
     */
    @Test(description = "Validar que carrito vacío no permite checkout", priority = 3)
    public void testCheckoutWithEmptyCart() {
        // Ir directamente al carrito
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        // Verificar que el botón de checkout no está disponible
        Assert.assertFalse(cartPage.isCheckoutButtonAvailable(), 
            "No debe haber botón de checkout en carrito vacío");
    }

    /**
     * TC015: Validar precio total entre carrito y checkout
     */
    @Test(description = "Validar que precio en carrito coincide con checkout", priority = 4)
    public void testPriceConsistencyCartToCheckout() {
        // Agregar producto
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        String product = productListPage.getFirstProductName();
        productListPage.addProductToCartByName(product);
        waitUtils.waitSeconds(1);

        // Ir al carrito y capturar precio
        driver.navigate().to(baseUrl + "/cart");
        waitUtils.waitForPageToLoad();

        String cartTotal = cartPage.getTotalPrice();

        // Ir a checkout
        cartPage.clickCheckoutButton();
        waitUtils.waitForPageToLoad();

        String checkoutTotal = checkoutPage.getOrderTotal();

        // Validar que los precios coinciden
        Assert.assertEquals(cartTotal, checkoutTotal, 
            "El total del carrito debe coincidir con el checkout");
    }
}