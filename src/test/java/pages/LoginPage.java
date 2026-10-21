package pages;

import org.openqa.selenium.By;
import utilities.ElementHelper;

public class LoginPage {

    private ElementHelper elementHelper;

    public LoginPage(){
        this.elementHelper = new ElementHelper();
    }

    //locators
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("loginButton");

    //başarılı giriş yaptığımızı doğrulamak için
    private final By accountButton = By.id("navbarAccount");
    private final By logoutButton = By.id("navbarLogoutButton");

    public void bilgileriDoldurVeGirisYap(String email, String password){
        elementHelper.sendKeys(emailInput,email);
        elementHelper.sendKeys(passwordInput,password);
        elementHelper.click(loginButton);
    }

    public boolean cikisButonuGorunurMu(){
        //kullanıcı giriş yaptıktan sonra Account menüsüne tıklarız
        elementHelper.click(accountButton);
        //açılan menüde logout butonu belirdimi kontrol ederiz
        return elementHelper.isDisplayed(logoutButton);
    }
}
