package com.liverpool.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para el menú de categorías (navegación lateral)
 */
public class CategoriesPage extends BasePage {

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Hace click en una categoría principal por href
     * @param categoryHref El href de la categoría (ej: "/tienda/zapatos/cat5040494")
     */
    public void clickMainCategory(String categoryHref) {
        By categoryLocator = By.xpath("//a[@href='" + categoryHref + "']");
        click(categoryLocator);
    }

    /**
     * Hace click en una subcategoría por href
     * @param subcategoryHref El href de la subcategoría
     */
    public void clickSubcategory(String subcategoryHref) {
        By subcategoryLocator = By.xpath("//a[@href='" + subcategoryHref + "']");
        click(subcategoryLocator);
    }

    /**
     * Hace click en un 3er nivel de categoría por href
     * @param thirdLevelHref El href del 3er nivel
     */
    public void clickThirdLevelCategory(String thirdLevelHref) {
        By thirdLevelLocator = By.xpath("//a[@href='" + thirdLevelHref + "']");
        click(thirdLevelLocator);
    }
}