package org.example.Controller;

import org.example.App.Facade;
import org.example.Entity.Answer;
import org.example.Row.AnswerRow;
import org.example.Row.VoteRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final Facade facade;

    public AnswerController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Answer addAnswer(@RequestBody AnswerRow answer) {
        return facade.addAnswer(answer);
    }

    @PostMapping("{id}/vote")
    public Answer vote(@PathVariable Long id, @RequestBody VoteRequest userId){
        return facade.voteAnswer(id, userId);
    }
}
