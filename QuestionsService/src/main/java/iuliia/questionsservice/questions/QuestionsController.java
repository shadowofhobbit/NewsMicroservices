package iuliia.questionsservice.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    public Questions getNewQuestions(@RequestParam String tag) throws IOException, InterruptedException {
        return questionsService.getQuestionsFromSO(tag);
    }
}
