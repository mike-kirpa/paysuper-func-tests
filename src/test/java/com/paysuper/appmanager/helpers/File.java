package com.paysuper.appmanager.helpers;

import com.paysuper.tests.TestBase;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class File {

    public static String getResource(String resourceName) {
        try {
            return Paths.get(TestBase.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }
}
