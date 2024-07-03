package org.example.Controller;

import org.example.App.AppService;
import org.example.Entity.Question;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final AppService appService;

    @Autowired
    public QuestionController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public Question addQuestion(@RequestBody QuestionRow question) throws IllegalArgumentException {
        try {
            return appService.addQuestion(question);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public Question updateQuestion(@PathVariable long id,  @RequestBody QuestionRow question) {
        try {
            return appService.updateQuestion(id, question);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/tag")
    public List<Question> getQuestionsByTag(@RequestParam String tag) {
        return appService.getQuestionsByTag(tag);
    }

    @PostMapping("/{id}/vote")
    public Question vote(@PathVariable Long id, @RequestBody VoteRequest userId){
        return appService.voteQuestion(id, userId);
    }




    @PostMapping("/{id}/tag")
    public Question addTag(@PathVariable Long id, @RequestBody String tag) {
        return appService.addTag(id, tag);
    }


}
