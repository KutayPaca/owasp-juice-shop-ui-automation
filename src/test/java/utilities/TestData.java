package utilities;

import java.io.FileInputStream;
import java.util.Properties;

//yalnıca test verilerini testdata.properties okumakla görevlidir
public class TestData {
    private static Properties properties;

    static {
        try {
            String path = "testdata.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("testdata.properties dosyasi okunamadi.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}