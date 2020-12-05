package jun.study.discount;

import jun.study.member.Member;

public interface DiscountPolicy {
  
  int discount(Member member, int price);
}
