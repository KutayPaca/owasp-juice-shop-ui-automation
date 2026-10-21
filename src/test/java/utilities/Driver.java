package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    //singleton pattern yapısı
    private Driver(){ //boş kilit mekanizması kimse bu sınıftan bir nesne üretemesin
    }

    private static WebDriver driver; //açılacak olan tarayıcıyı tutan değişken
                                     //başlangıçta tarayıcı olmadığından null

    public static WebDriver getDriver(){

        if(driver == null){ //eğer daha önce bir tarayıcı açılmamış driver varsayılan değer null ise

            String browserType = ConfigurationReader.getProperty("browser");

            //okunan tarayıcı tipine göre ilgili tarayıcıyı başlatıcaz
            switch (browserType.toLowerCase()){
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //Chrome 111 sürümüne geçtiğinde Google bir WebSocket güvenlik kuralı eklemiş
                    //Sorun Selenium WebSocket protokolü üzerinden ChromeDriver ile haberleşiyor
                    //Google bu güncellemeyle güvenlik gerekçesiyle kaynağı belirsiz bağlantıları reddediyorum demiş
                    //Sonuç olarak tüm dünyada Selenium testleri şu hatayla çökmeye başlamış
                    //Invalid Status code=403 text=Forbidden veya Connection reset / Remote origin error
                    //Çözüm tarayıcıya şunun gönderilmesini şart koşmuş:
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--lang=en"); // Tarayıcı dilini doğrudan İngilizce yapar
                    driver = new ChromeDriver(chromeOptions);
                    break;

                    case "firefox":
                        driver = new FirefoxDriver();
                        break;

                        case "edge":
                            driver = new EdgeDriver();
                            break;

                            default:
                                throw new RuntimeException("Geçersiz tarayıcı tipi: " + browserType);
            }

            //Tarayıcıyı tam ekran yap
            driver.manage().window().maximize();
            // Sayfadaki bir element hemen yüklenmezse anında hata verme 10 saniye doma bekle
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        //zaten açık tarayıcı varsa if bloğuna girmez
        return driver;
    }

    //test bittiğinde taayıcıyı kapatma
    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver = null;//yeni testler için driver null olmalı en üstte yazdık
        }
    }

}
