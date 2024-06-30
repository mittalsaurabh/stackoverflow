package org.example.Entity;

import org.example.Interfaces.VotingStrategy;

public class DefaultVotingStrategy implements VotingStrategy {
    @Override
    public void upVote(TextBasedEntity entity, User user) {
        if (entity.getMembersWhoDownVotedThisEntity().contains(user.getId())) {
            entity.getMembersWhoDownVotedThisEntity().remove(user.getId());
        }
        entity.getMembersWhoUpVotedThisEntity().add(user.getId());
    }

    @Override
    public void downVote(TextBasedEntity entity, User user) {
        if (entity.getMembersWhoUpVotedThisEntity().contains(user.getId())) {
            entity.getMembersWhoUpVotedThisEntity().remove(user.getId());
        }
        entity.getMembersWhoDownVotedThisEntity().add(user.getId());
    }
}
