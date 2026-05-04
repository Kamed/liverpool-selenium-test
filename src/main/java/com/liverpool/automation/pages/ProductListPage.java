package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la lista de productos
 */
public class ProductListPage extends BasePage {

    private By productItem = By.xpath("//div[contains(@class, 'product')]");
    private By addToCartButton = By.xpath("//button[contains(text(), 'Agregar al carrito')]");
    private By productName = By.xpath("//span[contains(@class, 'product-name')]");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si la lista de productos se muestra
     */
    public boolean isProductListDisplayed() {
        return isElementDisplayed(productItem);
    }

    /**
     * Busca y agrega un producto al carrito por nombre
     */
    public void addProductToCartByName(String productName) {
        By productXpath = By.xpath(String.format("//div[contains(@class, 'product')]//span[contains(text(), '%s')]/ancestor::div[@class='product']//button[contains(text(), 'Agregar')]", productName));
        click(productXpath);
    }

    /**
     * Obtiene el nombre del primer producto
     */
    public String getFirstProductName() {
        return getText(productName);
    }

    /**
     * Verifica si un producto específico existe
     */
    public boolean isProductExists(String productName) {
        By productXpath = By.xpath(String.format("//span[contains(text(), '%s')]", productName));
        return isElementDisplayed(productXpath);
    }
}