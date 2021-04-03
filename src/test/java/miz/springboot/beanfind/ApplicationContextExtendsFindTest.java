package miz.springboot.beanfind;

import miz.springboot.discount.DiscountPolicy;
import miz.springboot.discount.FixDiscountPolicy;
import miz.springboot.discount.RateDiscountPolicy;
import miz.springboot.member.MemberRepository;
import miz.springboot.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);

    @Configuration
    static class BeanConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 자식이 둘 이상있으면 중복오류 발생")
    void 빈_이름으로_조회(){
        assertThrows(NoUniqueBeanDefinitionException.class
                , () -> ac.getBean(DiscountPolicy.class));

    }
    @Test
    @DisplayName("타입으로 조회시 이름을 지정하면 됩니다.")
    void 빈이름으로조회(){
        //같은 타입이 여러개면 어려워짐
        DiscountPolicy ratediscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(ratediscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면 하위타입으로지정 하면 됩니다.")
    void 특정하위타입조회(){
        //같은 타입이 여러개면 어려워짐
        DiscountPolicy ratediscountPolicy = ac.getBean("rateDiscountPolicy",RateDiscountPolicy.class);
        assertThat(ratediscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기.")
    void 특정타입_모두조회(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        //테스트케이스는 시스템 아웃을 안하는걸 추천~
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value"+ beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("object타입으로 모두 조회하기.")
    void object타입_모두조회(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        //테스트케이스는 시스템 아웃을 안하는걸 추천~
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value"+ beansOfType.get(key));
        }

    }


}
