package com.liverpool.automation.pages;

import com.liverpool.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Page Object para la página de detalles del producto
 */
public class ProductDetailsPage extends BasePage {

    // Localizadores
    private By sizeListContainer = By.id("size-list-container");
    private By firstSizeButton = By.xpath("//ul[@id='size-list-container']/li[1]//button");
    private By addToCartButton = By.id("opc_pdp_addCartButton");
    private By searchBrandInput = By.id("searchBrand");
    private By closeButton = By.xpath("//i[@class='icon-close close']");

    private WaitUtils waitUtils;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Verifica si la lista de tamaños se muestra
     */
    public boolean isSizeListDisplayed() {
        try {
            return driver.findElement(sizeListContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Hace click en el primer tamaño disponible
     */
    public void clickFirstSizeBKP() {
        click(firstSizeButton);
    }

    /**
     * Hace click en el primer tamaño disponible
     */
    public void clickFirstSize() {
        try {
            waitUtils.waitSeconds(1);

            // Intentar con JavaScript click (más confiable)
            var element = driver.findElement(firstSizeButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            System.out.println("Click en tamaño realizado con JavaScript");
        } catch (Exception e) {
            System.out.println("Error al hacer click en tamaño: " + e.getMessage());
            // Intentar con click normal como fallback
            try {
                click(firstSizeButton);
            } catch (Exception e2) {
                System.out.println("Falló tanto JavaScript como click normal");
                throw e2;
            }
        }
    }

    /**
     * Selecciona un tamaño específico
     * @param sizeValue El valor del tamaño (ej: "25 cm", "M", etc.)
     */
    public void selectSize(String sizeValue) {
        By sizeLocator = By.xpath(String.format(
                "//ul[@id='size-list-container']//button[contains(., '%s')]", sizeValue));
        click(sizeLocator);
    }

    /**
     * Verifica si el botón "Agregar a mi bolsa" está disponible
     */
    public boolean isAddToCartButtonAvailable() {
        try {
            return driver.findElement(addToCartButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Hace click en "Agregar a mi bolsa"
     */
    public void clickAddToCartButton() {
        click(addToCartButton);
    }

    /**
     * Flujo completo: selecciona tamaño y agrega al carrito
     */
    public void selectSizeAndAddToCart() {
        clickFirstSize();
        waitUtils.waitSeconds(1);
        clickAddToCartButton();
    }

    /**
     * Obtiene el nombre del producto
     */
    public String getProductName() {
        try {
            By productNameLocator = By.xpath("//h1[@class='a-productName']");
            return driver.findElement(productNameLocator).getText();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Ingresa texto en el filtro de marca
     * @param brandName Nombre de la marca (ej: "Lenovo", "Sony")
     */
    public void searchBrand(String brandName) {
        sendKeys(searchBrandInput, brandName);
    }

    /**
     * Selecciona un checkbox de marca
     * @param brandId El ID de la marca (ej: "brand-LENOVO", "brand-SONY")
     */
    public void selectBrandCheckbox(String brandId) {
        By brandCheckbox = By.id(brandId);
        click(brandCheckbox);
    }

    /**
     * Cierra el modal o popup
     */
    public void closeModal() {
        try {
            click(closeButton);
        } catch (Exception e) {
            System.out.println("No se encontró botón de cierre");
        }
    }

    /**
     * Flujo para filtrar por marca (sin seleccionar tamaño)
     */
    public void filterByBrand(String brandName, String brandId) {
        searchBrand(brandName);
        waitUtils.waitSeconds(1);
        selectBrandCheckbox(brandId);
        waitUtils.waitSeconds(2); // Esperar a que se actualice la lista
    }
}