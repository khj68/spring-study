package jpabook.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpabook.jpashop.domain.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
  
}
