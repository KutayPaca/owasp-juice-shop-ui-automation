package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementHelper;

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
}



