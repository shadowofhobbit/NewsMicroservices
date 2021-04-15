package iuliia.repositories

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class Repository(@JsonProperty("id") val id: Long,
                      @JsonProperty("full_name") val fullName: String,
                      @JsonProperty("html_url") val htmlUrl: String,
                      @JsonProperty("description") val description: String?,
                      @JsonProperty("updated_at") val updatedAt: String)

