package org.example.Controller;

import org.example.App.Facade;
import org.example.Entity.Question;
import org.example.Entity.User;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.example.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @PostMapping("")
    public Question addQuestion(@RequestBody QuestionRow questionRow) {
        return facade.addQuestion(questionRow);
    }

    @PatchMapping("/{id}/update")
    public Question updateQuestion(@PathVariable long id, @RequestBody QuestionRow questionRow) {
        return facade.updateQuestion(id, questionRow);
    }



    @GetMapping("/tag")
    public List<Question> getQuestionsByTag(@RequestParam String tag) {
        return facade.getQuestionsByTag(tag);
    }

    @PostMapping("/{id}/vote")
    public Question vote(@PathVariable Long id, @RequestBody VoteRequest voteRequest){
        return facade.voteQuestion(id, voteRequest);
    }


    @PostMapping("/{id}/tag")
    public Question addTag(@PathVariable Long id, @RequestBody String tag) {
        return facade.addTag(id, tag);
    }


}
