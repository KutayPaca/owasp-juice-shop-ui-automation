package pages;

import org.openqa.selenium.By;
import utilities.ElementHelper;

public class RegisterPage {
    private ElementHelper elementHelper;

    public RegisterPage(){
        this.elementHelper = new ElementHelper();
    }
    // e posta giriş kutusu locatoru
    private final By emailInput = By.id("emailControl");
    //şifre giriş locatoru
    private final By passwordInput = By.id("passwordControl");
    //şifre tekrar giriş doğrulama locatoru
    private final By repeatPasswordInput = By.id("repeatPasswordControl");
    //güvenlik sorusu açılır menüsü
    private final By securityQuestionDropdown = By.cssSelector("mat-select[name='securityQuestion'], mat-select[aria-label*='security question' i]");
    private final By firstSecurtyQuestionOption = By.cssSelector("mat-option:first-child, .mat-mdc-option:first-child");
    //güvenlik sorusu cevabı kutusu
    private final By securityAnswerInput = By.id("securityAnswerControl");
    //formun en altındaki register kayıt butonu
    private final By registerButton = By.id("registerButton");
    //kayıt tamamlandığında ekranın altında çıkan bildirim çubuğu
    private final By successSnackBar = By.cssSelector(".mat-mdc-snack-bar-container, .mat-simple-snack-bar-content");

    //aksiyon metodu
    public void kayitFormunuDoldur(String email, String password, String securityAnswer){
        // E-posta ve şifreleri yaz
        elementHelper.sendKeys(emailInput, email);
        elementHelper.sendKeys(passwordInput, password);
        elementHelper.sendKeys(repeatPasswordInput, password);

        // Güvenlik sorusu kutusuna kaydır ve tıkla
        elementHelper.scrollToElement(securityQuestionDropdown);
        elementHelper.clickWithJS(securityQuestionDropdown);

        // Açılan listedeki ilk seçeneğe tıkla
        elementHelper.click(firstSecurtyQuestionOption);

        // Güvenlik cevabını yaz
        elementHelper.sendKeys(securityAnswerInput, securityAnswer);

        // Register butonuna kaydırıp tıkla
        elementHelper.scrollToElement(registerButton);
        elementHelper.clickWithJS(registerButton);
    }

    public boolean basariBildirimiGorunduMu(){
        // SnackBar ekranda belirdi mi kontrol eder
        return elementHelper.isDisplayed(successSnackBar);
    }
}
