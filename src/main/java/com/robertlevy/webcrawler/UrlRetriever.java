package com.robertlevy.webcrawler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.SECONDS;

public class UrlRetriever {

    public ArrayList getBingResults(String searchTerm) throws IOException, InterruptedException, URISyntaxException {

        // https://docs.microsoft.com/en-us/bing/search-apis/bing-web-search/reference/query-parameters
        // Create get request (count query parameter excluded, but default is 10, though this is not guaranteed)
        searchTerm = searchTerm.replaceAll("\\s", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.bing.microsoft.com/v7.0/search?q=" + searchTerm)) //replace whitespace with +
                .header("Ocp-Apim-Subscription-Key", "5907ca092b4f40839c977ac42e677171")
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        // Send request and get response
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        // Extract urls
        ArrayList urls = extractUrls(response);

        return urls;
    }

    private ArrayList extractUrls(HttpResponse<String> response) {
        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray webPageArray = jsonResponse.getJSONObject("webPages").getJSONArray("value");
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < webPageArray.length(); i++) {
            urls.add(webPageArray.getJSONObject(i).getString("url"));
        }

        return urls;
    }
}
