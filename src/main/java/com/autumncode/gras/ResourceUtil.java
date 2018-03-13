package com.autumncode.gras;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResourceUtil {
    public String readLine(String testResourceName) {
        try {
            if (testResourceName.startsWith("/")) {
                testResourceName = testResourceName.substring(1);
            }
            List<String> locations = new ArrayList<>();
            locations.add(testResourceName);
            locations.add("/" + testResourceName);
            InputStream in = null;
            URLClassLoader loader = new URLClassLoader(new URL[]{new File(System.getProperty("user.home")+"/").toURI().toURL()});

            for (String location : locations) {
                in = loader.getResourceAsStream(location);
                if (in != null) {
                    System.out.println("found one at "+location);
                    break;
                }
            }
            Scanner scanner = new Scanner(in);
            return scanner.nextLine().trim();
        } catch (MalformedURLException murle) {
            murle.printStackTrace();
            throw new RuntimeException(murle);
        }
    }
}
