package study.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.jpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        // JPQL
        return em.createQuery("select m from Member m")
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }
}
