package com.paysuper.appmanager.helpers;

import com.paysuper.appmanager.ApplicationManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {

    //файл, который хранит свойства нашего проекта
    public static Properties properties = new Properties();
/*
    public GetProperties()  {
        File file = new File("src/test/resources/EnvironmentVariables/" + ApplicationManager.zone +".properties");
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    public static String value(String key){
        File file = new File("src/test/resources/EnvironmentVariables/" + ApplicationManager.zone +".properties");
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
