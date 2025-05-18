package com.example.smartpropertymanagementsystem;
import com.google.gson.*;
            import java.io.*;
            import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;
import javafx.application.Platform;

    public class GeminiApi {

        private static final String API_KEY = "AIzaSyCycesWlA0bQNL9vOqRwBXPNUDe3bfgTp0";  // Replace with your Gemini API key
        private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + API_KEY;

        public static void getGeminiResponse(String userPrompt, Consumer<String> callback) {
            new Thread(() -> {
                try {
                    // Prepare JSON request
                    JsonObject part = new JsonObject();
                    part.addProperty("text", userPrompt);

                    JsonArray partsArray = new JsonArray();
                    partsArray.add(part);

                    JsonObject content = new JsonObject();
                    content.add("parts", partsArray);

                    JsonArray contentsArray = new JsonArray();
                    contentsArray.add(content);

                    JsonObject request = new JsonObject();
                    request.add("contents", contentsArray);

                    // HTTP request
                    URL url = new URL(API_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(request.toString().getBytes("utf-8"));
                    }

                    // Read response
                    BufferedReader br;
                    if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                    } else {
                        br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                    }

                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        responseBuilder.append(line.trim());
                    }

                    // Parse JSON
                    JsonObject responseJson = JsonParser.parseString(responseBuilder.toString()).getAsJsonObject();
                    JsonArray candidates = responseJson.getAsJsonArray("candidates");

                    String responseText = "No response.";
                    if (candidates != null && candidates.size() > 0) {
                        JsonObject first = candidates.get(0).getAsJsonObject();
                        JsonArray responseParts = first.getAsJsonObject("content").getAsJsonArray("parts");
                        responseText = responseParts.get(0).getAsJsonObject().get("text").getAsString();
                    }

                    String finalResponse = responseText;
                    Platform.runLater(() -> callback.accept(finalResponse));

                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> callback.accept("Error: " + e.getMessage()));
                }
            }).start();
        }
    }
