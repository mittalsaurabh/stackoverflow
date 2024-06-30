package org.example.Controller;

import org.example.App.AppService;
import org.example.Entity.Comment;
import org.example.Row.CommentRow;
import org.example.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {


    private final AppService appService;

    @Autowired
    public CommentController( AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentRow comment) {
        return appService.addComment(comment);
    }

//    @GetMapping
//    public List<Comment> getAllComments() {
//        return appService.getAllComments();
//    }
//
//    @GetMapping("/{id}")
//    public Comment getCommentById(@PathVariable Long id) {
//        return appService.getCommentById(id);
//    }
}

