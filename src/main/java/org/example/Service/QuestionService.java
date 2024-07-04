package org.example.Service;

import org.example.Entity.Question;
import org.example.Entity.Tag;
import org.example.Entity.User;
import org.example.Enums.VoteType;
import org.example.Exception.QuestionNotFoundException;
import org.example.Repository.QuestionRepository;
import org.example.Row.QuestionRow;
import org.example.Row.VoteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question addQuestion(QuestionRow question, User author, List<Tag> tags) {
        Question newQuestion = new Question(question.getText(), author, question.getTitle(), tags);
        return questionRepository.save(newQuestion);
    }

    public Question voteQuestion(long id, User user, VoteRequest voteRequest) {
        Question question = validateQuestion(id);
        if (voteRequest.getVoteType() == VoteType.UPVOTE) {
            question.upVote(user);
        } else {
            question.downVote(user);
        }
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long questionId, QuestionRow question, User author, List<Tag> tags) {
        Question oldQuestion = validateQuestion(questionId);
        if (!oldQuestion.getCreator().getId().equals(author.getId())) {
            throw new IllegalArgumentException("Only the author of the question can update it");
        }
        oldQuestion.setText(question.getText());
        oldQuestion.setTitle(question.getTitle());
        oldQuestion.setTags(tags);
        return questionRepository.save(oldQuestion);
    }

    public Question addTag(long id, Tag tag) {
        Question question = validateQuestion(id);
        question.addTag(tag);
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByTag(Tag tag) {
        return questionRepository.findByTags(tag);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question validateQuestion(long id) {
        Question question = getQuestionById(id);
        if (question == null) {
            throw new QuestionNotFoundException("Question not found by this id : " + id);
        }
        return question;
    }

}
