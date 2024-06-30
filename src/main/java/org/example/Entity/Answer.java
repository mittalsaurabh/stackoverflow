package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Answer extends TextBasedEntity {

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @ElementCollection
    private List<String> media;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments;

    public Answer() {
        super();
    }

    public Answer(String text, User author, Question question, List<String> photos) {
        super(text, author);
        this.question = question;
        this.media = photos;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
