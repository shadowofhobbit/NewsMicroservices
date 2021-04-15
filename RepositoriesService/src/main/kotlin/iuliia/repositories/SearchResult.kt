package iuliia.repositories

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchResult(@JsonProperty("total_count") val totalCount: Long,
                        @JsonProperty("incomplete_results") val incomplete: Boolean,
                        @JsonProperty("items") val items: List<Repository>)
