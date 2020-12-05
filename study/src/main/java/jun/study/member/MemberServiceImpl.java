package jun.study.member;

public class MemberServiceImpl implements MemberService {

  private MemberRepository memberRepository;

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

@Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  
  
}
