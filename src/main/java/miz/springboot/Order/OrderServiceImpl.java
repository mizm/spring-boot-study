package miz.springboot.Order;

import miz.springboot.discount.DiscountPolicy;
import miz.springboot.discount.FixDiscountPolicy;
import miz.springboot.discount.RateDiscountPolicy;
import miz.springboot.member.Member;
import miz.springboot.member.MemberRepository;
import miz.springboot.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 할인 정책을 바꿔야 하는 dip 위반
    // nullpoint exceptiondl 발생함
    // 누군가가 주입해줘야함
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
