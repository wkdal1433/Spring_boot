package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /** 할인 정책이 바뀌었으니 객체도 바꿔줘야함.
     * 우리가 지금 구현한 것은 의존성을 잘 지킨 것 처럼 보이는데
     * 사실 추상 인터페이스인 OrderServiceImpl뿐만 아니라
     * 아래 FixDiscountPolicy인 구체클래스에도 의존하고 있게 됨.
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     */
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
