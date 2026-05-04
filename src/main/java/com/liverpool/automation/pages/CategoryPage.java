package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para páginas de categoría
 */
public class CategoryPage extends BasePage {

    private By subcategoryLink = By.xpath("//a[contains(text(), '%s')]");
    private By categoryTitle = By.xpath("//h1");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Hace click en una subcategoría
     */
    public void clickSubcategory(String subcategoryName) {
        By subcategoryLocator = By.xpath(String.format("//a[contains(text(), '%s')]", subcategoryName));
        click(subcategoryLocator);
    }

    /**
     * Obtiene el título de la categoría
     */
    public String getCategoryTitle() {
        return getText(categoryTitle);
    }

    /**
     * Verifica si estamos en la página de categoría
     */
    public boolean isCategoryPageDisplayed() {
        return isElementDisplayed(categoryTitle);
    }
}