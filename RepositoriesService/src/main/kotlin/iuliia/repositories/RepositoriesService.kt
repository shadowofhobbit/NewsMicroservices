package iuliia.repositories


import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import javax.annotation.PostConstruct

@Service
class RepositoriesService @Autowired constructor() {
    private val mapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    private val httpClient = HttpClient.newHttpClient()
    private val url = "https://api.github.com/search/repositories?q=language:java&sort=updated&order=desc"

    @PostConstruct
    fun getRepositories() {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        val searchResult = mapper.readValue<SearchResult>(response.body())
        println(searchResult.items)
    }
}


