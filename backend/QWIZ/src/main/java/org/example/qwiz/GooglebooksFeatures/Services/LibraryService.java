package org.example.qwiz.GooglebooksFeatures.Services;

import org.example.qwiz.GooglebooksFeatures.DTO.LibraryDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private static final String API_KEY = "AIzaSyA0v0zMrFVPgiTVFPkdR7zvZg1RcPhwGr4";
    public List<LibraryDTO> getLibrary(String search){
        try {
            String link = "https://www.googleapis.com/books/v1/volumes?q=" + search + "&key=" + API_KEY;
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(response.toString());
                JSONArray items = (JSONArray) object.get("items");
                System.out.println(object);
                List<LibraryDTO> result = new ArrayList<>();
                for(int i = 0; i < items.size(); i++){
                    JSONObject item = (JSONObject) items.get(i);
                    JSONObject volumeInfo = (JSONObject) item.get("volumeInfo");
                    JSONObject accessInfo = (JSONObject) item.get("accessInfo");

                    String title = volumeInfo.get("title").toString();
                    JSONArray authors = (JSONArray) volumeInfo.get("authors");
                    String[] authorz = new String[authors.size()];

                    for (int j = 0; j < authors.size(); j++) {
                        // Each element in the authors array is already a String, so we can get it directly
                        String auth = authors.toString();
                        authorz[j] = auth;
                    }

                    String publishedDate = CheckObject(volumeInfo.get("publishedDate"));
                    String description = CheckObject(volumeInfo.get("description"));
                    String language = CheckObject(volumeInfo.get("language"));
                    String pageCount = CheckObject(volumeInfo.get("pageCount"));
                    String averageRating = CheckObject(volumeInfo.get("averageRating"));
                    //thumb nail
                    JSONObject img = (JSONObject) volumeInfo.get("imageLinks");
                    String thumbnailLink = img.get("thumbnail").toString();
                    //pdf link
                    String pdfLink = "";
                    String pdfstatus = " ";
                    JSONObject pdf = (JSONObject) accessInfo.get("pdf");
                    if(pdf.size() ==2){
                        pdfstatus = pdf.get("isAvailable").toString();
                        pdfLink = pdf.get("acsTokenLink").toString();
                    }
                    else pdfLink = "Not Available";

                    //web read
                    String webread = accessInfo.get("webReaderLink").toString();

                    LibraryDTO dto = new LibraryDTO(title,authorz[0],publishedDate,language,description,pageCount,averageRating,thumbnailLink,pdfstatus,pdfLink,webread);
                    result.add(dto);
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + authorz[0]);
                    System.out.println("Publisged Date: " + publishedDate);
                    System.out.println("langueage: " + language);
                    System.out.println("Description: " + description);
                    System.out.println("Page Count: " + pageCount);
                    System.out.println("Average Rating: " + averageRating);
                    System.out.println("Thumbnail :" + thumbnailLink);
                    System.out.println("Pdf Status: " + pdfstatus);
                    System.out.println("Pdf Link" + pdfLink);
                    System.out.println("Web Read Link" + webread);

                    System.out.println("=======================================");

                }
                return result;
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String CheckObject(Object obj){
        if(obj == null){
            return "Not Available";
        }
        return obj.toString();
    }
    private boolean hasText(Object obj) {
        return obj != null && StringUtils.hasText(obj.toString());
    }
}
