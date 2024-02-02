package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    /** 할인 정책이 바뀌었으니 객체도 바꿔줘야함.
     * 우리가 지금 구현한 것은 의존성을 잘 지킨 것 처럼 보이는데
     * 사실 추상 인터페이스인 OrderServiceImpl뿐만 아니라
     * 아래 FixDiscountPolicy인 구체클래스에도 의존하고 있게 됨.
     * 그래서 이 의존성을 바꾸는 방법은 추상 클래스만 할당하게 바꾸면 됨.
     * 그런데 추상클래스만 할당하게 되면 nullpointException이 발생.
     * 그래서 이걸 주입시켜주는 애가 필요한데 그 역할을 해주는 것이 AppConfig임.
     *
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     */


    /*
    이 아래처럼 바꾸게 되면 지금 이 OrderServiceImpl 클래스는 완전히 추상클래스에만 의존할 수 있도록 됨.
    아래 OrderServiceImpl의 매개변수인 discountPolicy로 RateDiscountPolicy가 들어올지 FixDiscountPolicy가
    들어올지를 이 클래스에서 설정하지 않음으로서 분리됨.
     */
    private final MemberRepository memberRepository;
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
}
