package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야 한다.")
    void vip_o(){
//        given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
//        when
        int discount = discountPolicy.discount(member, 10000);
//        then
        assertThat(discount).isEqualTo(1000);
    }


    //테스트를 할 때는 성공 테스트 말고 실패할 경우의 테스트도 만들어 봐야함.
    @Test
    @DisplayName("VIP가 아닌 경우 할인이 적용되지 않아야 한다.")
    void vip_x(){
//        given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
//        when
        int discount = discountPolicy.discount(member, 10000);
//        then
        assertThat(discount).isEqualTo(0);
    }
}