package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;
import utilities.ElementHelper;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private ElementHelper elementHelper;

    public HomePage() {
        this.elementHelper = new ElementHelper();
    }

    //LOCATORS (Elementlerin HTML'deki Adresleri)
    // Hoş geldiniz pop up ındaki kapat butonu
    private final By dismissPopupButton = By.cssSelector("button[aria-label='Close Welcome Banner']");
    // alt kısmındaki çerez bildirimindeki we want it butonu
    private final By acceptCookiesButton = By.cssSelector("a[aria-label='dismiss cookie message']");
    // sağ üstteki account menüsü butonu
    private final By accountButton = By.id("navbarAccount");
    //account menüsü açılınca görünen login butonu
    private final By loginButton = By.id("navbarLoginButton");
    //üst bardaki büyüteç butonu
    private final By searchIconButton = By.cssSelector("#searchQuery button");
    //büyütece tıkladıktan sonra açılan text boxın locatoru, ID bulamadım
    //DOM hiyerarşisine baktığında bu arkadaş doğrudan kimin çocuğu
    //searchQuery ebeveyninin o yüzden
    private final By searchInputField = By.cssSelector("#searchQuery input");
    // Arama sonucu gelen ürün kartlarının başlıkları
    private final By productTitles = By.cssSelector("div.name");

    public void ilkAcilisEngelleriniKapat(){
        //pop up ekranda belirdiyse kapat
        if (elementHelper.isDisplayed(dismissPopupButton)) {
            elementHelper.click(dismissPopupButton);
        }
        //çerez bildirimi ekranda belirdiyse kabul et
        if (elementHelper.isDisplayed(acceptCookiesButton)) {
            elementHelper.click(acceptCookiesButton);
        }
    }
    //önce account butonuna tıkla ardından login butonuna tıkla
    public void loginSayfasinaGit(){
        elementHelper.click(accountButton);
        elementHelper.click(loginButton);
    }
    public void urunAra(String arananUrun){
        // Arama butonuna tıkla (input kutusunun belirmesi için)
        elementHelper.click(searchIconButton);
        //açılan input alanına kelimeyi yaz ve ENTER bas
        elementHelper.sendKeysWithKey(searchInputField, arananUrun, Keys.ENTER);
    }

    public List<String> getAramaSonuclariBasliklari() {
        // 1. Sayfadaki tüm ürün başlığı elementlerini çek
        List<WebElement> elements = elementHelper.getElements(productTitles);

        // 2. Bu elementlerin içindeki yazıları bir String listesine doldur
        List<String> urunAdlari = new ArrayList<>();
        for (WebElement element : elements) {
            urunAdlari.add(element.getText().toLowerCase());
        }
        return urunAdlari;
    }
}



