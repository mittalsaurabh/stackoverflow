package org.example.Service;


import org.example.Entity.Answer;
import org.example.Entity.Comment;

import org.example.Entity.Question;
import org.example.Entity.User;
import org.example.Repository.CommentRepository;
import org.example.Row.CommentRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addCommentOnQuestion(CommentRow comment, User user, Question question) throws IllegalArgumentException{
        if(comment.getQuestionId() != null && comment.getAnswerId() != null) {
            throw new IllegalArgumentException("Comment can have a questionId or an answerId, not both");
        }
        if(comment.getQuestionId() == null && comment.getAnswerId() == null) {
            throw new IllegalArgumentException("Comment must have a questionId or an answerId");
        }
        return commentRepository.save(new Comment(comment.getText(), user, question));


    }
    public Comment addCommentOnAnswer(CommentRow comment, User user, Answer answer) throws IllegalArgumentException{
        if(comment.getQuestionId() != null && comment.getAnswerId() != null) {
            throw new IllegalArgumentException("Comment can have a questionId or an answerId, not both");
        }
        if(comment.getQuestionId() == null && comment.getAnswerId() == null) {
            throw new IllegalArgumentException("Comment must have a questionId or an answerId");
        }
        return commentRepository.save(new Comment(comment.getText(), user, answer));
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }
}
