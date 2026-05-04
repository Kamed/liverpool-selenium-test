package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de checkout
 */
public class CheckoutPage extends BasePage {

    private By orderSummary = By.xpath("//div[@id='order-summary']");
    private By shippingAddress = By.xpath("//div[@id='shipping-address']");
    private By paymentMethod = By.xpath("//div[@id='payment-method']");
    private By orderTotal = By.xpath("//span[@id='order-total']");
    private By completeOrderButton = By.xpath("//button[contains(text(), 'Completar Orden')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica que estamos en la página de checkout
     */
    public boolean isCheckoutPageDisplayed() {
        return isElementDisplayed(orderSummary);
    }

    /**
     * Obtiene el total de la orden
     */
    public String getOrderTotal() {
        return getText(orderTotal);
    }

    /**
     * Verifica que la dirección de envío esté visible
     */
    public boolean isShippingAddressDisplayed() {
        return isElementDisplayed(shippingAddress);
    }

    /**
     * Verifica que el método de pago esté visible
     */
    public boolean isPaymentMethodDisplayed() {
        return isElementDisplayed(paymentMethod);
    }

    /**
     * Completa la orden
     */
    public void completeOrder() {
        click(completeOrderButton);
    }
}