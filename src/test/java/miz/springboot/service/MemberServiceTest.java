package miz.springboot.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import miz.springboot.domain.Member;
import miz.springboot.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	@BeforeEach
	void beforEach() {
			memberRepository = new MemoryMemberRepository();
			memberService = new MemberService(memberRepository);
	}

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}
	@Test
	void 회원가입() {
		//mock 객체~
		//given
		Member member = new Member();
		member.setName("hello");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("hello");

		Member member2 = new Member();
		member2.setName("hello");
		//when
		memberService.join(member1);
		IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
			() -> memberService.join(member2));
		//then
		assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

	}

	@Test
	void findOne() {

	}

}
