package org.example.App;

import org.example.Entity.*;
import org.example.Exception.AuthorNotFoundException;
import org.example.Exception.QuestionNotFoundException;
import org.example.Row.AnswerRow;
import org.example.Row.CommentRow;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.example.Service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Facade {
    private final CommentService commentService;
    private final UserService userService;

    private final QuestionService questionService;

    private final TagService tagService;

    private final AnswerService answerService;

    public Facade(CommentService commentService, UserService userService, QuestionService questionService, TagService tagService, AnswerService answerService) {
        this.commentService = commentService;
        this.userService = userService;
        this.questionService = questionService;
        this.tagService = tagService;
        this.answerService = answerService;
    }

    public User addUser(User user) {
        return userService.addUser(user);
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }


    public Question addQuestion(QuestionRow question) throws AuthorNotFoundException {
        List<Tag> tags = tagService.getTags(question.getTags());
        User author = userService.validateUser();
        return questionService.addQuestion(question, author, tags);
    }

    public Question updateQuestion(Long questionId, QuestionRow question) {
        List<Tag> tags = tagService.getTags(question.getTags());
        User author = userService.validateUser();
        return questionService.updateQuestion(questionId, question, author, tags);
    }

    public List<Question> getQuestionsByTag(String tagName) throws IllegalArgumentException {
        Tag tag = tagService.getTag(tagName);
        return questionService.getQuestionsByTag(tag);
    }

    public Answer addAnswer(AnswerRow answer) throws QuestionNotFoundException, AuthorNotFoundException{
        Question question = questionService.validateQuestion(answer.getQuestionId());
        User author = userService.validateUser();
        Answer newAnswer = new Answer(answer.getText(), author, question, answer.getMedia());
        return answerService.addAnswer(newAnswer);
    }

    public Comment addComment(CommentRow comment) {
        User author = userService.validateUser();
        if (comment.getQuestionId() != null) {
            Question question = questionService.validateQuestion(comment.getQuestionId());
            return commentService.addCommentOnQuestion(comment, author, question);
        }
        Answer answer = answerService.validateAnswer(comment.getAnswerId());
        return commentService.addCommentOnAnswer(comment, author, answer);
    }

    public Question voteQuestion(Long id,  VoteRequest voteRequest ) {
        User user = userService.validateUser();
        return questionService.voteQuestion(id, user, voteRequest);
    }

    public Answer voteAnswer(Long id, VoteRequest voteRequest) {
        Answer answer = answerService.validateAnswer(id);
        User user = userService.validateUser();
        return answerService.voteAnswer(answer, user, voteRequest.getVoteType());
    }

    public Question addTag(Long id, String tag) {
        Tag newTag = tagService.getTag(tag);
        return questionService.addTag(id, newTag);
    }

//    public Question getQuestionById(Long id) {
//    }
}
