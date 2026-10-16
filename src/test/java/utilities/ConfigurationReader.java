package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties; //Javanın proporties uzantılı dosyalarını okumak için üretilmiş sınıf

    static {
        try {
            //dosyamızın proje içindeki yolunu veriyoruz
            String path = "configuration.properties";

            //dosyayı bayt olarak okumak için kanal açıyor
            FileInputStream input = new FileInputStream(path);

            //nesneyi başlat
            properties = new Properties();

            //açılan kanaldan gelen veriyi properties nesnesine ver
            properties.load(input);

            //işin bitti kanalı kapat
            input.close();
        }catch(Exception e) {

            e.printStackTrace();
            throw new RuntimeException("configuration.properties dosyası okunamadı.");
        }
    }

    //properties private tanımlandı ya dışarıya kapalı olduğu için getter metodu yazıyoruz
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
