package miz.springboot.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingle() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //threadB : B사용자 2만원주문
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        //싱글 톤이라  바뀜.
        int price = statefulService1.getPrice();
        assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
