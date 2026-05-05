package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Page Object para la página del carrito
 */
public class CartPage extends BasePage {

    // Localizadores
    private By cartContainer = By.xpath("//div[@class='t-myBag__productList']");
    private By productItems = By.xpath("//div[@class='o-myBag o-myBag--giftTable']");
    private By productNames = By.xpath("//p[@class='a-inlineElement a-inlineElement--enphasis liverpool no-witdh']");
    private By checkoutButton = By.xpath("//button[contains(@class, 'a-btn-myBag--elements') and contains(., 'Comprar ahora')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si el carrito está vacío
     */
    public boolean isCartEmpty() {
        try {
            List<WebElement> items = driver.findElements(productItems);
            return items.isEmpty() || items.size() == 0;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Obtiene la cantidad de productos en el carrito
     */
    public int getCartItemCount() {
        try {
            List<WebElement> items = driver.findElements(productItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verifica si un producto está en el carrito por nombre
     */
    public boolean isProductInCart(String productName) {
        try {
            List<WebElement> names = driver.findElements(productNames);
            for (WebElement name : names) {
                if (name.getText().contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene todos los nombres de productos en el carrito
     */
    public List<String> getAllProductNames() {
        try {
            List<WebElement> names = driver.findElements(productNames);
            List<String> productNames = new java.util.ArrayList<>();
            for (WebElement name : names) {
                productNames.add(name.getText());
            }
            return productNames;
        } catch (Exception e) {
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Obtiene el precio total del carrito
     */
    public String getTotalPrice() {
        try {
            By totalLocator = By.xpath("//p[@class='a-inlineElement a-inlineElement--enphasis a-inlineElement--total change_color']");
            List<WebElement> totals = driver.findElements(totalLocator);
            if (!totals.isEmpty()) {
                return totals.get(totals.size() - 1).getText();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Hace click en botón "Comprar ahora" para ir a checkout
     */
    public void clickCheckoutButton() {
        click(checkoutButton);
    }

    /**
     * Verifica si el carrito se cargó correctamente
     */
    public boolean isCartPageDisplayed() {
        try {
            return driver.findElement(cartContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}