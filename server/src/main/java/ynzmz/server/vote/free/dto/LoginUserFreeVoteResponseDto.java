package ynzmz.server.vote.free.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.vote.Vote;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserFreeVoteResponseDto {
    private long FreeId;
    private Vote.Status voteStatus;
}