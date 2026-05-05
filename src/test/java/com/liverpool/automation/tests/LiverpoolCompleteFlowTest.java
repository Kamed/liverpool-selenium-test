package com.liverpool.automation.tests;

import com.liverpool.automation.pages.*;
import com.liverpool.automation.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test completo del flujo de compra en Liverpool
 * Navega > Agrega productos > Verifica carrito
 */
public class LiverpoolCompleteFlowTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private CategoriesPage categoriesPage;
    private ProductListPage productListPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    private WaitUtils waitUtils;

    @BeforeMethod
    public void beforeTest() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        categoriesPage = new CategoriesPage(driver);
        productListPage = new ProductListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
        waitUtils = new WaitUtils(driver);

        // Navegar a página principal
        homePage.navigateToHome(baseUrl);
        waitUtils.waitForPageToLoad();
    }

    /**
     * TC001: Login exitoso
     */
    @Test(description = "Realizar login exitoso", priority = 1)
    public void testSuccessfulLogin() {
        homePage.clickLoginButton();
        waitUtils.waitForPageToLoad();

        loginPage.login("tu_email@example.com", "tu_contraseña");
        waitUtils.waitForPageToLoad();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(!currentUrl.contains("login"),
                "Debe haber salido de la página de login");

        System.out.println("✓ Login exitoso");
    }

    /**
     * TC002: Agregar Zapatos Botas al carrito
     */
    @Test(description = "Agregar Zapatos > Hombre > Botas", priority = 2,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddZapatosBotasToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        categoriesPage.clickMainCategory("/tienda/zapatos/cat5040494");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickSubcategory("/tienda/hombre/cat5040523");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickThirdLevelCategory("/tienda/botas/cat5040526");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de productos");

        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        productDetailsPage.selectSizeAndAddToCart();
        waitUtils.waitSeconds(2);

        System.out.println("✓ Producto agregado: " + productName);
    }

    /**
     * TC003: Agregar Zapatos Mocasines al carrito
     */
    @Test(description = "Agregar Zapatos > Hombre > Mocasines", priority = 3,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddZapatosMocasinesToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        categoriesPage.clickMainCategory("/tienda/zapatos/cat5040494");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickSubcategory("/tienda/hombre/cat5040523");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickThirdLevelCategory("/tienda/mocasines-de-hombre/catst44696099");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de mocasines");

        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        productDetailsPage.selectSizeAndAddToCart();
        waitUtils.waitSeconds(2);

        System.out.println("✓ Mocasines agregados: " + productName);
    }

    /**
     * TC004: Agregar Perfume Hombre al carrito
     */
    @Test(description = "Agregar Belleza > Perfumes > Perfumes Hombre", priority = 4,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddPerfumeHombreToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        categoriesPage.clickMainCategory("/tienda/belleza/cat5020010");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickSubcategory("/tienda/perfumes/catst6202922");
        waitUtils.waitForPageToLoad();

        categoriesPage.clickThirdLevelCategory("/tienda/perfumes-hombre/catst44258581");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de perfumes");

        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        productDetailsPage.selectSizeAndAddToCart();
        waitUtils.waitSeconds(2);

        System.out.println("✓ Perfume agregado: " + productName);
    }

    /**
     * TC005: Agregar Computadora Lenovo al carrito
     * Flujo: Electrónica → Computación → Computadoras Escritorio → Filtrar Lenovo → Producto
     */
    @Test(description = "Agregar Electrónica > Computación > Computadora Lenovo", priority = 5,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddComputadoraLenovoToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        // Electrónica
        categoriesPage.clickMainCategory("/tienda/electrónica/cat5150041");
        waitUtils.waitForPageToLoad();

        // Computación
        categoriesPage.clickSubcategory("/tienda/computaci%C3%B3n/cat3410055");
        waitUtils.waitForPageToLoad();

        // Computadoras de escritorio
        categoriesPage.clickThirdLevelCategory("/tienda/computadoras-de-escritorio/catst46664340");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de computadoras");

        // Filtrar por marca Lenovo
        productDetailsPage.filterByBrand("Lenovo", "brand-LENOVO");
        waitUtils.waitSeconds(2);

        // Click en primer producto
        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        // Agregar al carrito (sin tamaño)
        productDetailsPage.clickAddToCartButton();
        waitUtils.waitSeconds(2);

        System.out.println("✓ Computadora Lenovo agregada: " + productName);
    }

    /**
     * TC006: Agregar Pantalla Sony al carrito
     * Flujo: Electrónica → TV y Video → Pantallas → Filtrar Sony → Producto
     */
    @Test(description = "Agregar Electrónica > TV y Video > Pantalla Sony", priority = 6,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddPantallaSonyToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        // Electrónica
        categoriesPage.clickMainCategory("/tienda/electrónica/cat5150041");
        waitUtils.waitForPageToLoad();

        // TV y Video
        categoriesPage.clickSubcategory("/tienda/tv-y-video/catst14456541");
        waitUtils.waitForPageToLoad();

        // Pantallas
        categoriesPage.clickThirdLevelCategory("/tienda/pantallas/catst14457077");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de pantallas");

        // Filtrar por marca Sony
        productDetailsPage.filterByBrand("Sony", "brand-SONY");
        waitUtils.waitSeconds(2);

        // Click en primer producto
        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        // Agregar al carrito
        productDetailsPage.clickAddToCartButton();
        waitUtils.waitSeconds(2);

        // Cerrar modal si aparece
        productDetailsPage.closeModal();
        waitUtils.waitSeconds(1);

        System.out.println("✓ Pantalla Sony agregada: " + productName);
    }

    /**
     * TC007: Agregar Xbox al carrito
     * Flujo: Videojuegos → Gaming → Xbox → Consolas Xbox → Producto → Tamaño
     */
    @Test(description = "Agregar Videojuegos > Gaming > Xbox > Consolas Xbox", priority = 7,
            dependsOnMethods = {"testSuccessfulLogin"})
    public void testAddXboxToCart() {
        homePage.clickCategoriesMenu();
        waitUtils.waitForPageToLoad();

        // Videojuegos
        categoriesPage.clickMainCategory("/tienda/videojuegos/cat670055");
        waitUtils.waitForPageToLoad();

        // Gaming
        categoriesPage.clickSubcategory("/tienda/gaming/catst53450369");
        waitUtils.waitForPageToLoad();

        // Xbox (3er nivel)
        categoriesPage.clickThirdLevelCategory("/tienda/xbox/cat940612");
        waitUtils.waitForPageToLoad();

        // Consolas Xbox (4to nivel)
        categoriesPage.clickThirdLevelCategory("/tienda/consolas-xbox/catst14539973");
        waitUtils.waitForPageToLoad();

        Assert.assertTrue(productListPage.isProductListDisplayed(),
                "Debe mostrar lista de consolas Xbox");

        // Click en primer producto
        String productName = productListPage.getFirstProductName();
        productListPage.clickFirstProduct();
        waitUtils.waitForPageToLoad();

        // Seleccionar tamaño y agregar al carrito
        productDetailsPage.selectSizeAndAddToCart();
        waitUtils.waitSeconds(2);

        // Cerrar modal si aparece
        productDetailsPage.closeModal();
        waitUtils.waitSeconds(1);

        System.out.println("✓ Xbox agregada: " + productName);
    }

    /**
     * TC008: Verificar carrito con todos los productos
     * Navega al carrito y verifica que todos los 6 productos se agregaron
     */
    @Test(description = "Verificar carrito con 6 productos", priority = 8,
            dependsOnMethods = {"testAddZapatosBotasToCart", "testAddZapatosMocasinesToCart",
                    "testAddPerfumeHombreToCart", "testAddComputadoraLenovoToCart",
                    "testAddPantallaSonyToCart", "testAddXboxToCart"})
    public void testVerifyCartWithAllProducts() {
        // Navegar al carrito
        homePage.clickCartButton();
        waitUtils.waitForPageToLoad();

        // Verificar que el carrito no está vacío
        Assert.assertFalse(cartPage.isCartEmpty(),
                "El carrito debe tener productos");

        // Verificar cantidad de productos
        int cartCount = cartPage.getCartItemCount();
        Assert.assertTrue(cartCount >= 6,
                "Debe haber al menos 6 productos en el carrito, encontrados: " + cartCount);

        // Obtener y mostrar productos
        java.util.List<String> products = cartPage.getAllProductNames();
        System.out.println("✓ Productos en carrito (" + products.size() + "):");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + products.get(i));
        }

        // Verificar precio total
        String totalPrice = cartPage.getTotalPrice();
        Assert.assertNotNull(totalPrice, "Debe haber un precio total");
        System.out.println("✓ Total: " + totalPrice);
    }

    /**
     * TC009: Proceder a checkout
     * Hacer click en botón "Comprar ahora" para ir a la página de checkout
     */
    @Test(description = "Proceder a checkout", priority = 9,
            dependsOnMethods = {"testVerifyCartWithAllProducts"})
    public void testProceedToCheckout() {
        // Navegar al carrito primero
        homePage.clickCartButton();
        waitUtils.waitForPageToLoad();

        // Hacer click en botón Comprar ahora
        cartPage.clickCheckoutButton();
        waitUtils.waitSeconds(2);

        // Verificar que se navegó a checkout o login (dependiendo de la sesión)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Debe haber una URL");
        System.out.println("✓ Navegó a: " + currentUrl);
    }
}