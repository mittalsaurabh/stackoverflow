package org.example.Entity;

import org.example.Interfaces.VotingStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@GenericGenerator(
        name = "global_sequence",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "global_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1"),
                @Parameter(name = "optimizer", value = "none"),
                @Parameter(name = "allocation_size", value = "1")
        }
)
public abstract class TextBasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_sequence")
    protected Long id;

    protected String text;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    protected User author;

    protected LocalDate createdAt;
    protected LocalDate lastUpdated;

    @ElementCollection
    protected Set<Long> upVotes = new HashSet<>();

    @ElementCollection
    protected Set<Long> downVotes = new HashSet<>();

    @Transient
    protected VotingStrategy votingStrategy;

    public TextBasedEntity() {
        this.createdAt = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }

    public TextBasedEntity(String text, User author) {
        this();
        this.text = text;
        this.author = author;
    }

    public void upVote(User user) {
        if (downVotes.contains(user.getId())) {
            downVotes.remove(user.getId());
        }
        upVotes.add(user.getId());
    }

    public void downVote(User user) {
        if (upVotes.contains(user.getId())) {
            upVotes.remove(user.getId());
        }
        downVotes.add(user.getId());
    }

    public void updateText(String text) {
        this.text = text;
        this.lastUpdated = LocalDate.now();
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUpVoteCount() {
        return upVotes.size();
    }

    public int getDownVoteCount() {
        return downVotes.size();
    }

    public User getCreator() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDate getCreationDateTime() {
        return createdAt;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public Set<Long> getMembersWhoDownVotedThisEntity() {
        return downVotes;
    }

    public Set<Long> getMembersWhoUpVotedThisEntity() {
        return upVotes;
    }
}
