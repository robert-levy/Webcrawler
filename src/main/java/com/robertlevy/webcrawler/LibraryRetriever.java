package com.robertlevy.webcrawler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LibraryRetriever {

    public void downloadWebpages(ArrayList webpages) {

        //Download webpages to downloaded_webpages folder (old files are overwritten)
        int num = 0;
        for (Object webpage : webpages) {
            getWebpage(webpage.toString(),num);
            num++;
        }
    }

    private void getWebpage(String webpage, int num) {
        try {

            // Create URL object
            URL url = new URL(webpage);
            BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));

            // Enter filename in which you want to download
            BufferedWriter writer = new BufferedWriter(new FileWriter("downloaded_webpages/Download"+num+".html"));

            // read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                writer.write(line);
            }

            readr.close();
            writer.close();
            System.out.println("Successfully Downloaded.");
        }

        // Exceptions
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        } catch (IOException ie) {
            System.out.println("IOException raised");
        }
    }


    public void extractLibraries() {

    }

    public void printMostUsedLibraries() {
    }
}
