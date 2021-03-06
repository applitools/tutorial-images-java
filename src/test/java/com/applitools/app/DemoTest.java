package com.applitools.app;

import com.applitools.eyes.images.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.images.Target;

import org.junit.jupiter.api.Test;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.file.Paths;

public class DemoTest {
    @Test
    public void test() {
        
        Eyes eyes = new Eyes();

        // // Define the OS and hosting application to identify the baseline.
        eyes.setHostOS("Windows 10");
        eyes.setHostApp("My maxthon browser");

        try {
            // Start the test with a viewport size of 800x600.
            eyes.open("Demo App - Images Java", "Smoke Test - Images Java", new RectangleSize(800, 600));

            // Load page image and validate.
            BufferedImage img = ImageIO.read(new URL("https://i.ibb.co/bJgzfb3/applitools.png"));

            // // Visual validation.
            eyes.check("Image buffer", Target.image(img));

            // End visual UI testing.
            eyes.close();
        } catch(IOException ex){
            System.out.println(ex);
        } finally {
            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();
        }
    }
}