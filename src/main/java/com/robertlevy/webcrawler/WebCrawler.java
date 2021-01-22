package com.robertlevy.webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebCrawler {
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a search keyword ...");
        String searchTerm = sc.nextLine();
        
        //Make request
        UrlRetriever urlRetriever = new UrlRetriever();
        ArrayList bingResults = urlRetriever.getBingResults(searchTerm);

        //Show URLs found
        System.out.println("These were the first "+bingResults.size()+" results on Bing:");
        for (int i = 0; i < bingResults.size(); i++) {
            System.out.println(bingResults.get(i));
        }

        //Download webpages, extract JS libraries used, and print the 5 most used
        LibraryRetriever libraryretriever = new LibraryRetriever();
        libraryretriever.downloadWebpages(bingResults);
        libraryretriever.extractLibraries();
        libraryretriever.printMostUsedLibraries();

    }
}
