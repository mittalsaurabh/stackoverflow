package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends TextBasedEntity {

    @ManyToOne
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JsonBackReference
    private Answer answer;

    public Comment() {
        super();
    }

    public Comment(String text, User author, Question question) {
        super(text, author);
        this.question = question;
    }

    public Comment(String text, User author, Answer answer) {
        super(text, author);
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
