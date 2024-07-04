package org.example.Controller;

import org.example.App.Facade;
import org.example.Entity.Question;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final Facade facade;

    @Autowired
    public QuestionController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Question addQuestion(@RequestBody QuestionRow question) throws IllegalArgumentException {
        try {
            return facade.addQuestion(question);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PatchMapping("/{id}/update")
    public Question updateQuestion(@PathVariable long id,  @RequestBody QuestionRow question) {
        try {
            return facade.updateQuestion(id, question);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/tag")
    public List<Question> getQuestionsByTag(@RequestParam String tag) {
        return facade.getQuestionsByTag(tag);
    }

    @PostMapping("/{id}/vote")
    public Question vote(@PathVariable Long id, @RequestBody VoteRequest userId){
        return facade.voteQuestion(id, userId);
    }




    @PostMapping("/{id}/tag")
    public Question addTag(@PathVariable Long id, @RequestBody String tag) {
        return facade.addTag(id, tag);
    }


}
