package ynzmz.server.vote.free.mapper;

import org.mapstruct.Mapper;
import ynzmz.server.vote.free.dto.FreeVoteDto;
import ynzmz.server.vote.free.entity.FreeVote;

@Mapper(componentModel = "spring")
public interface FreeVoteMapper {
    default FreeVoteDto.Response FreeVoteToFreeResponse(FreeVote freeVote) {

        FreeVoteDto.Response freeFreeVoteResponse = new FreeVoteDto.Response();

        freeFreeVoteResponse.setTarget(freeVote.getTarget());
        freeFreeVoteResponse.setStatus(freeVote.getStatus());
        freeFreeVoteResponse.setMemberId(freeVote.getMember().getMemberId());
        if( freeVote.getFree() != null ) {
            freeFreeVoteResponse.setFreeId(freeVote.getFree().getFreeId());
            freeFreeVoteResponse.setFreeTotalCount(freeVote.getFree().getVoteCount());
        }
        if( freeVote.getFreeComment() != null ) {
            freeFreeVoteResponse.setFreeCommentId(freeVote.getFreeComment().getFreeCommentId());
            freeFreeVoteResponse.setFreeCommentTotalCount(freeVote.getFreeComment().getVoteCount());
        }
        return freeFreeVoteResponse;
    }
}
