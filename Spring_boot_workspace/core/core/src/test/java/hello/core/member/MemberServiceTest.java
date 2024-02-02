package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){//given when then은 어떤 환경이 주어졌는지, 그때 , 무엇을 할 것인지
        //given
        Member member = new Member(1L, "memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertEquals(member,findMember);
    }
}
