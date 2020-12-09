package jpabook.jpashop.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
  
  private final MemberService memberService;

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMember(@RequestBody @Valid Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }\

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    
    Member member = new Member();
    member.setName(request.getName());
      
    memberService.join(member);
  }

  @Data
  private static class CreateMemberRequest {
    private String name;
  }

  @Data
  private static class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
      this.id = id;
    }
  }
}
