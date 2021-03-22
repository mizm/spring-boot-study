package miz.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import miz.springboot.repository.MemberRepository;
import miz.springboot.repository.MemoryMemberRepository;
import miz.springboot.service.MemberService;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
