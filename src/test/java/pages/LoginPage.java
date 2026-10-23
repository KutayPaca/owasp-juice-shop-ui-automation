package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import utilities.ElementHelper;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private ElementHelper elementHelper;
    private final WebDriverWait wait;

    public LoginPage(){
        this.elementHelper = new ElementHelper();
        this.wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    }

    //locators
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("loginButton");

    //başarılı giriş yaptığımızı doğrulamak için
    private final By accountButton = By.id("navbarAccount");
    private final By logoutButton = By.id("navbarLogoutButton");

    // Negatif doğrulama elementi
    private final By errorMessage = By.cssSelector(".error");

    // login sayfasındaki formun altındaki Not yet a customer bağlantısı
    private final By newCustomerLink = By.cssSelector("#newCustomerLink a");

    private final By navbarLoginButton = By.id("navbarLoginButton");

    public void bilgileriDoldurVeGirisYap(String email, String password){
        elementHelper.sendKeys(emailInput, email);
        elementHelper.sendKeys(passwordInput, password);

        // Eğer login butonu aktifse tıkla pasifse atla
        if (isLoginButtonEnabled()) {
            elementHelper.click(loginButton);
        }
    }

    public boolean cikisButonuGorunurMu(){
        //kullanıcı giriş yaptıktan sonra Account menüsüne tıklarız
        elementHelper.click(accountButton);
        //açılan menüde logout butonu belirdimi kontrol ederiz
        return elementHelper.isDisplayed(logoutButton);
    }

    public void oturumuKapat() {
        // Eger menu zaten acik degilse Account butonuna basarak ac
        List<WebElement> logoutBtns = Driver.getDriver().findElements(logoutButton);
        if (logoutBtns.isEmpty() || !logoutBtns.get(0).isDisplayed()) {
            elementHelper.click(accountButton);
        }
        elementHelper.click(logoutButton);

        // Logout sonrasi eski menunun kapandigini dogrula
        wait.until(ExpectedConditions.invisibilityOfElementLocated(logoutButton));
    }

    public boolean cikisBasariliMi() {
        elementHelper.click(accountButton);
        return elementHelper.isDisplayed(navbarLoginButton);
    }

    public String getHataMesajMetni(){
        //getText element görünene kadar bekletir
        return elementHelper.getText(errorMessage);
    }

    public void kayitOlSayfasinaGit(){
        // Elementin olduğu yere odaklan ve JS ile katman engeline takılmadan tıkla
        // Veya standart click öncesi scroll yap:
        elementHelper.scrollToElement(newCustomerLink);
        elementHelper.clickWithJS(newCustomerLink);
    }

    //Butonun HTML disabled özelliğini doğrulamak için
    public boolean isLoginButtonEnabled() {
        return Driver.getDriver().findElement(loginButton).isEnabled();
    }

    //sadece bilgi yazan ayrı bir metot
    public void sadeceBilgileriDoldur(String email, String password){
        elementHelper.sendKeys(emailInput, email);
        elementHelper.sendKeys(passwordInput, password);
    }
}
