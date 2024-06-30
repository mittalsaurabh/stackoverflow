package org.example.Row;

import org.example.Enums.VoteType;

public class VoteRequest {

    private Long userId;
    private VoteType voteType;

    public VoteRequest() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
}
