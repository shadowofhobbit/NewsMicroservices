package iuliia.questionsservice.questions;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.zip.GZIPInputStream;

@Service
public class QuestionsService {
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String API_BASE_URL = "https://api.stackexchange.com/2.2/questions" +
            "?order=desc&sort=activity&site=stackoverflow";
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.of(10, ChronoUnit.SECONDS))
            .build();

    public Questions getQuestionsFromSO(String tag) throws IOException, InterruptedException {
        Instant dayAgo = Instant.now().minus(1, ChronoUnit.DAYS);
        var uri = API_BASE_URL + "&tagged=" + tag + "&fromdate=" + dayAgo.getEpochSecond();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        Questions questions;
        try (InputStream inputStream = handleGzip(response)) {
            questions = mapper.readValue(inputStream, Questions.class);
        }
        return questions;
    }

    private static InputStream handleGzip(HttpResponse<InputStream> response) throws IOException {
        InputStream inputStream;
        String encoding = response.headers().firstValue("Content-Encoding").orElse("");
        if (encoding.equals("gzip")) {
            inputStream = new GZIPInputStream(response.body());
        } else {
            inputStream = response.body();
        }
        return inputStream;
    }
}

