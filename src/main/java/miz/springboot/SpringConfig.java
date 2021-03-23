package miz.springboot;

import miz.springboot.repository.JpaMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import miz.springboot.repository.MemberRepository;
import miz.springboot.repository.MemoryMemberRepository;
import miz.springboot.service.MemberService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {


	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	private final MemberRepository memberRepository;

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

//	@Bean
//	public MemberRepository memberRepository() {
////		return new JpaMemberRepository(memberRepository);
//	}
}
