package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.util.List;

import java.time.Duration;

public class ElementHelper {

    private WebDriver driver;

    private WebDriverWait wait;

    //kurucu metod constructor
    public ElementHelper(){
        //Singleton tasarlanan driver sınıfından açık olan chrome alınıp
        //Bu sınıfın içindeki driver değişkenine eşitlendi this.driver
        this.driver = Driver.getDriver();
        //this.driverı en fazla 10 saniye element gelene kadar bekle
        //thread.sleep()ten farkı dinamik beklemedir
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //Dışarıdan bir bylocator alır ve tıklar
    public void click(By locator){
        //wait.until() belirttiğimiz kural gerçekleşene kadar bekle
        // ExpectedConditions.elementToBeClickable(locator)
        //iki şart arar element domda görünür mü ve element etkin mi
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        //böylece elemanın tıklanabilirliğinden emin olunduktan sonra tıklama gerçekleşir
        element.click();
    }
    //metin yazma metodu
    public void sendKeys(By locator, String text){
        //ınput box görünür mü?
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear(); //kutuya yazmadan önce kutuyu bi temizle
        element.sendKeys(text); // input kutusuna metni yazar
    }
    //metin okuma metodu
    public String getText(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));//biliyon artık
        return element.getText(); //elementin tagler arasındaki metninini alır ve yollar
    }
    // JavaScript kullanarak doğrudan DOM üzerinden tıklama yapar (Önündeki katmanları yok sayar)
    public void clickWithJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Element ekranın altında kaldıysa oraya doğru sayfayı kaydırır
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
    //görünürlük kontrol metodu
    //bir elementin ekranda olup olmadığını kontrol eder sonuç olarak true yada false döner
    public boolean isDisplayed(By locator){
        try {
            //elementin 10 saniye içerisinde sayfada görünür olmasını bekler
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        }catch (Exception e){
            //normalde element 10 saniye içinde sayfaya gelmezse WebDriverWait normalde hata fırlatır
            //hata catch ile yakalanıyoki testimiz burada patlamasın
            //eleman bulunmadığı için false veriyoruz
            return false;
        }
    }
    // Klavyeden ENTER gibi herhangi bir tuş ile birlikte metin yazmak için
    public void sendKeysWithKey(By locator, String text, Keys key) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text, key);
    }

    // Ekranda bir kurala uyan tüm elementleri liste halinde almak için
    public List<WebElement> getElements(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    // URL belirtilen metni içerene kadar bekler (en fazla belirlenen timeout süresi kadar)
    public boolean waitForUrlContains(String text) {
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (Exception e) {
            return false;
        }
    }

}
