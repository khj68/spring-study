package jun.study.repository;

import java.util.List;
import java.util.Optional;

import jun.study.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
