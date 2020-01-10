package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
    String filename;
    //файл, который хранит свойства нашего проекта
    File file = new File("src/test/resources/conf/" + filename +".properties");
    Properties properties = new Properties();

    public GetProperties(String filename)  {
        this.filename = filename;
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
