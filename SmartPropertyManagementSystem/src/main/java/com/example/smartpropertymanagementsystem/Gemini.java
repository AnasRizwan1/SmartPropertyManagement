package com.example.smartpropertymanagementsystem;

public class Gemini {

    public static String callGemini(String userMessage) {
        String response = "";
        try {
            // Simulate a call to the Gemini API
            response = "Gemini response to: " + userMessage;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return response;
    }


}
