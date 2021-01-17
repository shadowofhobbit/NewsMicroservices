package iuliia.questionsservice.questions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {
    String title;

    @JsonProperty("is_answered")
    Boolean isAnswered;

    String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", isAnswered=" + isAnswered +
                ", link='" + link + '\'' +
                '}';
    }
}
