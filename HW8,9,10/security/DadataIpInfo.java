package com.example.demo.security;

import com.sun.deploy.net.HttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class DadataIpInfo {
    private static final String API_KEY = "5101156e635ab9c23a131f1c799c4407f1c0419b";
    private static final String targetURL = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/iplocate/address?ip=";
    private static final String targetURL2Ip = "http://api.2ip.ua/geo.json?ip=";

    public String getCountryIsoCode(String ipAddress) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetURL + ipAddress);
            connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Token " + API_KEY);

            StringBuilder sb = new StringBuilder();
            int HttpResult = connection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.println(sb.toString());
                JSONObject object = (JSONObject) new JSONParser().parse(sb.toString());
                JSONObject locationObject = (JSONObject) object.get("location");
                if(locationObject != null) {
                    JSONObject dataObject = (JSONObject) locationObject.get("data");
                    String isoCode = (String) dataObject.get("country_iso_code");
                    System.out.println(isoCode);
                    return isoCode;
                } else {
                    URL url1 = new URL(targetURL2Ip+ipAddress);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    HttpResult = connection.getResponseCode();
                    StringBuilder sb1 = new StringBuilder();
                    if(HttpResult == HttpURLConnection.HTTP_OK){
                        BufferedReader br1 = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                        String line1 = null;
                        while ((line1 = br1.readLine()) != null) {
                            sb1.append(line1).append("\n");
                        }
                        br1.close();
                        JSONObject object1 = (JSONObject) new JSONParser().parse(sb1.toString());
                        String isoCode = (String) object1.get("country_code");
                        return isoCode;
                    }
                }
            } else {
                System.out.println(connection.getResponseMessage());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
        return "";
    }
}
