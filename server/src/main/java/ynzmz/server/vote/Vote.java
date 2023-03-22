package ynzmz.server.vote;

public interface Vote {
    long getVoteCount();
    void setVoteCount(long voteCount);

    enum Target{
        Free,
        QUESTION,
        ANSWER,
        REVIEW,
        COMMENT,
        RECOMMENT
    }
    enum Status {
        UP,
        NONE,
        DOWN
    }
}
