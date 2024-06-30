package org.example.App;

import org.example.Entity.*;
import org.example.Enums.VoteType;
import org.example.Exception.AuthorNotFoundException;
import org.example.Repository.AnswerRepository;
import org.example.Row.AnswerRow;
import org.example.Row.CommentRow;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.example.Service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {
    private final CommentService commentService;
    private final UserService userService;

    private final QuestionService questionService;

    private final TagService tagService;

    private final AnswerService answerService;

    public AppService(CommentService commentService, UserService userService, QuestionService questionService, TagService tagService, AnswerService answerService) {
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


//    public List<Question> getAllQuestions() {
//
//    }

    public Question addQuestion(QuestionRow question) {
        User author = userService.getUserById(question.getAuthorId());
        if (author == null) {
            throw new AuthorNotFoundException("Author not found by this id : " + question.getAuthorId());
        }
        List<Tag> tags = new ArrayList<>();
        for (String tagName : question.getTags()) {
            Tag tag = tagService.getTagByName(tagName);
            if (tag == null) {
                tag = new Tag(tagName);
                tagService.addTag(tag);
            }
            tags.add(tag);
        }
        Question newQuestion = new Question(question.getText(), author, question.getTitle(), tags);
        return questionService.addQuestion(newQuestion);
    }

    public List<Question> getQuestionsByTag(String tagName) {
        Tag tag = tagService.getTagByName(tagName);
        if(tag == null) {
            return new ArrayList<>();
        }
        return questionService.getQuestionsByTag(tag);
    }

    public Answer addAnswer(AnswerRow answer) {
        Question question = questionService.getQuestionById(answer.getQuestionId());
        if (question == null) {
            throw new IllegalArgumentException("Question not found by this id : " + answer.getQuestionId());
        }
        User author = userService.getUserById(answer.getAuthorId());
        if (author == null) {
            throw new AuthorNotFoundException("Author not found by this id : " + answer.getAuthorId());
        }

        Answer newAnswer = new Answer(answer.getText(), author, question, answer.getMedia());
        return answerService.addAnswer(newAnswer);
    }

    public Comment addComment(CommentRow comment) {
        User author = userService.getUserById(comment.getAuthorId());
        if (author == null) {
            throw new AuthorNotFoundException("Author not found by this id : " + comment.getAuthorId());
        }

        if(comment.getQuestionId() != null && comment.getAnswerId() != null) {
            throw new IllegalArgumentException("Comment can have a questionId or an answerId, not both");
        }
        if (comment.getQuestionId() != null) {
            Question question = questionService.getQuestionById(comment.getQuestionId());
            if (question == null) {
                throw new IllegalArgumentException("Question not found by this id : " + comment.getQuestionId());
            }
            Comment newComment = new Comment(comment.getText(), author, question);
            return commentService.addComment(newComment);
        } else if (comment.getAnswerId() != null) {
            Answer answer = answerService.getAnswerById(comment.getAnswerId());
            if (answer == null) {
                throw new IllegalArgumentException("Answer not found by this id : " + comment.getAnswerId());
            }
            Comment newComment = new Comment(comment.getText(), author, answer);
            return commentService.addComment(newComment);
        } else {
            throw new IllegalArgumentException("Comment must have a question or an answer");
        }
    }

    public Question voteQuestion(Long id, VoteRequest voteRequest) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            throw new IllegalArgumentException("Question not found by this id : " + id);
        }
        User user = userService.getUserById(voteRequest.getUserId());
        if (user == null) {
            throw new AuthorNotFoundException("Author not found by this id : " + voteRequest.getUserId());
        }

        if(voteRequest.getVoteType().equals(VoteType.DOWNVOTE))
            question.downVote(user);
        else if(voteRequest.getVoteType().equals(VoteType.UPVOTE))
        question.upVote(user);
        return questionService.addQuestion(question);
    }

    public Answer voteAnswer(Long id, VoteRequest voteRequest) {
        Answer answer = answerService.getAnswerById(id);
        if (answer == null) {
            throw new IllegalArgumentException("Answer not found by this id : " + id);
        }
        User user = userService.getUserById(voteRequest.getUserId());
        if (user == null) {
            throw new AuthorNotFoundException("Author not found by this id : " + voteRequest.getUserId());
        }

        if(voteRequest.getVoteType().equals(VoteType.DOWNVOTE))
            answer.downVote(user);
        else if(voteRequest.getVoteType().equals(VoteType.UPVOTE))
            answer.upVote(user);
        return answerService.addAnswer(answer);
    }

//    public Question downVoteQuestion(Long id, Long userId) {
//        Question question = questionService.getQuestionById(id);
//        if (question == null) {
//            throw new IllegalArgumentException("Question not found by this id : " + id);
//        }
//        User user = userService.getUserById(userId);
//        if (user == null) {
//            throw new AuthorNotFoundException("Author not found by this id : " + userId);
//        }
//        question.downVote(user);
//        return questionService.addQuestion(question);
//    }






//    public Question getQuestionById(Long id) {
//    }
}
