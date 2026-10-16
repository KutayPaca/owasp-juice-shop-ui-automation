package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));//biliyon artık
        return element.getText(); //elementin tagler arasındaki metninini alır ve yollar
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

}
