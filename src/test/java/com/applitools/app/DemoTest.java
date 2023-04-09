package com.applitools.app;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.config.Configuration;
import com.applitools.eyes.images.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.images.ImageRunner;
import com.applitools.eyes.images.Target;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.file.Paths;

public class DemoTest {
    private static EyesRunner runner = new ImageRunner();
    private static Configuration config = new Configuration();

    @BeforeAll
    public static void setUp(){        
        //config.setServerUrl("https://eyes.applitools.com/"); //set by default
        // Define the OS and hosting application to identify the baseline.
        config.setHostOS("Windows 11");
        config.setHostApp("My maxthon browser");


    }

    @Test
    public void test() {
        
        Eyes eyes = new Eyes(runner);
        eyes.setConfiguration(config);

        try {
            // Start the test with a viewport size of 800x600.
            eyes.open("Demo App - Images Java", "Smoke Test - Images Java", new RectangleSize(800, 600));

            // Load image.
            BufferedImage img = ImageIO.read(new URL("https://i.ibb.co/bJgzfb3/applitools.png"));

            // Visual validation.
            eyes.check("Image buffer", Target.image(img));

            // End visual UI testing.
            eyes.closeAsync();
        } catch(IOException ex){
            System.out.println(ex);
        } finally {
            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();
        }
    }

    @AfterAll
    public static void tearDown(){
        //wait for Applitools test to finish and fetch the results
        TestResultsSummary  results = runner.getAllTestResults();
        System.out.println(results);
    }
}
