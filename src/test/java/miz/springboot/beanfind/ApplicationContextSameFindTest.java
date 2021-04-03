package miz.springboot.beanfind;

import miz.springboot.AppConfig;
import miz.springboot.member.MemberRepository;
import miz.springboot.member.MemberService;
import miz.springboot.member.MemberServiceImpl;
import miz.springboot.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면 오류 발생")
    void 빈_이름으로_조회(){
        assertThrows(NoUniqueBeanDefinitionException.class
                , () -> ac.getBean(MemberRepository.class));

    }
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상있으면 빈이름지정 하면 됩니다.")
    void 빈이름으로조회(){
        //같은 타입이 여러개면 어려워짐
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    @Test
    @DisplayName("특정 타입으로 모두 조회하기.")
    void 특정타입_모두_조회(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value"+ beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }


}
