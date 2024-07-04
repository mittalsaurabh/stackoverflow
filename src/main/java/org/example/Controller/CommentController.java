package org.example.Controller;

import org.example.App.Facade;
import org.example.Entity.Comment;
import org.example.Row.CommentRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {


    private final Facade facade;

    @Autowired
    public CommentController( Facade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentRow comment) {
        return facade.addComment(comment);
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

