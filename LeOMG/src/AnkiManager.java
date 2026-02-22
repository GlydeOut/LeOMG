import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnkiManager {

    public static void addCard(String deck, String front, String back) throws IOException, InterruptedException {
        String json = """
        {
          "action": "addNote",
          "version": 6,
          "params": {
            "note": {
              "deckName": "%s",
              "modelName": "Basic (and reversed card)",
              "fields": {
                "Front": "%s",
                "Back": "%s"
              },
              "tags": ["Generated with LeOMG"]
            }
          }
        }
        """.formatted(deck, front, back);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8765"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}







