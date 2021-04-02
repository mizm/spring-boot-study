package miz.springboot;

import miz.springboot.Order.OrderService;
import miz.springboot.Order.OrderServiceImpl;
import miz.springboot.discount.DiscountPolicy;
import miz.springboot.discount.FixDiscountPolicy;
import miz.springboot.member.MemberRepository;
import miz.springboot.member.MemberService;
import miz.springboot.member.MemberServiceImpl;
import miz.springboot.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정정
@Configuration
public class AppConfig {

    // 메서드 이름으로 빈을 만든다.
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
        return new FixDiscountPolicy();
    }
}
