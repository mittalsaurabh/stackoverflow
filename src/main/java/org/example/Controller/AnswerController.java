package org.example.Controller;

import org.example.App.AppService;
import org.example.Entity.Answer;
import org.example.Entity.Question;
import org.example.Row.AnswerRow;
import org.example.Row.VoteRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AppService appService;

    public AnswerController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public Answer addAnswer(@RequestBody AnswerRow answer) {
        return appService.addAnswer(answer);
    }

    @PostMapping("{id}/vote")
    public Answer vote(@PathVariable Long id, @RequestBody VoteRequest userId){
        return appService.voteAnswer(id, userId);
    }
}
