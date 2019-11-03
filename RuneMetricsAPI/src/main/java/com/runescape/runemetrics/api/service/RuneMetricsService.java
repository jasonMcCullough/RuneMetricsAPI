package com.runescape.runemetrics.api.service;

import com.runescape.runemetrics.api.model.Quest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RuneMetricsService {

    private static final String RUNEMETRICS_QUESTS_URL = "https://apps.runescape.com/runemetrics/quests?user=";

    public String getQuestsCompletedByUser(String username) {
        String httpResponse = createHttpConnection(RUNEMETRICS_QUESTS_URL, username);
        return parseCompletedQuestsFromHttpResponse(httpResponse);
    }

    private String convertWhitespaceForHttpEncoding(String inputString) {
        return inputString.replaceAll("\\s", "+");
    }

    private String createHttpConnection(String httpUrl, String parameter) {
        String encodedParameter = convertWhitespaceForHttpEncoding(parameter);
        try {
            URL url = new URL(httpUrl + encodedParameter);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String httpResponseOutput;
            while ((httpResponseOutput = br.readLine()) != null) {
                return httpResponseOutput;
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseCompletedQuestsFromHttpResponse(String jsonInput) {
        ArrayList<Quest> questList = new ArrayList();
        try {
            JSONObject jsonResponse = new JSONObject(jsonInput);
            JSONArray arr = jsonResponse.getJSONArray("quests");

            Quest quest = new Quest();
            for (int i = 0; i < arr.length(); i++) {
                quest.setTitle(arr.getJSONObject(i).getString("title"));
                quest.setStatus(arr.getJSONObject(i).getString("status"));
                quest.setDifficulty(arr.getJSONObject(i).getInt("difficulty"));
                quest.setMembers(arr.getJSONObject(i).getBoolean("members"));
                quest.setQuestPoints(arr.getJSONObject(i).getInt("questPoints"));
                quest.setUserEligible(arr.getJSONObject(i).getBoolean("userEligible"));

                if (quest.getStatus().equals("COMPLETED")) {
                    questList.add(quest);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return questList.toString();
    }

}