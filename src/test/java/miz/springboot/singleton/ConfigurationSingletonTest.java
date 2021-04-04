package miz.springboot.singleton;

import miz.springboot.AppConfig;
import miz.springboot.Order.OrderServiceImpl;
import miz.springboot.member.MemberRepository;
import miz.springboot.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
       ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        //@Configuration이 없으면 테스트가 깨진다.
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
    }

    @Test
    void configurationDeepTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        // 클래스 명에 뭔가 붙어서 나옴
        // 바이트코드조작 라이브러리를 통해 AppConfig클래스를 상속받은 임의의 다른 클래스르 만듬

    }
}
