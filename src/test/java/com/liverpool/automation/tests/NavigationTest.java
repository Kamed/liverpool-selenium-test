package com.liverpool.automation.tests;

import com.liverpool.automation.pages.CategoryPage;
import com.liverpool.automation.pages.HomePage;
import com.liverpool.automation.pages.LoginPage;
import com.liverpool.automation.pages.ProductListPage;
import com.liverpool.automation.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests para navegación de categorías
 */
public class NavigationTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ProductListPage productListPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productListPage = new ProductListPage(driver);
        waitUtils = new WaitUtils(driver);

        // Login antes de cada test
        loginPage.navigateToLoginPage(baseUrl);
        waitUtils.waitForPageToLoad();
        loginPage.login(testEmail, testPassword);
        waitUtils.waitForUrlContains("/home");
    }

    /**
     * TC004: Navegar a categoría Zapatos de Hombre - Botas
     */
    @Test(description = "Navegar a Zapatos de Hombre > Botas", priority = 1)
    public void testNavigateToMenBoots() {
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Botas");
        waitUtils.waitForPageToLoad();

        // Verificar que se cargó la lista de productos
        Assert.assertTrue(productListPage.isProductListDisplayed(), 
            "No se cargó la lista de botas");
    }

    /**
     * TC005: Navegar a categoría Zapatos de Hombre - Mocasines
     */
    @Test(description = "Navegar a Zapatos de Hombre > Mocasines", priority = 2)
    public void testNavigateToMenMocasins() {
        homePage.clickCategory("Zapatos de Hombre");
        waitUtils.waitForPageToLoad();

        categoryPage.clickSubcategory("Mocasines");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(), 
            "No se cargó la lista de mocasines");
    }

    /**
     * TC006: Navegar a categoría Perfumes
     */
    @Test(description = "Navegar a categoría Perfumes", priority = 3)
    public void testNavigateToPerfumes() {
        homePage.clickCategory("Perfumes");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(), 
            "No se cargó la lista de perfumes");
    }

    /**
     * TC007: Navegar a categoría Electrónicos
     */
    @Test(description = "Navegar a categoría Electrónicos", priority = 4)
    public void testNavigateToElectronics() {
        homePage.clickCategory("Electrónicos");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(), 
            "No se cargó la lista de electrónicos");
    }
}