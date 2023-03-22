package ynzmz.server.board.free.mapper;


import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.free.dto.FreeDto;
import ynzmz.server.comment.free.dto.FreeCommentDto;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.dto.MemberDto;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.mapper.MemberMapper;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FreeMapper {
    Free freePostToFree(FreeDto.post p);
    Free freePatchToFree(FreeDto.patch p);
//---------------------------Response-----------------------
    FreeDto.DetailResponse freeToFreeDetailResponse(Free free);

    FreeDto.ListResponse freeToFreeListResponse(Free free);


    List<FreeDto.ListResponse> freesToFreeListResponses(List<Free> frees);




}
