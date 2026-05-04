package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para el carrito de compras
 */
public class CartPage extends BasePage {

    private By cartItemCount = By.xpath("//span[@id='cart-count']");
    private By cartEmptyMessage = By.xpath("//p[contains(text(), 'vacío')]");
    private By cartItems = By.xpath("//tr[@class='cart-item']");
    private By totalPrice = By.xpath("//span[@id='total-price']");
    private By checkoutButton = By.xpath("//button[contains(text(), 'Comprar ahora')]");
    private By quantityInput = By.xpath("//input[@class='quantity']");
    private By removeButton = By.xpath("//button[contains(text(), 'Eliminar')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Obtiene el count de items en el carrito
     */
    public int getCartItemCount() {
        String countText = getText(cartItemCount);
        return Integer.parseInt(countText.replaceAll("\\D", ""));
    }

    /**
     * Verifica si el carrito está vacío
     */
    public boolean isCartEmpty() {
        return isElementDisplayed(cartEmptyMessage);
    }

    /**
     * Obtiene el precio total
     */
    public String getTotalPrice() {
        return getText(totalPrice);
    }

    /**
     * Navega al checkout
     */
    public void clickCheckoutButton() {
        click(checkoutButton);
    }

    /**
     * Verifica que el botón de checkout esté disponible
     */
    public boolean isCheckoutButtonAvailable() {
        return isElementDisplayed(checkoutButton);
    }
}