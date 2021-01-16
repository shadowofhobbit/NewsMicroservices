package iuliia.questionsservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.zip.GZIPInputStream;

@Service
public class QuestionsService {
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Questions getQuestionsFromSO() {
        Questions questions = null;
        var httpClient = HttpClient.newHttpClient();

        var uri = "https://api.stackexchange.com/2.2/questions?fromdate=1610755200&order=desc&sort=activity&tagged=spring&site=stackoverflow";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            String encoding = response.headers().firstValue("Content-Encoding").orElse("");
            InputStream inputStream;
            if (encoding.equals("gzip")) {
                inputStream = new GZIPInputStream(response.body());
            } else {
                inputStream = response.body();
            }
            questions = mapper.readValue(inputStream, Questions.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

