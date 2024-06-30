package org.example.Interfaces;

import org.example.Entity.TextBasedEntity;
import org.example.Entity.User;

public interface VotingStrategy {
    void upVote(TextBasedEntity entity, User user);
    void downVote(TextBasedEntity entity, User user);
}
