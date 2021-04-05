package miz.springboot.autowired;

import miz.springboot.AutoAppConfig;
import miz.springboot.discount.DiscountPolicy;
import miz.springboot.member.Grade;
import miz.springboot.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class allBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rate = discountService.discount(member,10000,"rateDiscountPolicy");
        assertThat(rate).isEqualTo(2000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
