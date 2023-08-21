import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dic {
    public static void main(String[] args) throws IOException {
        String apiKey = "abb147a5-34f9-417c-b5e0-efca2fbda860";
        String apiUrl = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/";

        for (char c = 'a'; c <= 'z'; c++) {
            String letter = String.valueOf(c);
            URL url = new URL(apiUrl + letter + "?key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                // Parse the jsonResponse to extract definitions and check if words are 5 letters long
                // Implement filtering logic here
            } else {
                System.out.println("API request failed with response code: " + responseCode);
            }
        }
    }

    private static List<String> extractWords(String jsonResponse) {
        List<String> words = new ArrayList<>();

        // Assuming jsonResponse is a JSON array string
        JsonArray jsonArray = Json.createReader(new StringReader(jsonResponse)).readArray();

        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            // Assuming the JSON structure has a "word" field
            String word = jsonObject.getString("word");
            words.add(word);
        }

        return words;
    }
}





