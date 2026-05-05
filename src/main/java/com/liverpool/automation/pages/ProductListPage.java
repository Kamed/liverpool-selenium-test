package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de lista de productos
 */
public class ProductListPage extends BasePage {

    // Localizadores
    private By productList = By.xpath("//ul[@class='m-product__listingPlp']");
    private By firstProductLink = By.xpath("//ul[@class='m-product__listingPlp']/li[1]//a");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si la lista de productos se muestra
     */
    public boolean isProductListDisplayed() {
        try {
            return driver.findElement(productList).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Hace click en el primer producto de la lista
     */
    public void clickFirstProduct() {
        click(firstProductLink);
    }

    /**
     * Obtiene el nombre del primer producto
     */
    public String getFirstProductName() {
        try {
            return driver.findElement(firstProductLink).getText();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Hace click en un producto por nombre
     */
    public void clickProductByName(String productName) {
        By productLocator = By.xpath(String.format(
                "//ul[@class='m-product__listingPlp']//a[contains(., '%s')]", productName));
        click(productLocator);
    }
}