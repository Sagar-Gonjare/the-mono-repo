package org.dnyanyog.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RestAPIClient<T> {

  public T sendGetRequest(String apiUrl, Class<T> responseType) {
    try {
      URL url = new URL(apiUrl);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("GET");

      int responseCode = connection.getResponseCode();

      // Check if the response code indicates success (e.g., 200 OK)
      if (responseCode == HttpURLConnection.HTTP_OK) {

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Received response " + response.toString());
        T responseData = objectMapper.readValue(response.toString(), responseType);

        connection.disconnect();

        return responseData;
      } else {
        System.err.println("API request failed with response code: " + responseCode);
      }

      // Close the connection
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public T sendPostRequest(String apiUrl, String requestData, Class<T> responseType) {
    System.out.println(requestData);

    try {
      URL url = new URL(apiUrl);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

      ObjectMapper objectMapper = new ObjectMapper();
      //   String requestJson = objectMapper.writeValueAsString(requestData);

      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = requestData.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
      }

      //            int responseCode = connection.getResponseCode();

      // Check if the response code indicates success (e.g., 200 OK)
      //            if (responseCode == HttpURLConnection.HTTP_OK) {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      T responseData = objectMapper.readValue(response.toString(), responseType);

      connection.disconnect();

      return responseData;
      //            } else {
      //                System.err.println("API request failed with response code: " +
      // responseCode);
      //            }

      //            connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public T sendDeleteRequest(String apiUrl, Class<T> responseType) {
    try {
      URL url = new URL(apiUrl);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      connection.setRequestMethod("DELETE");

      int responseCode = connection.getResponseCode();

      // Check if the response code indicates success (e.g., 200 OK)
      if (responseCode == HttpURLConnection.HTTP_OK) {

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Received response " + response.toString());
        T responseData = objectMapper.readValue(response.toString(), responseType);

        connection.disconnect();

        return responseData;
      } else {
        System.err.println("API request failed with response code: " + responseCode);
      }

      // Close the connection
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
