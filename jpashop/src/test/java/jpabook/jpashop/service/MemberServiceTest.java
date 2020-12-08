package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {
  
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;
  
  @Test
  @Rollback(false)
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("kim");

    //when
    Long savedId = memberService.join(member);

    //then
    assertEquals(member, memberRepository.findOne(savedId));

  }

  @Test
  public void 중복_회원_예외() throws Exception {
    //given

    //when

    //then

  }
}
