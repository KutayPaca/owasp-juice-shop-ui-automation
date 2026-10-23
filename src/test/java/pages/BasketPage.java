package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.ElementHelper;

import java.time.Duration;

public class BasketPage {
    private final ElementHelper elementHelper;
    private final WebDriverWait wait;

    public BasketPage() {
        this.elementHelper = new ElementHelper();
        this.wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    // LOCATORS

    //adet yazısı
    private final By quantitySpan = By.cssSelector("mat-row mat-cell.cdk-column-quantity > span");

    //artırma butonu
    private final By plusButton = By.cssSelector("mat-row mat-cell.cdk-column-quantity button:last-of-type");

    //birim fiyat hücresi
    private final By unitPriceCell = By.cssSelector("mat-row mat-cell.cdk-column-price");

    //toplam tutar
    private final By totalPriceLabel = By.id("price");

    // Sepetteki ürünü silen çöp kutusu butonu
    private final By deleteProductButton = By.cssSelector("mat-row mat-cell.cdk-column-remove button");


    //Sepetteki ürünün adedini 1 artırır ve sayının güncellenmesini bekler.
    public void urunAdediniArttir() {
        //Ekrandaki mevcut adedi oku
        String currentQty = getUrunAdedi();
        int expectedQty = Integer.parseInt(currentQty) + 1;

        //Artı butonuna bir kez tıkla
        elementHelper.clickWithJS(plusButton);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(quantitySpan, String.valueOf(expectedQty)));
    }


    // Güncel ürün adedini döner.
    public String getUrunAdedi() {
        return elementHelper.getText(quantitySpan).trim();
    }


    //Birim fiyatı okur ve double tipine çevirir
    public double getBirimFiyat() {
        String text = elementHelper.getText(unitPriceCell).replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(text);
    }


    //Toplam tutarı okur ve double tipine çevirir
    public double getToplamTutar() {
        String text = elementHelper.getText(totalPriceLabel).replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(text);
    }

    // Sepetteki ürünü siler ve satırın kaybolmasını bekler
    public void urunuSepettenSil() {
        WebElement row = Driver.getDriver().findElement(By.cssSelector("mat-row"));
        elementHelper.clickWithJS(deleteProductButton);
        wait.until(ExpectedConditions.stalenessOf(row));
    }
}