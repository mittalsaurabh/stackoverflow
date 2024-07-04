package org.example.Service;

import org.example.Entity.Answer;
import org.example.Entity.User;
import org.example.Enums.VoteType;
import org.example.Repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer addAnswer(Answer answer) {
        return  answerRepository.save(answer);
    }

    public Answer voteAnswer(Answer answer, User user, VoteType voteType) {
        if(voteType == VoteType.UPVOTE) {
            answer.upVote(user);
        } else {
            answer.downVote(user);
        }
        return answerRepository.save(answer);
    }

    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    public Answer validateAnswer(long id){
        Answer answer = getAnswerById(id);
        if(answer == null){
            throw new IllegalArgumentException("Answer not found by this id : " + id);
        }
        return answer;
    }
}
