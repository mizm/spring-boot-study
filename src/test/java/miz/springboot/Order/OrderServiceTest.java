package miz.springboot.Order;

import miz.springboot.AppConfig;
import miz.springboot.member.Grade;
import miz.springboot.member.Member;
import miz.springboot.member.MemberService;
import miz.springboot.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "member", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        ///then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}