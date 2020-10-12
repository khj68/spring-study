package jun.study.service;

import jun.study.repository.MemberRepository;
import jun.study.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member) {
        
    }
}
