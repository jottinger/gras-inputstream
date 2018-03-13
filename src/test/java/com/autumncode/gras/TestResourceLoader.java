package com.autumncode.gras;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TestResourceLoader {
    String testResourceName = "classpath-resource.txt";
    String homeResource="shouldNotExist.txt";
    String resourceInHome = System.getProperty("user.home") + "/" + homeResource;

    @Test
    public void readDefaultResource() throws IOException {
        // need to make sure the "sample file" doesn't exist - if it does, fail to test.
        File file = new File(resourceInHome);
        if (file.exists()) {
            fail("Cannot run tests: ");
        }
        // okay, file doesn't exist. Let's try to read it from the classpath.
        ResourceUtil loader = new ResourceUtil();
        assertEquals(loader.readLine(testResourceName), "from classpath");
        try {
            try (FileWriter fileWriter = new FileWriter(file);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println("from user.home\n");
            }
            assertEquals(loader.readLine(homeResource), "from user.home");
        } finally {
            file.delete();
        }
    }
}
