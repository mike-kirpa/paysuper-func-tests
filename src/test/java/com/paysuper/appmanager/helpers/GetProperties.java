package com.paysuper.appmanager.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
    String filename;
    //файл, который хранит свойства нашего проекта
    public Properties properties = new Properties();

    public GetProperties(String filename)  {
        File file = new File("src/test/resources/EnvironmentVariables/" + filename +".properties");
        this.filename = filename;
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value (String key){
        return properties.getProperty(key);
    }
}
