package jun.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jun.study.discount.DiscountPolicy;
import jun.study.discount.RateDiscountPolicy;
import jun.study.member.MemberRepository;
import jun.study.member.MemberService;
import jun.study.member.MemberServiceImpl;
import jun.study.member.MemoryMemberRepository;
import jun.study.order.OrderService;
import jun.study.order.OrderServiceImpl;

@Configuration
public class AppConfig {
  
  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
  
  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }

}
